package com.unity.account_service.repository;

import com.unity.account_service.entity.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
}
