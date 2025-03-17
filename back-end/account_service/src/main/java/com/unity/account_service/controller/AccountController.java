package com.unity.account_service.controller;

import com.unity.account_service.dto.AccountRequestDTO;
import com.unity.account_service.dto.BankAccountDTO;
import com.unity.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/requestAccount")
    public ResponseEntity<String> requestAccount(@RequestBody AccountRequestDTO requestDTO) {
        accountService.requestAccount(requestDTO);
        return ResponseEntity.ok("Account request submitted successfully!");
    }

    @GetMapping("/getRequests")
    public ResponseEntity<List<AccountRequestDTO>> getAllRequests() {
        return ResponseEntity.ok(accountService.getAllAccountRequests());
    }

    @PutMapping("/approveRequest")
    public ResponseEntity<String> approveAccountRequest(@RequestParam Long requestId, @RequestParam Long adminId) {
        accountService.approveAccountRequest(requestId, adminId);
        return ResponseEntity.ok("Account request approved and account created.");
    }

    @PutMapping("/rejectRequest")
    public ResponseEntity<String> rejectAccountRequest(@RequestParam Long requestId, @RequestParam Long adminId) {
        accountService.rejectAccountRequest(requestId, adminId);
        return ResponseEntity.ok("Account request rejected.");
    }

    @GetMapping("/getUserAccounts")
    public ResponseEntity<List<BankAccountDTO>> getUserAccounts(@RequestParam Long userId) {
        return ResponseEntity.ok(accountService.getUserAccounts(userId));
    }

    @PutMapping("/deactivateAccount")
    public ResponseEntity<String> deactivateAccount(@RequestParam Long accountId, @RequestParam Long adminId) {
        accountService.deactivateAccount(accountId, adminId);
        return ResponseEntity.ok("Account deactivated successfully.");
    }

    @PutMapping("/closeAccount")
    public ResponseEntity<String> closeAccount(@RequestParam Long accountId, @RequestParam Long adminId) {
        accountService.closeAccount(accountId, adminId);
        return ResponseEntity.ok("Account closed successfully.");
    }
}
