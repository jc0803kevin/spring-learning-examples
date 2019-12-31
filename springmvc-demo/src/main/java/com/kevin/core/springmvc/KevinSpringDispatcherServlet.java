package com.kevin.core.springmvc;

import com.kevin.core.springmvc.annotation.KevinAutowired;
import com.kevin.core.springmvc.annotation.KevinController;
import com.kevin.core.springmvc.annotation.KevinRequestMapping;
import com.kevin.core.springmvc.annotation.KevinService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/31 9:22
 */
public class KevinSpringDispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();
    private List<String> classNames = new ArrayList<String>();
    private Map<String, Object> ioc = new HashMap<String, Object>();
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.解析配置文件，扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.初始化所有相关的类，并且将其放入到IOC容器中
        doInstance();

        //4.完成依赖注入
        doAutoWired();

        //5.初始化HandlerMapping映射
        initHandlerMapping();

    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classpath = new File(url.getFile());
        for (File file : classpath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = scanPackage + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }

    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        for (String className : classNames) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(KevinController.class)) {
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(KevinService.class)) {
                    //1.默认的类名为首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    //2.自定义的beanName
                    KevinService service = clazz.getAnnotation(KevinService.class);
                    if (!"".equals(service.value())) {
                        beanName = service.value();
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    //3.创建接口的实例，怎么办？
                    for (Class<?> i : clazz.getInterfaces()) {
                        if (ioc.containsKey(i.getName())) {
                            throw new Exception("The beanName has existed in ioc");
                        }
                        ioc.put(i.getSimpleName(), instance);
                    }
                } else {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doAutoWired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(KevinAutowired.class)) {
                    continue;
                }

                KevinAutowired autoWired = field.getAnnotation(KevinAutowired.class);
                String beanName = autoWired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getSimpleName();
                }
                //beanName这里没有问题吗？
                field.setAccessible(true);//强制访问

                try {
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(KevinController.class)) {
                continue;
            }

            String baseUrl = "";
            if (clazz.isAnnotationPresent(KevinRequestMapping.class)) {
                KevinRequestMapping requestMapping = clazz.getAnnotation(KevinRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(KevinRequestMapping.class)) {
                    continue;
                }
                KevinRequestMapping requestMapping = method.getAnnotation(KevinRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");//正则表达式替换任意个/为单个/
                handlerMapping.put(url, method);
                System.out.println("Mapped:" + url + ":" + method);

            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6.调度，转发
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 exception" + e);
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextUrl = req.getContextPath();
        url = url.replaceAll(contextUrl, "").replaceAll("/+", "/");
        if (!this.handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 FileNotFound！");
            return;
        }
        Method method = handlerMapping.get(url);
        Map<String, String[]> params = req.getParameterMap();
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName), new Object[]{req, resp, params.get("name")[0]});
    }


    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
