package com.diogorolins.battleShip.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		strike.setGame(gameService.findById(strike.getGame().getId()));
		strike.setPlayer(playerService.getById(strike.getPlayer().getId()));		
		for(Ship ship : strike.getGame().getShips()) {
			if(ship.getPlayer().equals(strike.getPlayer())) {
				position = ship.getPosition().stream().filter(p -> p.getPosition().equals(strike.getPosition())).collect(Collectors.toList());
			}
		}
		if(position.size() > 0) {
			strike.setHit(true);
			position.get(0).setHit(Hit.HITED);
			shipPosityionService.insert(position.get(0));
		}
		
		
		return repository.save(strike);
	}
}
