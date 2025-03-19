package com.unity.account_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBalanceRequestDTO {

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @NotNull(message = "Transaction amount is required")
    @Min(value = 0, message = "Amount must be greater than zero")
    private double amount;
}
