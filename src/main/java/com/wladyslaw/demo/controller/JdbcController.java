package com.wladyslaw.demo.controller;

import com.wladyslaw.demo.model.User;
import com.wladyslaw.demo.service.JdbcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jdbc/users/")
@RequiredArgsConstructor
public class JdbcController {
    private final JdbcService jdbcService;

    @GetMapping
    public List<User> getUsers() {
        return jdbcService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return jdbcService.getUser(id);
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestParam("lastName") String lastName, @RequestParam("age") int age) {
        return jdbcService.updateUser(id, lastName, age);
    }

    @GetMapping("/{id}/role")
    public User userRole(@PathVariable int id) {
        return jdbcService.userRole(id);
    }

    @GetMapping("/{id}/full")
    public User getFullUserInfo(@PathVariable int id) {
        return jdbcService.getFullUserInfo(id);
    }

    @GetMapping("/{id}/fullV1")
    public User getFullUserInfoV1(@PathVariable int id) {
        return jdbcService.getFullUserInfoV1(id);
    }

}
