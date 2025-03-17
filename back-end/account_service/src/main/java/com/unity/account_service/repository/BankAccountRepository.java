package com.unity.account_service.repository;

import com.unity.account_service.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByUserId(Long userId);

    @Query("SELECT MAX(b.accountNumber) FROM BankAccount b")
    Optional<String> findLastAccountNumber();
}
