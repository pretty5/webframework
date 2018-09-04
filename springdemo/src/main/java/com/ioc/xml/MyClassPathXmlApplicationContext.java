package com.ioc.xml;


import org.apache.commons.beanutils.ConvertUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*@ClassName:MyClassPathXmlApplicationContext
 @Description:TODO
 @Author:
 @Date:2018/9/3 10:16 
 @Version:v1.0
*/
public class MyClassPathXmlApplicationContext {
    private Map<String,Object> context=new HashMap<String, Object>();


    public static void main(String[] args) throws Exception {
        MyClassPathXmlApplicationContext factory = new MyClassPathXmlApplicationContext("appContext.xml");
        //从工厂中获取对象
        Person tom = (Person) factory.getBean("tom");

        tom.sayHello();
        //
        System.out.println(tom.getName());


    }

    private Object getBean(String name) {
        Object o = context.get(name);
        if (o==null){
            throw new IllegalArgumentException("no such bean defined");
        }
        return o;
    }

    public MyClassPathXmlApplicationContext(String configLocation) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        InputStream resource = MyClassPathXmlApplicationContext.class.getClassLoader()
                .getResourceAsStream(configLocation);
        /*通过dom4j读取xml文件*/
        SAXReader reader = new SAXReader();
        //得到dom对象
        Document document = reader.read(resource);
        Element root = document.getRootElement();
        //获取指定的元素
        List<Element> beans = root.elements("bean");

        for (Element bean :
                beans) {
            String id = bean.attribute("id").getValue();
            String className = bean.attribute("class").getValue();
            //通过反射机制获取对象
            Object instance = Class.forName(className).newInstance();


            List<Element> properties = bean.elements("property");

            for (Element property:
                 properties) {
                String fieldName = property.attribute("name").getValue();
                String fieldValue = property.attribute("value").getValue();
                Field field = Class.forName(className).getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(instance,ConvertUtils.convert(fieldValue,field.getType()));

            }


            context.put(id,instance);

        }



        //System.out.println(bean);

    }
}
