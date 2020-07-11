package com.diogorolins.battleShip.model.enums;

public enum StatusInvite {
	WAITING(1), ACCEPTED(2), DECLINED(3);

	private final int status;

	StatusInvite(int status){
        this.status = status;
    }

	public int getStatusInvite() {
		return status;
	}
}
