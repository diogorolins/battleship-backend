package com.diogorolins.battleShip.model.enums;

public enum StatusGame {
	CREATED(1), BEGAN(2), PAUSE(3), FINISHED(4);

	private final int status;

	StatusGame(int status){
        this.status = status;
    }

	public int getStatusGame() {
		return status;
	}
}
