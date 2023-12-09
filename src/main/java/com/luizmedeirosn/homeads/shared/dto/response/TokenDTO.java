package com.luizmedeirosn.homeads.shared.dto.response;

import java.time.Instant;

public record TokenDTO(

    Long id,
    String userName,
    Boolean authenticated,
    Instant creationDate,
    Instant expirationDate,
    String accessToken,
    String refreshToken

) {
}
