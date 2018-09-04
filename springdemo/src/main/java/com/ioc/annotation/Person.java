package com.ioc.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
*@ClassName:Person
 @Description:TODO
 @Author:
 @Date:2018/9/3 15:08 
 @Version:v1.0
*/
@Component
@Data
public class Person {
    @Autowired
    private Animal pet;
}
