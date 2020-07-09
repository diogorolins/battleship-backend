package com.diogorolins.battleShip.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.diogorolins.battleShip.model.enums.StatusGame;

@Entity
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany
	private List<Player> players = new ArrayList<>();
	private StatusGame status;
	private Date date;
	
	private Player winner;

	@OneToMany(mappedBy = "game")
	private List<Ship> ships = new ArrayList<>();

	public Game() {

	}

	public Game(Integer id, List<Player> players, StatusGame status, Date date, Player winner, List<Ship> ships) {
		super();
		this.id = id;
		this.players = players;
		this.status = status;
		this.date = date;
		this.winner = winner;
		this.ships = ships;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public StatusGame getStatus() {
		return status;
	}

	public void setStatus(StatusGame status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", players=" + players + ", status=" + status + ", date=" + date + ", winner="
				+ winner + ", ships=" + ships + "]";
	}

}
