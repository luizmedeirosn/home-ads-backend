package com.luizmedeirosn.homeads.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luizmedeirosn.homeads.entities.AdImage;
import com.luizmedeirosn.homeads.repositories.AdImageRepository;

@Service
public class AdImageService {

    @Autowired
    private AdImageRepository adImageRepository;

    public static String createImageLink(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/adsimages/" + id).toUriString();
    }

    public AdImage findById(Long id) {
        return adImageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Image ID not found"));
    }

}
