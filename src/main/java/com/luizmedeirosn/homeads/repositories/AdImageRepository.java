package com.luizmedeirosn.homeads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizmedeirosn.homeads.entities.AdImage;

@Repository
public interface AdImageRepository extends JpaRepository<AdImage, Long> {
}
