package com.diogorolins.battleShip.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.Ship;
import com.diogorolins.battleShip.model.ShipPosition;
import com.diogorolins.battleShip.model.Strike;
import com.diogorolins.battleShip.model.enums.Hit;
import com.diogorolins.battleShip.repositories.StrikeRepository;

@Service
public class StrikeService {

	@Autowired
	private StrikeRepository repository;
	
	@Autowired
	private ShipPositionService shipPosityionService;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private PlayerService playerService;
	
	
	public Strike hitStrike(Strike strike) {
		
		List<ShipPosition> position = new ArrayList<>();
		Game game = gameService.findById(strike.getGame().getId());
		Player player = playerService.findById(strike.getPlayer().getId());
		strike.setGame(game);		
		strike.setPlayer(player);	
		gameService.setPlayerTurn(game, player);
		
		for(Ship ship : game.getShips()) {
			if(ship.getPlayer().equals(strike.getPlayer())) {
				position.addAll(ship.getPosition().stream().filter(p -> p.getPosition().equals(strike.getPosition())).collect(Collectors.toList())) ;
			}
		}
		if(position.size() > 0) {
			strike.setHit(true);
			position.get(0).setHit(Hit.HITED);
			shipPosityionService.insert(position.get(0));
		}
		
		
		return repository.save(strike);
	}


	public List<Strike> getStrikesByGame(Integer id) {
		return repository.findByGame(gameService.findById(id));
	}
}
