package com.company;

import User.student;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 查询“计算机系2019级1班”所有同学的成绩，
// 要求 显示班级信息，学生信息，课程信息，分数

public class TestDataSourceUpdate {

    public static void main(String[] args) {
        update("计算机系2019级1班");
    }

    // 模拟文本框输入班级名称，查询信息
    // 实现一个方法，参数为传入的班级名称，返回类型为 List<stu>
    public static int update(String name) {
        // write your code here
        Connection connection = null;
        PreparedStatement statement = null;

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
            String sql = "update emp set salary = salary + 1";

            // preparedStatement 预编译的操作命令对象 ： 注意使用 String sql 传入参数
            statement = connection.prepareStatement(sql);// 发送sql，让数据库预编译（语法分析、执行顺序、优化）
            // 预编译以后不需要再传 sql 进去了
            // 插入、修改、删除 都是 executeUpdate 方法，返回值都是 int
            int r = statement.executeUpdate();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            // 执行某个功能，如果出现异常，建议再次抛出异常
            throw new RuntimeException("修改员工信息出错了",e);
        } finally {
            // 5、释放资源
            try {
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
