package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.AccountNotFoundException;
import com.example.exception.InvalidRegistrationDataException;
import com.example.exception.UsernameAlreadyExistsException;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account register(Account account) {
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount.isPresent()) {
            throw new UsernameAlreadyExistsException("Username alreay exists: " + account.getUsername());
        }

        if (account.getUsername().isBlank() || account.getPassword().length() < 4) {
            throw new InvalidRegistrationDataException("Invalid registration information: Username shoult not be blank, and password should be at least 4 characters long");
        }
        return accountRepository.save(account);
    }

    public Account login(String username, String password) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getPassword().equals(password)) {
                return account;
            }
            throw new AccountNotFoundException("Incorrect password for account: " + username);
        } else {
            throw new AccountNotFoundException("Account not found for username: " + username);
        }
        
    } 
}