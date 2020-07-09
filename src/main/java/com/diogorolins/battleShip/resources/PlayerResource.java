package com.diogorolins.battleShip.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.dto.PlayerCreateDTO;
import com.diogorolins.battleShip.services.PlayerService;

@RestController
@RequestMapping(value = "/players")
public class PlayerResource {
	
	@Autowired
	private PlayerService playerService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Player> findAll(){
		Player player = new Player(null, "Diogo", "email", "password", null);
		return  ResponseEntity.ok().body(player);
	};
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PlayerCreateDTO> insert(@RequestBody @Valid PlayerCreateDTO objDto){
		Player player = playerService.convertFromDto(objDto);
		player = playerService.insert(player);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(player.getId()).toUri();
		return ResponseEntity.created(uri).body(new PlayerCreateDTO(player));
	}
	
	
	
}
