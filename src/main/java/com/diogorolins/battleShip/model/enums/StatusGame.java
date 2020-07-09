package com.diogorolins.battleShip.model.enums;

public enum StatusGame {
	CRIADO(1), INICIADO(2), PAUSA(3), FINALIZADO(4);

	private final int status;

	StatusGame(int status){
        this.status = status;
    }

	public int getStatusGame() {
		return status;
	}
}
