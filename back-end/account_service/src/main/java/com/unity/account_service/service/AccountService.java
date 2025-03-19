package com.unity.account_service.service;

import com.unity.account_service.dto.AccountRequestDTO;
import com.unity.account_service.dto.BankAccountDTO;

import java.util.List;

public interface AccountService {
    void requestAccount(AccountRequestDTO requestDTO);
    List<AccountRequestDTO> getAllAccountRequests();
    void approveAccountRequest(Long requestId, Long adminId);
    void rejectAccountRequest(Long requestId, Long adminId);
    List<BankAccountDTO> getUserAccounts(Long userId);
    void deactivateAccount(Long accountId, Long adminId);
    void closeAccount(Long accountId, Long adminId);
    void updateAccountBalance(Long accountId, double amount);
    double getAccountBalance(Long accountId);
}
