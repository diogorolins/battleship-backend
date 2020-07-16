package com.diogorolins.battleShip.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diogorolins.battleShip.model.ShipType;
import com.diogorolins.battleShip.services.ShipTypeService;

@RestController
@RequestMapping(value = "/ships")
public class ShipResource {
	
	@Autowired
	private ShipTypeService shipTypeService;

	@RequestMapping(method = RequestMethod.GET, value = "/types")
	public ResponseEntity<List<ShipType>> getShipTypes(){
		return ResponseEntity.ok().body(shipTypeService.findAll());
	}
}
