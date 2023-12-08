package com.luizmedeirosn.homeads.services.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luizmedeirosn.homeads.entities.Ad;
import com.luizmedeirosn.homeads.repositories.ad.AdRepository;
import com.luizmedeirosn.homeads.shared.dto.request.PostAdDTO;
import com.luizmedeirosn.homeads.shared.dto.response.AdMinDTO;
import com.luizmedeirosn.homeads.shared.exceptions.DatabaseException;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<AdMinDTO> findAll() {
        return adRepository.findAll().stream().map(AdMinDTO::new).toList();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public AdMinDTO save(PostAdDTO postAdDTO) {
        try {
            return new AdMinDTO(adRepository.save(new Ad(postAdDTO)));
        } catch (RuntimeException e) {
            throw new DatabaseException("Constraint violation error");
        }
    }

}
