package com.ioc.xml;

/*
*@ClassName:Person
 @Description:TODO
 @Author:
 @Date:2018/9/3 9:35 
 @Version:v1.0
*/
public class Person {

    private Person friend;

    public Person getFriend() {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }

    private String name;

    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello(){
        System.out.println("hello");
    }
}
