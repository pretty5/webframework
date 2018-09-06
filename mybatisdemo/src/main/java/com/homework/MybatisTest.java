package com.homework;

import com.zhiyou100.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*
*@ClassName:MybatisDemo
 @Description:TODO
 @Author:
 @Date:2018/9/4 16:23 
 @Version:v1.0
*/
public class MybatisTest {
    public static SqlSessionFactory build;
    static {
        String resource="mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        build = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static void main(String[] args) throws IOException {



        testOneToMany();

    }

    private static void testOneToMany() throws IOException {
        String resource = "mybatis-config.xml";
        //创建一个sqlsessionFactory对象  用来获取sqlsession
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //打开一个会话
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper mapper 是dao的实现
        RoleDao roleDao = session.getMapper(RoleDao.class);
        Role id = roleDao.findById(1);
        System.out.println(id);
    }




}
