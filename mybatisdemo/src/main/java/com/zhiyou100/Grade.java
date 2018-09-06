package com.zhiyou100;

import lombok.Data;

import java.util.List;

@Data
public class Grade {
    private  int id;
    private  String name;
    private List<Student> studentList;

}
