package com.wladyslaw.demo.controller;

import com.wladyslaw.demo.model.User;
import com.wladyslaw.demo.service.JdbcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/jdbc/users/")
@RequiredArgsConstructor
public class JdbcController {
    private final JdbcService jdbcService;

    @GetMapping
    public List<User> getUsers(){
        return jdbcService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return jdbcService.getUser(id); }
}
