package com.lisseth.inventory.common.application.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    @NotBlank(message = "must not be empty or null")
    private String username;

    @NotBlank(message = "must not be empty or null")
    private String password;
}
