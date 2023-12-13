package com.luizmedeirosn.homeads.shared.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

import com.luizmedeirosn.homeads.entities.Ad;
import com.luizmedeirosn.homeads.services.AdImageService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdFullDTO {
    private Long id;
    private String title;
    private String comment;
    private BigDecimal averagePrice;
    private Integer rating;
    private String category;
    private String imageLink;
    private Instant publicationDate;
    private Long userId;
    private String userEmail;

    public AdFullDTO(Ad ad) {
        id = ad.getId();
        title = ad.getTitle();
        comment = ad.getComment();
        averagePrice = ad.getAveragePrice();
        rating = ad.getRating();
        imageLink = AdImageService.createImageLink(ad.getId());
        publicationDate = ad.getPublicationDate();
        userId = ad.getUser().getId();
        userEmail = ad.getUser().getEmail();

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
