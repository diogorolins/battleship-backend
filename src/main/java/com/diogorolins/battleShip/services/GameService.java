package com.diogorolins.battleShip.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.exception.ObjectNotFoundException;
import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.Ship;
import com.diogorolins.battleShip.model.dto.GameCreateDTO;
import com.diogorolins.battleShip.model.enums.StatusGame;
import com.diogorolins.battleShip.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository repository;
	
	@Autowired
	private ShipService shipService;
	

	public Game convertFromDto(GameCreateDTO objDto) {
		Game game = new Game();
		game.getPlayers().addAll(Arrays.asList(objDto.getPlayer1(), objDto.getPlayer2()));
		game.getShips().addAll(shipService.convertFromDto(objDto.getShips()));
		game.setDate(new Date());
		game.setStatus(StatusGame.INICIADO);
		return game;
	}
	
	public Game createNewGame(Player playerFrom, Player playerTo) {
		List<Player> players = new ArrayList<>();
		players.addAll(Arrays.asList(playerFrom, playerTo));
		return repository.save(new Game(null, players, StatusGame.CRIADO, new Date(), null, null));
	}

	public Game insert(Game game) {
		game = repository.save(game);
		for (Ship ship : game.getShips()) {
			ship.setGame(game);
			shipService.insert(ship);
		}
		return game;
		
	}

	public Game findById(Integer id) {
		Optional<Game> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found"));

	}

	public Game pause(Integer id) {
		Game game = findById(id);
		if(game.getStatus() == StatusGame.PAUSA) {
			game.setStatus(StatusGame.INICIADO);
		} else {
			game.setStatus(StatusGame.PAUSA);
		}		
		repository.save(game);
		return game;
	}


}
