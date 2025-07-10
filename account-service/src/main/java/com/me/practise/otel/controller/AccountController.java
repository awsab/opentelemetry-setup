/**
 * Author   : Prabakaran Ramu
 * User     : ramup
 * Date     : 10/07/2025
 * Usage    :
 * Since    : Version 1.0
 */
package com.me.practise.otel.controller;

import com.me.practise.otel.dto.AccountRequest;
import com.me.practise.otel.dto.AccountResponse;
import com.me.practise.otel.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(AccountRequest accountRequest) {
        log.info("Creating account with request: {}", accountRequest);
        return ResponseEntity.ok(accountService.createAccount(accountRequest));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccountByAccountId(@PathVariable Long accountId) {
        log.info("Fetching account with ID: {}", accountId);
        return ResponseEntity.ok(accountService.getAccountByAccountId(accountId));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<AccountResponse>> getAllAccounts() {
        log.info("Fetching all accounts");
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

}
