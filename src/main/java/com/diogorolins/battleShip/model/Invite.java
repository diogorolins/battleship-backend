package com.diogorolins.battleShip.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.diogorolins.battleShip.model.enums.StatusInvite;

@Entity
public class Invite implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "player_from")
	private Player from;
	
	@ManyToOne
	@JoinColumn(name = "player_to")
	private Player to;
	
	private StatusInvite status;
	
	private Date date;
	
	public Invite(Integer id, Player from, Player to, StatusInvite status, Date date) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.status = status;
		this.date = date;
	}
	
	public Invite() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Player getFrom() {
		return from;
	}
	public void setFrom(Player from) {
		this.from = from;
	}
	public Player getTo() {
		return to;
	}
	public void setTo(Player to) {
		this.to = to;
	}
	public StatusInvite getStatus() {
		return status;
	}
	public void setStatus(StatusInvite status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
