package com.luizmedeirosn.homeads.services.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.luizmedeirosn.homeads.entities.Ad;
import com.luizmedeirosn.homeads.repositories.ad.AdRepository;
import com.luizmedeirosn.homeads.shared.dto.request.PostAdDTO;
import com.luizmedeirosn.homeads.shared.dto.request.UpdateAdDTO;
import com.luizmedeirosn.homeads.shared.dto.response.AdFullDTO;
import com.luizmedeirosn.homeads.shared.dto.response.AdMinDTO;
import com.luizmedeirosn.homeads.shared.exceptions.DatabaseException;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public AdFullDTO save(PostAdDTO postAdDTO) {
        try {
            return new AdFullDTO(adRepository.save(new Ad(postAdDTO)));
        } catch (RuntimeException e) {
            throw new DatabaseException("Constraint violation error");
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<AdMinDTO> findAll() {
        return adRepository.findAll().stream().map(AdMinDTO::new).toList();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public AdFullDTO findById(Long id) {
        return new AdFullDTO(adRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        ));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public AdFullDTO updateById(UpdateAdDTO updateAdDTO) {
        Ad ad = adRepository.findById(updateAdDTO.id()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        try {
            ad.updateData(updateAdDTO);
            return new AdFullDTO(adRepository.save(ad));
        } catch (RuntimeException e) {
            throw new DatabaseException("Constraint violation error");
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void deleteById(Long id) {
        Ad ad = adRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        adRepository.deleteById(ad.getId());
    }

}
