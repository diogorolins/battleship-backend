package com.diogorolins.battleShip.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.model.Ship;
import com.diogorolins.battleShip.model.ShipPosition;
import com.diogorolins.battleShip.model.dto.ShipInsertDTO;
import com.diogorolins.battleShip.repositories.ShipRepository;

@Service
public class ShipService {
	
	@Autowired
	private ShipRepository repository;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private ShipTypeService shipTypeService;
	
	@Autowired
	private ShipPositionService shipPositionService;

	public Collection<? extends Ship> convertFromDto(List<ShipInsertDTO> shipsDto) {
		List<Ship> ships = new ArrayList<>();
		for(ShipInsertDTO shipDto : shipsDto) {
			Ship ship = new Ship();
			ship.setPlayer(playerService.getById(shipDto.getPlayer()));
			ship.setType(shipTypeService.getbyId(shipDto.getType()));
			ship.getPosition().addAll(shipDto.getPosition());
			ships.add(ship);
		}		
		return ships;
	}
	
	public void insert(Ship ship) {
		ship = repository.save(ship);
		for(ShipPosition position : ship.getPosition()) {
			position.setShip(ship);
			shipPositionService.insert(position);			
		}
		
	}
	
	
}
