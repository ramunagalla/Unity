package com.unity.account_service.dto;

import com.unity.account_service.constants.AccountRequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDTO {

    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Account type is required")
    private String accountType;

    private AccountRequestStatus status;
}
