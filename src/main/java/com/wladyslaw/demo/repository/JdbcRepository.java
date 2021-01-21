package com.wladyslaw.demo.repository;

import com.wladyslaw.demo.model.Address;
import com.wladyslaw.demo.model.Role;
import com.wladyslaw.demo.model.User;
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
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt("age");

                users.add(new User(id, firstName, lastName, age, null, null));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return users;
    }

    public User getUser(int id) {
        User user = null;
        try {
            Connection connection = getConnection();
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

    public void updateUser(int id, String lastName, int age) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE user SET last_name = ?, age = ? WHERE id = ?");
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public User userRole(int id) {
        User user = null;
        Role role = new Role();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM user u LEFT OUTER JOIN role r on u.role_id = r.id WHERE u.id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                role.setId(resultSet.getInt(5));
                role.setName(resultSet.getString(7));
                user.setRole(new Role(role.getId(), role.getName()));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return user;
    }

    public User getFullUserInfo(int id) {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.id AS id, u.first_name AS firstName, " +
                    "u.last_name AS lastName, u.age AS age, r.id AS roleId, r.name AS roleName " +
                    "FROM user u LEFT OUTER JOIN role r on u.role_id = r.id WHERE u.id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getInt("age"));
                user.setRole(new Role(resultSet.getInt("roleId"), resultSet.getString("roleName")));
                user.setAddress(getAddress(id));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return user;
    }

    private List<Address> getAddress(int id) {
        List<Address> address = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM address WHERE user_id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Address adr = new Address();
                adr.setId(resultSet.getInt("id"));
                adr.setCity(resultSet.getString("city"));
                adr.setStreet(resultSet.getString("street"));
                address.add(adr);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return address;
    }

    public User getFullUserInfoV1(int id) {
        User user = new User();
        List<Address> address = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT u.id as id, u.first_name as firstName, " +
                            "u.last_name as lastName, u.age as age, r.id as roleId, r.name as roleName, " +
                            "a.id as address_id, a.city as city, a.street as street FROM user u " +
                            "LEFT JOIN role r on r.id = u.role_id LEFT JOIN address a on u.id = a.user_id " +
                            "WHERE u.id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Address adr = new Address();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getInt("age"));
                user.setRole(new Role(resultSet.getInt("roleId"),
                        resultSet.getString("roleName")));
                adr.setId(resultSet.getInt("address_id"));
                adr.setCity(resultSet.getString("city"));
                adr.setStreet(resultSet.getString("street"));
                address.add(adr);
                user.setAddress(getAddress(id));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return user;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

