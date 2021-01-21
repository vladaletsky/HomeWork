package com.wladyslaw.demo.service.impl;

import com.wladyslaw.demo.model.User;
import com.wladyslaw.demo.repository.JdbcRepository;
import com.wladyslaw.demo.service.JdbcService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcServiceImpl implements JdbcService {

    private final JdbcRepository jdbcRepository;

    @Override
    public List<User> getUsers() {
        return jdbcRepository.getUsers();
    }

    @Override
    public User getUser(int id) {
        return jdbcRepository.getUser(id);
    }

    @Override
    public User updateUser(int id, String lastName, int age) {
        jdbcRepository.updateUser(id, lastName, age);
        return jdbcRepository.getUser(id);
    }

    @Override
    public User userRole(int id) {
        return jdbcRepository.userRole(id);
    }

    public User getFullUserInfo(int id) {
        return jdbcRepository.getFullUserInfo(id);
    }

    public User getFullUserInfoV1(int id) {
        return jdbcRepository.getFullUserInfoV1(id);
    }


}
