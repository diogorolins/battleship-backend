package com.diogorolins.battleShip.services;

import java.util.List;
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
	
	public Player findyEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public Player findById(Integer id) {
		Optional<Player> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found"));
		
	}
	
	public List<Player> findAll(){
		return repository.findAll();
	}

	public void setPlayerLogged(String email) {
		Player player = repository.findByEmail(email);
		player.setLoggged(true);
		repository.save(player);
	}

	public void setPlayerLogOff(Integer id) {
		Player player = repository.findById(id).get();
		player.setLoggged(false);
		repository.save(player);
		
	}

	public List<Player> findLoggedPlayers(String emailPlayer) {
		return repository.findLoggedPlayers(emailPlayer);
	}
	

}
