package com.diogorolins.battleShip.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.exception.ObjectNotFoundException;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.dto.PlayerCreateDTO;
import com.diogorolins.battleShip.repositories.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository repository;

	public Player convertFromDto(PlayerCreateDTO objDto) {
		Player player = new Player();
		player.setName(objDto.getName());
		player.setEmail(objDto.getEmail());
		player.setPassword(player.getPassword());
		return player;
	}

	public Player insert(Player player) {
		return repository.save(player);
	}
	
	public Player getById(Integer id) {
		Optional<Player> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found"));
		
	}

	

}
