package com.luizmedeirosn.homeads.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luizmedeirosn.homeads.services.AdService;
import com.luizmedeirosn.homeads.shared.dto.request.PostAdDTO;
import com.luizmedeirosn.homeads.shared.dto.request.UpdateAdDTO;
import com.luizmedeirosn.homeads.shared.dto.response.AdFullDTO;
import com.luizmedeirosn.homeads.shared.dto.response.AdMinDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/ads")
public class AdController {

    @Autowired
    private AdService adService;

    @PostMapping
    public ResponseEntity<AdFullDTO> save(@ModelAttribute @Valid PostAdDTO postAdDTO) {
        AdFullDTO adDTO = adService.save(postAdDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(adDTO);
    }

    @GetMapping
    public ResponseEntity<List<AdMinDTO>> findAll() {
        return ResponseEntity.ok().body(adService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdFullDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(adService.findById(id));
    }

    @PutMapping
    public ResponseEntity<AdFullDTO> updateById(@RequestBody @Valid UpdateAdDTO updateAdDTO) {
        return ResponseEntity.ok().body(adService.updateById(updateAdDTO));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable Long id) {
        adService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
