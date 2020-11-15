package com.company;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // write your code here
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // JDBC操作的第一步，创建连接
            // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法区，并执行该类的静态方法块、静态属性。
            Class.forName("com.mysql.jdbc.Driver");
            // 1、创建数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test? user=root&password=1213456&useUnicode=true&characterEncoding=UTF-8");
            System.out.println(connection);

            // 2、创建操作命令对象 statement
            statement = connection.createStatement();

            // 3、执行 sql
            String sql = "select id,name,role,salary from emp";
            resultSet = statement.executeQuery("sql");

            List<Emp> empList = new ArrayList<>();
            // 4、处理结果集 ResultSet（查询操作） ：类似 List < Map < String ，字段类型 >> 结构
            while (resultSet.next()) { //下行是否有数据
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                System.out.printf("id=%s,name=%s,role=%s,salary=%s%n",  id, name, role, salary);

                // 实例化一个对象
                Emp emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setRole(role);
                emp.setSalary(salary);
                empList.add(emp);
            }
            System.out.println(empList);
        } catch (Exception e) {
            e.printStackTrace();
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

        // 构造一个内部类
        private static class Emp {
            private Integer id;
            private String name;
            private String role;
            private BigDecimal salary;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public BigDecimal getSalary() {
                return salary;
            }

            public void setSalary(BigDecimal salary) {
                this.salary = salary;
            }

            @Override
            public String toString() {
                return "Emp{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", role='" + role + '\'' +
                        ", salary=" + salary +
                        '}';
            }
        }

}


