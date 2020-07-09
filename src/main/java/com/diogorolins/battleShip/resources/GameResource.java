package com.diogorolins.battleShip.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Strike;
import com.diogorolins.battleShip.model.dto.GameCreateDTO;
import com.diogorolins.battleShip.services.GameService;
import com.diogorolins.battleShip.services.StrikeService;

@RestController
@RequestMapping(value = "/games")
public class GameResource {
	
	@Autowired
	private GameService gameService;
	
	@Autowired 
	private StrikeService strikeService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<GameCreateDTO> insert(@RequestBody GameCreateDTO objDto) {
		Game game = gameService.convertFromDto(objDto);
		game = gameService.insert(game);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(game.getId()).toUri();
		return ResponseEntity.created(uri).body(new GameCreateDTO(game));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Game> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(gameService.findById(id));
	}
	
	@RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
	public ResponseEntity<Game> pause(@PathVariable Integer id){
		Game game = gameService.pause(id);
		return ResponseEntity.ok().body(game); 
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/strike")
	public ResponseEntity<Strike> strike(@RequestBody Strike strike){
		strike = strikeService.hitStrike(strike);
		return ResponseEntity.ok().body(strike); 
	}
}
