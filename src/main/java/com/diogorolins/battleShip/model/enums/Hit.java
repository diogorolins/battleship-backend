package com.diogorolins.battleShip.model.enums;

public enum Hit {
	CLEAN(1), HITED(2);

	private final int status;

	Hit(int status){
        this.status = status;
    }

	public int getHit() {
		return status;
	}
}
