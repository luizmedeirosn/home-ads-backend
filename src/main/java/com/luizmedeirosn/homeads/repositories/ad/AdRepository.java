package com.luizmedeirosn.homeads.repositories.ad;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizmedeirosn.homeads.entities.Ad;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
