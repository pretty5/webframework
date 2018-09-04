package com.aop;

import java.sql.Connection;
import java.sql.SQLException;

/*
*@ClassName:UserService
 @Description:TODO
 @Author:
 @Date:2018/9/3 16:04 
 @Version:v1.0
*/
public interface UserService {
    int add(Connection conn) throws SQLException;
    int delete();
    int update();
}
