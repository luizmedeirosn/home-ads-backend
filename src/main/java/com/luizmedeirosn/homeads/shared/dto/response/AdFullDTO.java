package com.luizmedeirosn.homeads.shared.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

import com.luizmedeirosn.homeads.entities.Ad;
import com.luizmedeirosn.homeads.shared.enums.AdCategoryEnum;

public record AdFullDTO(
        Long id,
        String title,
        String adDescription,
        BigDecimal averagePrice,
        Integer rating,
        AdCategoryEnum category,
        Instant publicationDate) {

    public AdFullDTO(Ad ad) {
        this(
                ad.getId(),
                ad.getTitle(),
                ad.getAdDescription(),
                ad.getAveragePrice(),
                ad.getRating(),
                ad.getCategory(),
                ad.getPublicationDate());
    }

}
