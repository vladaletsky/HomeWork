package com.wladyslaw.demo.repository;

import com.wladyslaw.demo.model.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class JdbcRepository {
    private final static String URL = "jdbc:mysql://localhost:3306/demo";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "12131415";

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt("age");

                users.add(new User(id, firstName, lastName, age));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return users;
    }

    public User getUser(int id) {
        User user = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet2 = preparedStatement.executeQuery();

            while (resultSet2.next()) {
                user = new User();
                user.setId(resultSet2.getInt("id"));
                user.setFirstName(resultSet2.getString(2));
                user.setLastName(resultSet2.getString(3));
                user.setAge(resultSet2.getInt(4));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());

        }
        return user;
    }

    public User updateUser(int id, String lastName, int age) {
        User user = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE user SET last_name = ?, age = ? WHERE id = ?");
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, id);
            int resultSet3 = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return user;
    }
}

