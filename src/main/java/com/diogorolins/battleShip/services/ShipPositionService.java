package com.diogorolins.battleShip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.model.ShipPosition;
import com.diogorolins.battleShip.repositories.ShipPositionRepository;

@Service
public class ShipPositionService {

	@Autowired
	private ShipPositionRepository repository;
	
	public ShipPosition insert(ShipPosition position) {
		return repository.save(position);
	}
}
