package com.luizmedeirosn.homeads.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.luizmedeirosn.homeads.entities.Ad;
import com.luizmedeirosn.homeads.entities.User;
import com.luizmedeirosn.homeads.repositories.AdRepository;
import com.luizmedeirosn.homeads.repositories.UserRepository;
import com.luizmedeirosn.homeads.shared.dto.request.PostAdDTO;
import com.luizmedeirosn.homeads.shared.dto.request.UpdateAdDTO;
import com.luizmedeirosn.homeads.shared.dto.response.AdFullDTO;
import com.luizmedeirosn.homeads.shared.dto.response.AdMinDTO;
import com.luizmedeirosn.homeads.shared.exceptions.DatabaseException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public AdFullDTO save(PostAdDTO postAdDTO) {
        try {
            User user = userRepository.findById(postAdDTO.userId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID not found"));
            Ad ad = new Ad(postAdDTO);
            ad.setUser(user);

            return new AdFullDTO(adRepository.save(ad));

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
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public AdFullDTO updateById(UpdateAdDTO updateAdDTO) {
        try {
            Ad ad = adRepository.getReferenceById(updateAdDTO.id());
            ad.updateData(updateAdDTO);
            return new AdFullDTO(adRepository.save(ad));

        } catch (SQLException | IOException | RuntimeException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void deleteById(Long id) {
        Ad ad = adRepository.getReferenceById(id);
        adRepository.deleteById(ad.getId());
    }

}
