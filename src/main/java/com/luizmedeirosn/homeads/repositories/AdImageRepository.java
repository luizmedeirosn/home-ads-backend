package com.luizmedeirosn.homeads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizmedeirosn.homeads.entities.domain.AdImage;

public interface AdImageRepository extends JpaRepository<AdImage, Long>{ 
}
