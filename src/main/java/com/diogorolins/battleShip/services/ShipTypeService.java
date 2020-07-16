package com.diogorolins.battleShip.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.model.ShipType;
import com.diogorolins.battleShip.repositories.ShipTypeRepository;

@Service
public class ShipTypeService {

	@Autowired
	private ShipTypeRepository repository;
	
	public ShipType getbyId(Integer id) {
		return repository.findById(id).get();
	}
	
	public List<ShipType> findAll() {
		return repository.findAll();
	}
	
}
