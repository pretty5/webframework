package com.homework;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private  int id;
    private  String name;
    private List<Permission> permission;
}
