package com.aop;

import org.springframework.stereotype.Component;

/*
*@ClassName:AopServiceAspect
 @Description:TODO
 @Author:
 @Date:2018/9/3 16:54 
 @Version:v1.0
*/
public class AopServiceAspect {
    public void before(){
        System.out.println("after method invoke");
        System.out.println("free resource");
    }
}
