package com.wladyslaw.demo.service.impl;

import com.wladyslaw.demo.model.Account;
import com.wladyslaw.demo.repository.AccountRepository;
import com.wladyslaw.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account getAccount(int id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
