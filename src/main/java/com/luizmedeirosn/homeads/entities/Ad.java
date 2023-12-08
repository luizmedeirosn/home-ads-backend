package com.luizmedeirosn.homeads.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.luizmedeirosn.homeads.shared.dto.request.PostAdDTO;
import com.luizmedeirosn.homeads.shared.dto.request.UpdateAdDTO;
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

    @Column(unique = true, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "average_price", nullable = false)
    private BigDecimal averagePrice;

    @Column(nullable = false)
    @Check(constraints = "score > 0 AND score <= 5")
    private Integer rating;

    @Column(nullable = false)
    private AdCategoryEnum category;
    
    @Column(name = "publication_date", nullable = false)
    @DateTimeFormat(pattern = "\"yyy-MM-dd'T'HH:mm:ss'Z'\"", iso = ISO.DATE_TIME)
    private Instant publicationDate;

    public Ad(PostAdDTO postAdDTO) {
        title = postAdDTO.title();
        description = postAdDTO.description();
        averagePrice = new BigDecimal(postAdDTO.averagePrice());
        rating = postAdDTO.rating();
        category = postAdDTO.category();
        publicationDate = postAdDTO.publicationDate();
    }

    public void updateData(UpdateAdDTO updateAdDTO) {
        title = updateAdDTO.title();
        description = updateAdDTO.description();
        averagePrice = new BigDecimal(updateAdDTO.averagePrice());
        rating = updateAdDTO.rating();
        category = updateAdDTO.category();
        publicationDate = updateAdDTO.publicationDate();
    }

}
