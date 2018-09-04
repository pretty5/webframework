package com.aop;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
*@ClassName:Main
 @Description:TODO
 @Author:
 @Date:2018/9/3 16:09 
 @Version:v1.0
*/
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ClassLoader loader = Main.class.getClassLoader();
        Class[] interfaces = {UserService.class};
        UserServiceImpl proxied = new UserServiceImpl();


        UserService proxy = (UserService) Proxy.newProxyInstance(loader, interfaces, new UserServiceHandler(proxied));

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coredb", "root", "123456");

        proxy.add(connection);

       /* proxy.delete();

        proxy.update();*/

    }
}
