package com.wladyslaw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class AccountAddress {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String city;
    private String street;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.ALL)
    private Account account;
}
