package com.kevin.spring.Iocdemo.spring;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/26 17:49
 */
public class ClassPathXmlApplicationContext  implements BeanFactory{

    private Map<String, Object> beans = new HashMap<String, Object>();


    public static void main(String[] args) {
//            ClassPathXmlApplicationContext applicationContext =  new ClassPathXmlApplicationContext();
//        applicationContext.read();

        try {
            ClassPathXmlApplicationContext applicationContext =  new ClassPathXmlApplicationContext();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    public ClassPathXmlApplicationContext() throws JDOMException, IOException,
            InstantiationException, IllegalAccessException,
            ClassNotFoundException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, InvocationTargetException
    {
        SAXBuilder sb = new SAXBuilder();

         File file = new File("D://workspace//test//spring-learning-examples//spring-Ioc-demo//com/kevin/spring/Iocdemo/beans.xml");
        // 构造文档对象
        //Document doc = sb.build(ClassPathXmlApplicationContext.class.getClassLoader().getResourceAsStream("beans.xml"));
        Document doc = sb.build(new ByteArrayInputStream(getTemplateContent().getBytes()));
        // 获取根元素
        Element root = doc.getRootElement();
        // 取到根元素所有元素
        List list = root.getChildren();

        for (int i = 0; i < list.size(); i++)
        {
            Element element = (Element) list.get(i);
            // 取id子元素
            String beanid = element.getAttributeValue("id");
            // 取class子元素
            String clzss = element.getAttributeValue("class");
            // 实例化
            Object o = Class.forName(clzss).newInstance();
            // 将所有bean放入map中
            beans.put(beanid, o);
            // 获取property 进行依赖注入
            for (Element propertyElement : (List<Element>) element
                    .getChildren("property"))
            {
                String name = propertyElement.getAttributeValue("name");
                //System.out.println(name);//userDAO
                String bean = propertyElement.getAttributeValue("bean");
                //System.out.println(bean);//userDAO
                // 从beans.xml中根据id取到类的对象
                //Object beanObj = this.getBean(name);
                // 从beans.xml中根据id取到类的对象
                Object beanObj = this.getBean(bean);
                //System.out.println(beanObj);//com.yyb.dao.impl.UserDAOImpl@a09ee92
                // 形成setXXX方法名
                String methodName = "set" + name.substring(0, 1).toUpperCase()
                        + name.substring(1);
                //System.out.println(name.substring(0, 1).toUpperCase());//U
                //System.out.println(name.substring(1));//serDAO
                //System.out.println(methodName);//setUserDAO

                // 反射机制对方法进行调用，将对象在加载bean时就注入到环境上下文中
                Method m = o.getClass().getMethod(methodName,
                        beanObj.getClass().getInterfaces()[0]);
                //System.out.println(o.getClass());//class com.yyb.service.UserService
                //System.out.println(beanObj.getClass().getInterfaces()[0]);//interface com.yyb.dao.UserDAO
                //System.out.println(m);//public void com.yyb.service.UserService.setUserDAO(com.yyb.dao.UserDAO)
                // 执行注入,相当于执行了一个setXXX(args..)的方法
                m.invoke(o, beanObj);
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    private static String getTemplateContent() {
        File file = new File("D://workspace//test//spring-learning-examples//spring-Ioc-demo//target//classes//beans.xml");
        if(!file.exists()){
            return null;
        }

        try {
            FileInputStream inputStream  = new FileInputStream(file);
            int length = inputStream.available();
            byte bytes[] = new byte[length];
            inputStream.read(bytes);
            inputStream.close();
            String str =new String(bytes);

            return str ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
