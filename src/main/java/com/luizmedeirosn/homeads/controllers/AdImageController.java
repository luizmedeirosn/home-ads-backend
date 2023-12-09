package com.luizmedeirosn.homeads.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmedeirosn.homeads.entities.domain.AdImage;
import com.luizmedeirosn.homeads.services.AdImageService;
import com.luizmedeirosn.homeads.shared.exceptions.DatabaseException;


@RestController
@RequestMapping(value = "/adsimages")
public class AdImageController {
    
    @Autowired
    private AdImageService adImageService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ByteArrayResource> findById(@PathVariable Long id) {
        AdImage adImage = adImageService.findById(id);
        ByteArrayResource body;
        try {
            body = new ByteArrayResource(adImage.getContent().getBytes(1, (int)adImage.getContent().length()));
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, adImage.getImageType()).body(body);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
