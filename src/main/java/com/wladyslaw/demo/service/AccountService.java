package com.wladyslaw.demo.service;

import com.wladyslaw.demo.model.Account;

import java.util.List;

public interface AccountService {
    Account getAccount(int id);
    List<Account> getAllAccounts();
}
