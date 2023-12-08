package com.luizmedeirosn.homeads.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.lang.NonNull;

import com.luizmedeirosn.homeads.shared.dto.request.PostAdDTO;
import com.luizmedeirosn.homeads.shared.enums.AdCategoryEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_ad")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String title;

    @NonNull
    @Column(columnDefinition = "TEXT", unique = true)
    private String description;

    @NonNull
    @Column(name = "average_price")
    private BigDecimal averagePrice;

    @NonNull
    private Integer rating;

    @NonNull
    private AdCategoryEnum category;
    
    @NonNull
    @Column(name = "publication_date")
    private Instant publicationDate;

    public Ad(PostAdDTO postAdDTO) {
        title = postAdDTO.title();
        description = postAdDTO.description();
        averagePrice = new BigDecimal(postAdDTO.averagePrice());
        rating = postAdDTO.rating();
        category = postAdDTO.category();
        publicationDate = postAdDTO.publicationDate();
    }

}
