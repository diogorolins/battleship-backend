package com.diogorolins.battleShip.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.diogorolins.battleShip.model.enums.Hit;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ShipPosition implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String position;
	private Hit hit = Hit.CLEAN;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ship_id")	
	private Ship ship;
	
	public ShipPosition() {
		
	}

	public ShipPosition(String position, Hit hit, Ship ship) {
		super();
		this.position = position;
		this.hit = hit;
		this.ship = ship;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Hit getHit() {
		return hit;
	}

	public void setHit(Hit hit) {
		this.hit = hit;
	}
	
	
}
