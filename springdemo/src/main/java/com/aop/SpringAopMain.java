package com.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
*@ClassName:SpringAopMain
 @Description:TODO
 @Author:
 @Date:2018/9/3 17:07 
 @Version:v1.0
*/
public class SpringAopMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app3Context.xml");
        AopService aopService = (AopService) context.getBean("aopService");
        aopService.login();
    }
}
