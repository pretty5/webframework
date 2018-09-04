package com.ioc.annotation;

/*
*@ClassName:SingletonTest
 @Description:TODO
 @Author:
 @Date:2018/9/3 14:37 
 @Version:v1.0
*/
public class SingletonTest {
        //volatile 内存可见性：当一个线程修改由volatile修饰的字段时，其他线程时可见的。
        //保证一次写 多次读的线程安全，保证不了并发写的线程安全。
    private static volatile SingletonTest instance=null;

    private SingletonTest(){

    }

    public  static SingletonTest getInstance0(){
        if (instance==null){
            instance=new SingletonTest();
        }
        return instance;
    }
    public  static SingletonTest getInstance1(){
        //double check  双重检查 保证线程安全
        if (instance==null){
            synchronized (SingletonTest.class){
                if (instance==null){
                    instance=new SingletonTest();
                }
            }
        }
        return instance;
    }
    public static SingletonTest getInstance(){
        if (instance==null){
            instance=new SingletonTest();
        }
        return instance;
    }
}
