package com.diogorolins.battleShip.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.ShipType;
import com.diogorolins.battleShip.repositories.PlayerRepository;
import com.diogorolins.battleShip.repositories.ShipTypeRepository;

@Configuration
@Profile("dev")
public class Instantiation implements CommandLineRunner{

	@Autowired
	private ShipTypeRepository shipTyperepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Override
	public void run(String... args) throws Exception {
		
		ShipType shipType1 = new ShipType(null, "Porta-Aviões", 5);
		ShipType shipType2 = new ShipType(null, "Navio-Tanque", 4);
		ShipType shipType3 = new ShipType(null, "Contra-Torpedeiro", 3);
		ShipType shipType4 = new ShipType(null, "Submarino", 2);
		
		shipTyperepository.saveAll(Arrays.asList(shipType1,shipType2,shipType3,shipType4));
		
		Player player1 = new Player(null, "Diogo", "diogo@email.com", pe.encode("123456"), null);
		Player player2 = new Player(null, "Bernarto", "be@email.com", pe.encode("123456"), null);
		
		playerRepository.saveAll(Arrays.asList(player1,player2));
		
	}
}
