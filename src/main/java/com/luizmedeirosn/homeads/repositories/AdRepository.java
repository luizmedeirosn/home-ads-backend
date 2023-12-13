package com.luizmedeirosn.homeads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizmedeirosn.homeads.entities.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
}
