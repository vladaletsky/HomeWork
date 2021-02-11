package com.wladyslaw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class AccountRole {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String roleName;

    @JsonIgnore
    @OneToOne (mappedBy = "accountRole", cascade = CascadeType.ALL)
    private Account account;
}
