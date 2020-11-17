package com.company;

import User.student;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 查询“计算机系2019级1班”所有同学的成绩，
// 要求 显示班级信息，学生信息，课程信息，分数

public class TestDataSource {

    public static void main(String[] args) {
        List<student> list = query("计算机系2019级1班");
    }

    // 模拟文本框输入班级名称，查询信息
    // 实现一个方法，参数为传入的班级名称，返回类型为 List<stu>
    public static List<student> query(String name) {
        // write your code here
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // JDBC操作的第一步，创建连接
            // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法区，并执行该类的静态方法块、静态属性。
            // Class.forName("com.mysql.jdbc.Driver");

            // 1、创建数据库连接
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUrl("jdbc:mysql://localhost:3306/test? user=root&password=1213456&useUnicode=true&characterEncoding=UTF-8");
            connection = ds.getConnection();

            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test? user=root&password=1213456&useUnicode=true&characterEncoding=UTF-8");
            System.out.println(connection);

            // 2、创建操作命令对象 statement
            // statement = connection.createStatement(); // 创建简单的操作命令对象


            // 3、执行 sql
            String sql = "select cls.name,stu.sn,stu.name,stu.qq_mail,cou.name,sco.score " +
                    "from classes cls,student stu,course cou,score sco " +
                    "where cls.id = stu.classes_id " +
                    "and stu.id = sco.student_id " +
                    "and cou.id = sco.course_id " +
                    "and cls.name =?"; // ? 为占位符
//                    name+
//                    " ‘";
            // preparedStatement 预编译的操作命令对象 ： 注意使用 String sql 传入参数
            statement = connection.prepareStatement(sql);// 发送sql，让数据库预编译（语法分析、执行顺序、优化）
            // 替换占位符：指定占位符的位置（从1开始，数据类型）
            statement.setString(1,name);
            // 预编译以后不需要再传 sql 进去了
            resultSet = statement.executeQuery(); 


            List<student> list = new ArrayList<>();
            // 4、处理结果集 ResultSet（查询操作） ：类似 List < Map < String ，字段类型 >> 结构
            while (resultSet.next()) { //下行是否有数据
                int sn = resultSet.getInt("sn");
                String studentName = resultSet.getString("student_Name");
                String classesName = resultSet.getString("classes_Name");
                String qqMail = resultSet.getString("qq_mail");
                String courseName = resultSet.getString("course_name");
                BigDecimal score = resultSet.getBigDecimal("score");
                System.out.printf("sn=%s,studentName=%s,classesName=%s,qqMail=%s,courseName=%s,score=%s%n",  sn, studentName, classesName, qqMail,courseName,score);

                // 实例化一个对象
                student stu = new student();
                stu.setClassesName(classesName);
                stu.setSn(sn);
                stu.setStudentName(studentName);
                stu.setQqMail(qqMail);
                stu.setCourseName(courseName);
                stu.setScore(score);

                list.add(stu);
            }
            System.out.println(list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            // 执行某个功能，如果出现异常，建议再次抛出异常
            throw new RuntimeException("查询班级信息出错了",e);
        } finally {
            // 5、释放资源
            try {
                // 关闭结果集：
                if (resultSet != null)
                    resultSet.close();
                // 关闭命令：
                if (statement != null)
                    statement.close();
                // 关闭连接命令：
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
