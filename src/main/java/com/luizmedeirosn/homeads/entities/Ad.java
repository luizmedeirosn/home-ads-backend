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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_ad")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(name = "ad_description", columnDefinition = "TEXT", nullable = false)
    private String adDescription;

    @Column(name = "average_price", nullable = false)
    private BigDecimal averagePrice;

    @Column(nullable = false)
    @Check(constraints = "score > 0 AND score <= 5")
    private Integer rating;

    @Column(nullable = false)
    private AdCategoryEnum category;
    
    @Column(name = "publication_date", nullable = false)
    @DateTimeFormat(pattern = "\"yyyy-MM-dd'T'HH:mm:ss'Z'\"", iso = ISO.DATE_TIME)
    private Instant publicationDate;

    @OneToOne(mappedBy = "ad", cascade = CascadeType.ALL)
    private AdImage image;

    public Ad(PostAdDTO postAdDTO) {
        title = postAdDTO.title();
        adDescription = postAdDTO.adDescription();
        averagePrice = new BigDecimal(postAdDTO.averagePrice());
        rating = postAdDTO.rating();
        category = postAdDTO.category();
        publicationDate = postAdDTO.publicationDate();
    }

    public void updateData(UpdateAdDTO updateAdDTO) {
        title = updateAdDTO.title();
        adDescription = updateAdDTO.adDescription();
        averagePrice = new BigDecimal(updateAdDTO.averagePrice());
        rating = updateAdDTO.rating();
        category = updateAdDTO.category();
        publicationDate = updateAdDTO.publicationDate();
    }

}
