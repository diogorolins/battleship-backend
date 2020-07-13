package com.diogorolins.battleShip.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.diogorolins.battleShip.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

	public Optional<Player> findByEmail(String email);
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Player obj "
	+ "WHERE obj.loggged = true AND obj.email != :email")
	public List<Player> findLoggedPlayers(@Param("email") String email);
}
