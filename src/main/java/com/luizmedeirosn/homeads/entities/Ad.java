package com.luizmedeirosn.homeads.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.luizmedeirosn.homeads.shared.enums.AdCategoryEnum;

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

    private String name;
    private String description;
    private BigDecimal price;
    private Integer rating;
    private AdCategoryEnum category;
    private Instant publicationDate;

}
