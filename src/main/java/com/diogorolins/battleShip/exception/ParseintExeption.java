package com.diogorolins.battleShip.exception;

public class ParseintExeption extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ParseintExeption(String msg) {
		super(msg);
	}
	
	public ParseintExeption(String msg, Throwable cause) {
		super(msg, cause);
	}
}
