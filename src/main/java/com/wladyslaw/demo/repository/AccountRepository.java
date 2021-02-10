package com.wladyslaw.demo.repository;

import com.wladyslaw.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByLogin(String login);
}
