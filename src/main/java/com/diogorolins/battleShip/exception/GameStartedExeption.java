package com.diogorolins.battleShip.exception;

public class GameStartedExeption extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public GameStartedExeption(String msg) {
		super(msg);
	}
	
	public GameStartedExeption(String msg, Throwable cause) {
		super(msg, cause);
	}
}
