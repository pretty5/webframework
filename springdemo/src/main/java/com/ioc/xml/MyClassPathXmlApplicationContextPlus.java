package com.ioc.xml;

import org.apache.commons.beanutils.ConvertUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
public class MyClassPathXmlApplicationContextPlus {
    private Map<String, Object> context = new HashMap<String, Object>();


    public static void main(String[] args) throws Exception {
        MyClassPathXmlApplicationContextPlus factory = new MyClassPathXmlApplicationContextPlus("appContext.xml");
        //从工厂中获取对象
        Person tom = (Person) factory.getBean("tom");

        tom.sayHello();
        //
        System.out.println(tom.getName());

        Person rose = (Person) factory.getBean("rose");

        System.out.println(rose.getAge());


        System.out.println(rose.getFriend().getName());


    }

    private Object getBean(String name) {
        Object o = context.get(name);
        if (o == null) {
            throw new IllegalArgumentException("no such bean defined");
        }
        return o;
    }

    public MyClassPathXmlApplicationContextPlus(String configLocation) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        InputStream resource = MyClassPathXmlApplicationContextPlus.class.getClassLoader()
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
            List<Element> constructorArgs = bean.elements("constructor-arg");
            if (constructorArgs!=null){
                Object instance = constructorSet(constructorArgs, className);
                context.put(id,instance);
            }
            List<Element> properties = bean.elements("property");
            if (properties!=null){
                Object instance = propertySet(properties, className,context.get(id));
                context.put(id, instance);
            }
        }


        //System.out.println(bean);

    }

    private Object constructorSet(List<Element> constructorArgs, String className) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        //new Class[constructorArgs.size()];
        ArrayList<Class> types = new ArrayList();
        ArrayList<Object> params = new ArrayList();
        for (Element constructorArg :
                constructorArgs) {
            String fieldName = constructorArg.attribute("name").getValue();
            Field field = clazz.getDeclaredField(fieldName);
            types.add(field.getType());
            if (constructorArg.attribute("value") != null) {
                String arg = constructorArg.attribute("value").getValue();
                params.add(ConvertUtils.convert(arg,field.getType()));
            }
            if (constructorArg.attribute("ref") != null) {
                String ref = constructorArg.attribute("ref").getValue();
                params.add(context.get(ref));
            }
        }
        Constructor<?> constructor = clazz.getDeclaredConstructor(types.toArray(new Class[constructorArgs.size()]));
        Object instance = constructor.newInstance(params.toArray());
        return instance;
    }

    private Object propertySet(List<Element> properties, String  className,Object instance) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        if (instance==null){
            instance=Class.forName(className).newInstance();
        }
        for (Element property :
                properties) {
            String fieldName = property.attribute("name").getValue();
            Field field = Class.forName(instance.getClass().getName()).getDeclaredField(fieldName);
            if (property.attribute("value") != null) {
                valueSet(field, property, instance);
            }
            if (property.attribute("ref") != null) {
                refSet(field, property, instance);
            }
        }
        return instance;
    }

    private void refSet(Field field, Element property, Object instance) throws IllegalAccessException {
        String ref = property.attribute("ref").getValue();
        field.setAccessible(true);
        field.set(instance, context.get(ref));
    }

    private void valueSet(Field field, Element property, Object instance) throws IllegalAccessException {
        String fieldValue = property.attribute("value").getValue();
        field.setAccessible(true);
        ;
        field.set(instance, ConvertUtils.convert(fieldValue,field.getType()));
    }
}
