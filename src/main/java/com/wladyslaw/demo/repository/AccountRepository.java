package com.wladyslaw.demo.repository;

import com.wladyslaw.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByLogin(String login);

//  @Query(value = "SELECT * FROM account a WHERE a.role_name LIKE %:role%", nativeQuery = true)
    List<Account> findAccountsByAccountRole_RoleName(String role);

    List<Account> findAccountByAccountAddress_City(String city);
}
