package com.unity.account_service.entity;

import com.unity.account_service.constants.AccountRequestStatus;
import com.unity.account_service.constants.AccountType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRequestStatus status = AccountRequestStatus.PENDING;
}
