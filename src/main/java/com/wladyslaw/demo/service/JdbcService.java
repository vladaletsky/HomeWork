package com.wladyslaw.demo.service;

import com.wladyslaw.demo.model.User;

import java.util.List;

public interface JdbcService {
    List<User> getUsers();

    User getUser(int id);

    User updateUser(int id, String lastName, int age);

    User userRole(int id);

    User getFullUserInfo(int id);

    User getFullUserInfoV1(int id);
}
