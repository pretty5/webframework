package com.ioc.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
*@ClassName:Main
 @Description:TODO
 @Author:
 @Date:2018/9/3 14:21 
 @Version:v1.0
*/
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app2Context.xml");
        Animal animal = (Animal) context.getBean("dog");
        //根据类型获取
        Animal animal2 = context.getBean(Animal.class);
        System.out.println(animal);
        System.out.println(animal2);
        System.out.println(animal==animal2);
        animal.setName("tom");
        System.out.println(animal2.getName());

        //
        Person person = context.getBean(Person.class);
        System.out.println(person);
        System.out.println(person.getPet().getName());

    }
}
