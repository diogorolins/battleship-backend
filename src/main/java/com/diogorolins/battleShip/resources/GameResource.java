package com.diogorolins.battleShip.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diogorolins.battleShip.config.security.TokenService;
import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.Strike;
import com.diogorolins.battleShip.model.dto.ShipInsertDTO;
import com.diogorolins.battleShip.services.GameService;
import com.diogorolins.battleShip.services.PlayerService;
import com.diogorolins.battleShip.services.StrikeService;

@RestController
@RequestMapping(value = "/games")
public class GameResource {
	
	@Autowired
	private GameService gameService;
	
	@Autowired 
	private StrikeService strikeService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Game> addShips(
			@RequestBody List<ShipInsertDTO> shipsDTO, 
			@PathVariable Integer id,
			HttpServletRequest request) {
		
		String emailPlayer = tokenService.getUsername(tokenService.getToken(request));
		Player player = playerService.findyEmail(emailPlayer);
		Game game = gameService.insertShips(shipsDTO, id, player.getId());
		return ResponseEntity.ok().body(game);
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/strike/{id}")
	public ResponseEntity<List<Strike>> strikeyGame(@PathVariable Integer id){
		List<Strike> strikes = strikeService.getStrikesByGame(id);
		return ResponseEntity.ok().body(strikes); 
	}
}
