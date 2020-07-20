package com.diogorolins.battleShip.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.exception.GameStartedExeption;
import com.diogorolins.battleShip.exception.ObjectNotFoundException;
import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.Ship;
import com.diogorolins.battleShip.model.dto.ShipInsertDTO;
import com.diogorolins.battleShip.model.enums.StatusGame;
import com.diogorolins.battleShip.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository repository;
	
	@Autowired
	private ShipService shipService;
	
	@Autowired
	private PlayerService playerService;
	

	public Game insertShips(List<ShipInsertDTO> shipsDTO, Integer id, Integer playerId) {
		Game game = findById(id);	
		if(playerDontBelongToGame(game, playerId)){
			throw new ObjectNotFoundException("Player não está no jogo.");
		}
		
		if(game.getStatus() == StatusGame.STARTED) {
			throw new GameStartedExeption("Jogo em andamento.");
		}
		
		if(haveGameReadyToStart(game)) {
			Player player = playerService.findById(playerId);
			game.setPlayerTurn(player);
			game.setStatus(StatusGame.STARTED);
		}
		
		game.getShips().addAll(shipService.convertFromDto(shipsDTO));
		
		for (Ship ship : game.getShips()) {
			ship.setGame(game);
			shipService.insert(ship);
		}
		return game;
	}
	
	private boolean playerDontBelongToGame(Game game, Integer id) {
		List<Player> players = game.getPlayers().stream().filter(p -> p.getId() == id).collect(Collectors.toList());
		if(players.size() == 0) {
			return true;
		}
		return false;
	}

	private boolean haveGameReadyToStart(Game game) {
		if(game.getShips().size() > 0) {
			return true;
		}
		return false;
	}

	public Game createNewGame(Player playerFrom, Player playerTo) {
		List<Player> players = new ArrayList<>();
		players.addAll(Arrays.asList(playerFrom, playerTo));
		return repository.save(new Game(null, players, StatusGame.CREATED, new Date(), null, null, null));
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
		if(game.getStatus() == StatusGame.PAUSE) {
			game.setStatus(StatusGame.STARTED);
		} else {
			game.setStatus(StatusGame.PAUSE);
		}		
		repository.save(game);
		return game;
	}

	public void setPlayerTurn(Game game, Player player) {
		game.setPlayerTurn(player);
		repository.save(game);
		
	}


}
