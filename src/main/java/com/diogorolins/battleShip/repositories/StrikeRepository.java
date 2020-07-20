package com.diogorolins.battleShip.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Strike;

public interface StrikeRepository extends JpaRepository<Strike, Integer> {

	public List<Strike> findByGame(Game game);
}
