package com.zhiyou100;

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
public class MybatisDemo {
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
       //testDemo();
        //listdemo();
        //update();

          // testManyToOne();
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
        GradeDao gradeDao = session.getMapper(GradeDao.class);
        Grade gradeDaoById = gradeDao.findById(1);
        System.out.println(gradeDaoById);
    }

    private static void testManyToOne() throws IOException {
        String resource = "mybatis-config.xml";
        //创建一个sqlsessionFactory对象  用来获取sqlsession
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //打开一个会话
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper mapper 是dao的实现
        StudentDao studentDao = session.getMapper(StudentDao.class);
        Student student = studentDao.findById(1);
        System.out.println(student);
    }

    private static void update() throws IOException {
        String resource = "mybatis-config.xml";
        //创建一个sqlsessionFactory对象  用来获取sqlsession
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //打开一个会话
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper mapper 是dao的实现
        PersonDao personDao = session.getMapper(PersonDao.class);
        personDao.update(new Person(1,"laowang"));
        session.commit();
        Person byId = personDao.findById(1);
        System.out.println(byId);
    }

    private static void listdemo() throws IOException {
        String resource = "mybatis-config.xml";
        //创建一个sqlsessionFactory对象  用来获取sqlsession
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //打开一个会话
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper mapper 是dao的实现
        PersonDao personDao = session.getMapper(PersonDao.class);
        List<Person> personList = personDao.listfindId(1);
        System.out.println(personList);
    }

    private static void testDemo() throws IOException {

        //mybatis配置文件路径
        String resource = "mybatis-config.xml";
        //创建一个sqlsessionFactory对象  用来获取sqlsession
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //打开一个会话
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper mapper 是dao的实现
        PersonDao personDao = session.getMapper(PersonDao.class);

        personDao.insert(new Person(2018,"jerry"));

        session.commit();
        Person person = personDao.findById(2018);
        System.out.println(person);

        Person idToMap = personDao.findByIdToMap(1);
        System.out.println(idToMap);

    }
}
