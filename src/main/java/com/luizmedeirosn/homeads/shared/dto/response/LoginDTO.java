package com.luizmedeirosn.homeads.shared.dto.response;

import com.luizmedeirosn.homeads.entities.enums.UserRole;

public record LoginDTO(

        Long USER_ID,
        UserRole USER_ROLE,
        String JWT_TOKEN) {
}
