package com.luizmedeirosn.homeads.shared.dto.request;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupDTO(

        @NotBlank @NonNull String username,
        @Email String email,
        @NotNull @NotBlank String password

) {
}
