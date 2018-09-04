package com.aop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
*@ClassName:UserSeriveImpl
 @Description:TODO
 @Author:
 @Date:2018/9/3 16:05 
 @Version:v1.0
*/
public class UserServiceImpl implements UserService {
    public int add(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into t_user values(100,'aop',123456)");
        int rows = ps.executeUpdate();
        /*System.out.println("log");
        System.out.println("check permission");*/
        /*System.out.println("add a user");*/
        return rows;
    }

    public int delete() {
        /*System.out.println("log");
        System.out.println("check permission");*/
        System.out.println("delete a user");
        return 0;
    }

    public int update() {
        System.out.println("update");
        return 0;
    }
}
