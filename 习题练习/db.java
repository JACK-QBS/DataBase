package com.qbs.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class db {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost；3306/test?user=root&password=123456&charEncoding=UTF-8");
            statement = connection.createStatement();
            String sql = "select * from test";
            resultSet = statement.executeQuery("sql");
            List em = new ArrayList();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }
    }
}
