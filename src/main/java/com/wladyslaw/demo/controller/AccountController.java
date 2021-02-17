package com.wladyslaw.demo.controller;

import com.wladyslaw.demo.model.Account;
import com.wladyslaw.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping ("/{id}")
    public Account updateAccount(@PathVariable int id){
        return accountService.getAccount(id);
    }

    @GetMapping ("/role/{role}")
    private List<Account> findAccountByAccountRole(@PathVariable String role){
        return accountService.findAccountByAccountRole(role);
    }

    @GetMapping ("/address/{city}")
    private List<Account> findByAddress(@PathVariable String city){
        return accountService.findByAddress(city);
    }
}
