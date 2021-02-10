package com.wladyslaw.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO) // AutoIncrement
    private int id;
    private String login;
    private String email;
}
