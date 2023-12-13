package com.luizmedeirosn.homeads.entities;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;

import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.luizmedeirosn.homeads.entities.enums.AdCategoryEnum;
import com.luizmedeirosn.homeads.shared.dto.request.PostAdDTO;
import com.luizmedeirosn.homeads.shared.dto.request.UpdateAdDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_ad")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

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

    @ManyToOne
    private User user;

    public Ad(PostAdDTO postAdDTO) {
        title = postAdDTO.title();
        comment = postAdDTO.comment();
        averagePrice = new BigDecimal(postAdDTO.averagePrice());
        rating = postAdDTO.rating();
        category = postAdDTO.category();
        publicationDate = Instant.parse(postAdDTO.publicationDate());
        image = new AdImage(this, postAdDTO.image());
    }

    public void updateData(UpdateAdDTO updateAdDTO) throws SQLException, IOException {
        title = updateAdDTO.title();
        comment = updateAdDTO.comment();
        averagePrice = new BigDecimal(updateAdDTO.averagePrice());
        rating = updateAdDTO.rating();
        category = updateAdDTO.category();
        publicationDate = Instant.parse(updateAdDTO.publicationDate());
        image.updateData(updateAdDTO.image());
    }

}
