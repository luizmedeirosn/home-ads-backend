package com.luizmedeirosn.homeads.shared.dto.request;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;

public record PostUserDTO(

        @NonNull @NotBlank String userName,
        @NonNull @NotBlank String password

) {
}
