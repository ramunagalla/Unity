package com.unity.account_service.service.impl;

import com.unity.account_service.client.UserServiceClient;
import com.unity.account_service.constants.AccountRequestStatus;
import com.unity.account_service.constants.AccountStatus;
import com.unity.account_service.constants.Role;
import com.unity.account_service.dto.AccountRequestDTO;
import com.unity.account_service.dto.BankAccountDTO;
import com.unity.account_service.dto.UserDTO;
import com.unity.account_service.entity.AccountRequest;
import com.unity.account_service.entity.BankAccount;
import com.unity.account_service.exception.AccountException;
import com.unity.account_service.mapper.AccountRequestMapper;
import com.unity.account_service.mapper.BankAccountMapper;
import com.unity.account_service.repository.AccountRequestRepository;
import com.unity.account_service.repository.BankAccountRepository;
import com.unity.account_service.service.AccountService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRequestRepository accountRequestRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserServiceClient userServiceClient; // Using Feign Client

    private static final long START_ACCOUNT_NUMBER = 100000000001L;

    @Override
    public void requestAccount(AccountRequestDTO requestDTO) {
        requestDTO.setStatus(AccountRequestStatus.PENDING);
        AccountRequest accountRequest = AccountRequestMapper.toEntity(requestDTO);
        accountRequestRepository.save(accountRequest);
    }

    @Override
    public List<AccountRequestDTO> getAllAccountRequests() {
        return accountRequestRepository.findAll().stream()
                .map(AccountRequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void approveAccountRequest(Long requestId, Long adminId) {
        validateAdmin(adminId);

        AccountRequest request = accountRequestRepository.findById(requestId)
                .orElseThrow(() -> new AccountException("Request not found"));

        request.setStatus(AccountRequestStatus.APPROVED);
        accountRequestRepository.save(request);

        String newAccountNumber = generateNewAccountNumber();
        BankAccount newAccount = new BankAccount();
        newAccount.setUserId(request.getUserId());
        newAccount.setAccountType(request.getAccountType());
        newAccount.setAccountNumber(newAccountNumber);
        newAccount.setStatus(AccountStatus.ACTIVE);
        bankAccountRepository.save(newAccount);
    }

    @Override
    public void rejectAccountRequest(Long requestId, Long adminId) {
        validateAdmin(adminId);
        AccountRequest request = accountRequestRepository.findById(requestId)
                .orElseThrow(() -> new AccountException("Request not found"));
        request.setStatus(AccountRequestStatus.REJECTED);
        accountRequestRepository.save(request);
    }

    @Override
    public List<BankAccountDTO> getUserAccounts(Long userId) {
        return bankAccountRepository.findByUserId(userId).stream()
                .map(BankAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateAccount(Long accountId, Long adminId) {
        validateAdmin(adminId);
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
        account.setStatus(AccountStatus.SUSPENDED);
        bankAccountRepository.save(account);
    }

    @Override
    public void closeAccount(Long accountId, Long adminId) {
        validateAdmin(adminId);
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
        account.setStatus(AccountStatus.CLOSED);
        bankAccountRepository.save(account);
    }

    private void validateAdmin(Long adminId) {
        UserDTO user = userServiceClient.getUserById(adminId);
        if (user == null || !user.getRole().equals(Role.ADMIN)) {
            throw new AccountException("Unauthorized action. Admin access required.");
        }
    }

    private String generateNewAccountNumber() {
        Optional<String> lastAccount = bankAccountRepository.findLastAccountNumber();
        long newNumber = lastAccount.map(Long::parseLong).orElse(START_ACCOUNT_NUMBER - 1) + 1;
        return String.valueOf(newNumber);
    }

    @Transactional
    @Override
    public void updateAccountBalance(Long accountId, double amount) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Bank account not found"));

        double currentBalance = account.getBalance();
        if (amount < 0 && currentBalance < Math.abs(amount)) {
            throw new AccountException("Insufficient balance");
        }

        account.setBalance(currentBalance + amount);
        bankAccountRepository.save(account);
    }

    @Override
    public double getAccountBalance(Long accountId) {
        return bankAccountRepository.findById(accountId)
                .map(BankAccount::getBalance)
                .orElseThrow(() -> new AccountException("Bank account not found"));
    }
}
