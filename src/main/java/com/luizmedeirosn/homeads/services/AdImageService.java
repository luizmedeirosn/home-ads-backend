package com.luizmedeirosn.homeads.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luizmedeirosn.homeads.entities.AdImage;
import com.luizmedeirosn.homeads.repositories.AdImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdImageService {

    private final AdImageRepository adImageRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public static String createImageLink(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("api//adsimages/" + id).toUriString();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public AdImage findById(Long id) {
        return adImageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image ID not found"));
    }

}
