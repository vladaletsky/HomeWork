package com.wladyslaw.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Role role;
    private List<Address> address;
}
