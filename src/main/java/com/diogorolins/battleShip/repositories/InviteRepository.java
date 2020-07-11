package com.diogorolins.battleShip.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.diogorolins.battleShip.model.Invite;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.enums.StatusInvite;

public interface InviteRepository extends JpaRepository<Invite, Integer>{

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Invite obj "
	+ "WHERE obj.to = :player and obj.status = :statusInvite" ) 
	public List<Invite> findByPlayer(@Param("player") Player player, @Param("statusInvite") StatusInvite statusInvite);

}
