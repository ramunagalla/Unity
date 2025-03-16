package com.unity.account_service.entity;

import com.unity.account_service.constants.AccountRequestStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String accountType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRequestStatus status = AccountRequestStatus.PENDING;
}
