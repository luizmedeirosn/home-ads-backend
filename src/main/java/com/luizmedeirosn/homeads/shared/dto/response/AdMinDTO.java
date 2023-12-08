package com.luizmedeirosn.homeads.shared.dto.response;

import java.math.BigDecimal;

import com.luizmedeirosn.homeads.entities.Ad;
import com.luizmedeirosn.homeads.shared.enums.AdCategoryEnum;

public record AdMinDTO(
        Long id,
        String name,
        BigDecimal averagePrice,
        Integer rating,
        AdCategoryEnum category) {
    public AdMinDTO(Ad ad) {
        this(
                ad.getId(),
                ad.getTitle(),
                ad.getAveragePrice(),
                ad.getRating(),
                ad.getCategory());
    }
}