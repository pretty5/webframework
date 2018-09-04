package com.aop;

import org.springframework.stereotype.Service;

/*
*@ClassName:AopService
 @Description:TODO
 @Author:
 @Date:2018/9/3 16:52 
 @Version:v1.0
*/
@Service
public class AopService {
    public void login(){
        System.out.println("do login");
    }
}
