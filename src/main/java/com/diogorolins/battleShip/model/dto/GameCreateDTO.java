package com.diogorolins.battleShip.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Player;

public class GameCreateDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Player player1;
	private Player player2;
	private List<ShipInsertDTO> ships = new ArrayList<>(); 
	
	public GameCreateDTO() {
		
	}
	
	public GameCreateDTO(Game game) {
		this.player1 = game.getPlayers().get(0);
		this.player2 = game.getPlayers().get(1);
		this.ships = game.getShips().stream().map(ship -> new ShipInsertDTO(ship)).collect(Collectors.toList());
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public List<ShipInsertDTO> getShips() {
		return ships;
	}

	
}
