package com.diogorolins.battleShip.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.diogorolins.battleShip.model.ShipPosition;

public class ShipInsertDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer type;
	private Integer player;
	private List<ShipPosition> position = new ArrayList<>();	
	
	public ShipInsertDTO() {
		
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPlayer() {
		return player;
	}

	public void setPlayer(Integer player) {
		this.player = player;
	}

	public List<ShipPosition> getPosition() {
		return position;
	}

	public void setPosition(List<ShipPosition> position) {
		this.position = position;
	}
	
	

}
