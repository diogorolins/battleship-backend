package com.diogorolins.battleShip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.battleShip.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

	public Player findByEmail(String email);
}
