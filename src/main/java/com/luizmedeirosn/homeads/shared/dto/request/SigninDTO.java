package com.luizmedeirosn.homeads.shared.dto.request;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SigninDTO(

        @Email String email,
        @NonNull @NotBlank String password

) {

}
