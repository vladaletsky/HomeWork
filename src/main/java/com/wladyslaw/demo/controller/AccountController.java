package com.wladyslaw.demo.controller;

import com.wladyslaw.demo.model.Account;
import com.wladyslaw.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/jpa/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping ("/{id}")
    public Account getAccount(@PathVariable int id){
        return accountService.getAccount(id);
    }
}
