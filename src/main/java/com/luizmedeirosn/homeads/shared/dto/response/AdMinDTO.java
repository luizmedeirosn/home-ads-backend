package com.luizmedeirosn.homeads.shared.dto.response;

import java.math.BigDecimal;

import com.luizmedeirosn.homeads.entities.Ad;
import com.luizmedeirosn.homeads.services.AdImageService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AdMinDTO {
    private Long id;
    private String title;
    private BigDecimal averagePrice;
    private Integer rating;
    private String category;
    private String imageLink;

    public AdMinDTO(Ad ad) {
        id = ad.getId();
        title = ad.getTitle();
        averagePrice = ad.getAveragePrice();
        rating = ad.getRating();
        imageLink = AdImageService.createImageLink(ad.getId());

        String selectedCategory = "";
        switch (ad.getCategory().getCode()) {
            case 1:
                selectedCategory = "Cama, mesa e banho";
                break;
            case 2:
                selectedCategory = "Eletrodomésticos";
                break;
            case 3:
                selectedCategory = "Móveis";
                break;
            case 4:
                selectedCategory = "Ferramentas";
                break;
            default:
                break;
        }

        this.category = selectedCategory;
    }
}