package com.diogorolins.battleShip.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.exception.ObjectNotFoundException;
import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Invite;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.enums.StatusInvite;
import com.diogorolins.battleShip.repositories.InviteRepository;

@Service
public class InviteService {

	@Autowired
	private InviteRepository repository;
	
	@Autowired
	private GameService gameService;
	
	public Invite insert(Invite invite) {
		return repository.save(invite);
	}

	public List<Invite> searchInviteReceived(Player player) {
		return repository.findByPlayerReceived(player, StatusInvite.WAITING);
	}
	
	public List<Invite> searchInviteSent(Player player) {
		return repository.findByPlayerSent(player, StatusInvite.DONE);
	}
	
	public Invite findById(Integer id) {
		Optional<Invite>  obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found"));
	}

	public Invite declineInvite(Integer id) {
		Invite invite = findById(id);
		invite.setStatus(StatusInvite.DECLINED);
		repository.save(invite);
		return invite;
	}

	public Invite acceptInvite(Integer id) {
		Invite invite = findById(id);
		Game game = gameService.createNewGame(invite.getFrom(), invite.getTo());
		invite.setStatus(StatusInvite.ACCEPTED);
		invite.setGame(game);
		repository.save(invite);
		return invite;
	}
	
	public void finishInvitesWhenGameStart(Game game) {
		List<Invite> invites = repository.findByGame(game);
		for (Invite invite : invites) {
			invite.setStatus(StatusInvite.DONE);
		}		
	}
	
}
