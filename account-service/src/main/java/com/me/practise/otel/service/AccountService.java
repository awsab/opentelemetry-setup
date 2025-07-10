/**
 * Author   : Prabakaran Ramu
 * User     : ramup
 * Date     : 10/07/2025
 * Usage    :
 * Since    : Version 1.0
 */
package com.me.practise.otel.service;

import com.me.practise.otel.dto.AccountRequest;
import com.me.practise.otel.dto.AccountResponse;
import com.me.practise.otel.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    public static final List<Account> accounts = new ArrayList<> (List.of(
            new Account(1L, "1234567890", "AAAAA", "Current", 1000.00, "INR", "ACTIVE"),
            new Account(2L, "0987654321", "BBBBB", "Savings", 2000.00, "INR", "ACTIVE"),
            new Account(3L, "1122334455", "CCCCC", "Fixed Deposit", 3000.00, "INR", "ACTIVE"),
            new Account(4L, "2233445566", "DDDDD", "Recurring Deposit", 1500.00, "INR", "ACTIVE"),
            new Account(5L, "3344556677", "EEEEE", "Current", 2500.00, "INR", "ACTIVE"),
            new Account(6L, "4455667788", "FFFFF", "Savings", 1800.00, "INR", "ACTIVE"),
            new Account(7L, "5566778899", "GGGGG", "Fixed Deposit", 3500.00, "INR", "ACTIVE"),
            new Account(8L, "6677889900", "HHHHH", "Recurring Deposit", 1200.00, "INR", "ACTIVE"),
            new Account(9L, "7788990011", "IIIII", "Current", 4000.00, "INR", "ACTIVE"),
            new Account(10L, "8899001122", "JJJJJ", "Savings", 2200.00, "INR", "ACTIVE")
    ));

    public AccountResponse createAccount(AccountRequest accountRequest){
        log.info("Creating account with request: {}", accountRequest);
        Account newAccount = new Account(
                (long) (accounts.size() + 1),
                accountRequest.getAccountNumber(),
                accountRequest.getAccountHolderName(),
                accountRequest.getAccountType(),
                accountRequest.getBalance(),
                accountRequest.getCurrency(),
                "ACTIVE"
        );
        accounts.add(newAccount);
        return AccountResponse.builder()
                .accountId(newAccount.getAccountId())
                .accountNumber(newAccount.getAccountNumber())
                .accountHolderName(newAccount.getAccountHolderName())
                .accountType(newAccount.getAccountType())
                .balance(newAccount.getBalance())
                .currency(newAccount.getCurrency())
                .status(newAccount.getStatus())
                .build();
    }

    public AccountResponse getAccountByAccountId(Long accountId){
        log.info("Fetching account with ID: {}", accountId);
        return accounts.stream()
                .filter(account -> account.getAccountId().equals(accountId))
                .findFirst()
                .map(account -> AccountResponse.builder()
                        .accountId(account.getAccountId())
                        .accountNumber(account.getAccountNumber())
                        .accountHolderName(account.getAccountHolderName())
                        .accountType(account.getAccountType())
                        .balance(account.getBalance())
                        .currency(account.getCurrency())
                        .status(account.getStatus())
                        .build())
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<AccountResponse> getAllAccounts() {
        log.info("Fetching all accounts");
        return accounts.stream()
                .map(account -> AccountResponse.builder()
                        .accountId(account.getAccountId())
                        .accountNumber(account.getAccountNumber())
                        .accountHolderName(account.getAccountHolderName())
                        .accountType(account.getAccountType())
                        .balance(account.getBalance())
                        .currency(account.getCurrency())
                        .status(account.getStatus())
                        .build())
                .toList();
    }
}
