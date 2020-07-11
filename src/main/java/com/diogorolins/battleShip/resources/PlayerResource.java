package com.diogorolins.battleShip.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diogorolins.battleShip.config.security.TokenService;
import com.diogorolins.battleShip.model.Invite;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.dto.PlayerCreateDTO;
import com.diogorolins.battleShip.model.enums.StatusInvite;
import com.diogorolins.battleShip.services.InviteService;
import com.diogorolins.battleShip.services.PlayerService;

@RestController
@RequestMapping(value = "/players")
public class PlayerResource {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private InviteService inviteService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Player>> findAll(){
		List<Player> players = playerService.findAll();
		return  ResponseEntity.ok().body(players);
	};
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Player> findById(@PathVariable Integer id){
		Player player = playerService.findById(id);
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/logged")
	public ResponseEntity<List<Player>> findLoggedPlayers( HttpServletRequest request){
		String emailPlayer = tokenService.getUsername(tokenService.getToken(request));
		List<Player> players = playerService.findLoggedPlayers(emailPlayer);
		return ResponseEntity.ok().body(players);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/invite")
	public ResponseEntity<Invite> sendInvite(@RequestParam(value = "player") Integer playerId, HttpServletRequest request){
		String emailPlayer = tokenService.getUsername(tokenService.getToken(request));
		Player playerFrom = playerService.findyEmail(emailPlayer);
		Player playerTo = playerService.findById(playerId);
		Invite invite = inviteService.insert(new Invite(null, playerFrom, playerTo, StatusInvite.WAITING, new Date()));
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(invite.getId()).toUri();
		return ResponseEntity.created(uri).body(invite);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/invite")
	public ResponseEntity<List<Invite>> searchInvites(HttpServletRequest request){
		String emailPlayer = tokenService.getUsername(tokenService.getToken(request));
		Player player = playerService.findyEmail(emailPlayer);
		List<Invite> invites = inviteService.searchInvite(player);
		return ResponseEntity.ok().body(invites);
	}
	
	@RequestMapping(method = RequestMethod.PATCH, value = "/invite/decline/{id}")
	public ResponseEntity<Invite> declineInvite(@PathVariable Integer id){
		Invite invite = inviteService.declineInvite(id);
		return ResponseEntity.ok().body(invite);
	}
	
	@RequestMapping(method = RequestMethod.PATCH, value = "/invite/accept/{id}")
	public ResponseEntity<Invite> acceptInvite(@PathVariable Integer id){
		Invite invite = inviteService.acceptInvite(id);
		return ResponseEntity.ok().body(invite);
	}
	
	
	
}
