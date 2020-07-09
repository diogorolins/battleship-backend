package com.diogorolins.battleShip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.battleShip.model.Strike;

public interface StrikeRepository extends JpaRepository<Strike, Integer> {

}
