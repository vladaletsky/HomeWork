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


}
