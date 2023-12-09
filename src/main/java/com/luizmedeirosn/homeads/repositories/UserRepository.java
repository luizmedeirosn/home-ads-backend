package com.luizmedeirosn.homeads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizmedeirosn.homeads.entities.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}

