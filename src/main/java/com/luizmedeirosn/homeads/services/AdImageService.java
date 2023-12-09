package com.luizmedeirosn.homeads.services;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luizmedeirosn.homeads.entities.AdImage;
import com.luizmedeirosn.homeads.repositories.AdImageRepository;
import com.luizmedeirosn.homeads.shared.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdImageService {

    @Autowired
    private AdImageRepository adImageRepository;

    public static String createImageLink(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/adsimages/" + id).toUriString();
    }

    public AdImage findById(Long id) {
        return adImageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image ID not found"));
    }

    public AdImage save(MultipartFile file) {
        try {
            AdImage AdImage = new AdImage();
            AdImage.setAdImageFilename(file.getOriginalFilename());
            AdImage.setImageType(file.getContentType());
            AdImage.setContent( new SerialBlob(file.getBytes() ));
            
            return adImageRepository.save(AdImage);

        } catch (IOException | RuntimeException | SQLException e) {
            throw new DatabaseException("Error in picture reading");
        }
    }

}
