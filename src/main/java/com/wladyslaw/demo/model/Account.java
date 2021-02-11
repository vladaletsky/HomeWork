package com.wladyslaw.demo.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO) // AutoIncrement
    private int id;
    private String login;
    private String email;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "role_id")
    private AccountRole accountRole;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name = "account_id")
    private List<AccountAddress> accountAddress;
}
