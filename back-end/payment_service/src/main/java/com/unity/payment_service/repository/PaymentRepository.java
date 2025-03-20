package com.unity.payment_service.repository;

import com.unity.payment_service.entity.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserIdAndBankAccountId(Long userId, Long bankAccountId, Pageable pageable);
}
