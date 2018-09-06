package com.zhiyou100;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
*@ClassName:Person
 @Description:TODO
 @Author:
 @Date:2018/9/4 16:37 
 @Version:v1.0
*/
@Data

public class Person {
    private int id;
    private String username;

    public Person(int id, String username) {
        this.id = id;
        this.username = username;
    }
}
