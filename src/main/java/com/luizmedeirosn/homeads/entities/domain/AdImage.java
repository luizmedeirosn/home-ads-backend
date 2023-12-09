package com.luizmedeirosn.homeads.entities.domain;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.web.multipart.MultipartFile;

import com.luizmedeirosn.homeads.shared.exceptions.DatabaseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_ad_image")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ad_image_filename", unique = true, nullable = false)
    private String adImageFilename;

    @Column(name = "image_type", nullable = false)
    private String imageType;

    @Lob
    @Column(nullable = false)
    private Blob content;

    @OneToOne
    @MapsId
    private Ad ad;

    public AdImage(Ad ad, MultipartFile image) {
        try {
            adImageFilename = image.getOriginalFilename();
            imageType = image.getContentType();
            content = new SerialBlob(image.getBytes());
            this.ad = ad;
        } catch (SQLException | IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
