package com.luizmedeirosn.homeads.controllers.ad;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luizmedeirosn.homeads.services.ad.AdService;
import com.luizmedeirosn.homeads.shared.dto.request.PostAdDTO;
import com.luizmedeirosn.homeads.shared.dto.response.AdMinDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/ads")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping
    public ResponseEntity<List<AdMinDTO>> findAll() {
        return ResponseEntity.ok().body(adService.findAll());
    }

    @PostMapping
    public ResponseEntity<AdMinDTO> save(@RequestBody @Valid PostAdDTO postAdDTO) {
        AdMinDTO adDTO = adService.save(postAdDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(adDTO);
    }

}
