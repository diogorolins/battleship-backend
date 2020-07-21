package com.diogorolins.battleShip.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.diogorolins.battleShip.model.Game;
import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.model.Ship;
import com.diogorolins.battleShip.model.ShipPosition;
import com.diogorolins.battleShip.model.ShipType;
import com.diogorolins.battleShip.model.enums.Hit;
import com.diogorolins.battleShip.model.enums.StatusGame;
import com.diogorolins.battleShip.repositories.GameRepository;
import com.diogorolins.battleShip.repositories.PlayerRepository;
import com.diogorolins.battleShip.repositories.ShipPositionRepository;
import com.diogorolins.battleShip.repositories.ShipRepository;
import com.diogorolins.battleShip.repositories.ShipTypeRepository;

@Configuration
@Profile("dev")
public class Instantiation implements CommandLineRunner {

	@Autowired
	private ShipTypeRepository shipTyperepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private ShipRepository shipRepository;

	@Autowired
	private ShipPositionRepository positionRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Override
	public void run(String... args) throws Exception {

		ShipType shipType1 = new ShipType(null, "Porta-Aviões 1", 5);
		//ShipType shipType2 = new ShipType(null, "Porta-Aviões 2", 5);
		ShipType shipType3 = new ShipType(null, "Navio-Tanque 1", 4);
		//ShipType shipType4 = new ShipType(null, "Navio-Tanque 2", 4);
		ShipType shipType5 = new ShipType(null, "Contra-Torpedeiro 1", 3);
		//ShipType shipType6 = new ShipType(null, "Contra-Torpedeiro 2", 3);
		ShipType shipType7 = new ShipType(null, "Submarino 1", 2);
		//ShipType shipType8 = new ShipType(null, "Submarino 2", 2);

		//shipTyperepository.saveAll(
		//		Arrays.asList(shipType1, shipType2, shipType3, shipType4, shipType5, shipType6, shipType7, shipType8));

		shipTyperepository.saveAll(
					Arrays.asList(shipType1, shipType3, shipType5, shipType7));
		
				
		Player player1 = new Player(null, "Diogo", "diogo@email.com", pe.encode("123456"), null);
		Player player2 = new Player(null, "Bernardo", "be@email.com", pe.encode("123456"), null);
		Player player3 = new Player(null, "Felipe", "fe@email.com", pe.encode("123456"), null);
		Player player4 = new Player(null, "Mariana", "ma@email.com", pe.encode("123456"), null);

		playerRepository.saveAll(Arrays.asList(player1, player2, player3, player4));

		//game
		/*
		Game game1 = new Game(null, Arrays.asList(player1, player2), StatusGame.STARTED, new Date(), null, null, player2);

		gameRepository.save(game1);
		
		Ship ship1 = new Ship(null, shipType1, null, player1, null);

		Ship ship2 = new Ship(null, shipType1, null, player1, null);
		
		Ship ship3 = new Ship(null, shipType1, null, player2, null);

		Ship ship4 = new Ship(null, shipType1, null, player2, null);
		
		shipRepository.saveAll(Arrays.asList(ship1, ship2, ship3, ship4));

		ShipPosition position1 = new ShipPosition("A1", Hit.CLEAN, ship1);
		ShipPosition position2 = new ShipPosition("A2", Hit.CLEAN, ship1);
		ShipPosition position3 = new ShipPosition("A3", Hit.CLEAN, ship1);
		ShipPosition position4 = new ShipPosition("A4", Hit.CLEAN, ship1);
		ShipPosition position5 = new ShipPosition("A5", Hit.CLEAN, ship1);

		positionRepository.saveAll(Arrays.asList(position1, position2, position3, position4, position5));

		ShipPosition position6 = new ShipPosition("C1", Hit.CLEAN, ship2);
		ShipPosition position7 = new ShipPosition("C2", Hit.CLEAN, ship2);
		ShipPosition position8 = new ShipPosition("C3", Hit.CLEAN, ship2);
		ShipPosition position9 = new ShipPosition("C4", Hit.CLEAN, ship2);
		ShipPosition position10 = new ShipPosition("C5", Hit.CLEAN, ship2);

		positionRepository.saveAll(Arrays.asList(position6, position7, position8, position9, position10));
		
		ShipPosition position11 = new ShipPosition("A1", Hit.CLEAN, ship3);
		ShipPosition position12 = new ShipPosition("A2", Hit.CLEAN, ship3);
		ShipPosition position13 = new ShipPosition("A3", Hit.CLEAN, ship3);
		ShipPosition position14 = new ShipPosition("A4", Hit.CLEAN, ship3);
		ShipPosition position15 = new ShipPosition("A5", Hit.CLEAN, ship3);
		
		positionRepository.saveAll(Arrays.asList(position11, position12, position13, position14, position15));
		
		ShipPosition position16 = new ShipPosition("D1", Hit.CLEAN, ship3);
		ShipPosition position17 = new ShipPosition("D2", Hit.CLEAN, ship3);
		ShipPosition position18 = new ShipPosition("D3", Hit.CLEAN, ship3);
		ShipPosition position19 = new ShipPosition("D4", Hit.CLEAN, ship3);
		ShipPosition position20 = new ShipPosition("D5", Hit.CLEAN, ship3);


		positionRepository.saveAll(Arrays.asList(position16, position17, position18, position19, position20));
		
		ship1.setPosition(Arrays.asList(position1, position2, position3, position4, position5));
		ship2.setPosition(Arrays.asList(position6, position7, position8, position9, position10));
		ship3.setPosition(Arrays.asList(position11, position12, position13, position14, position15));
		ship4.setPosition(Arrays.asList(position16, position17, position18, position19, position20));
		

		shipRepository.saveAll(Arrays.asList(ship1, ship2, ship3, ship4));

		game1.setShips(Arrays.asList(ship1, ship2,ship3, ship4));

		gameRepository.save(game1);
		
		ship1.setGame(game1);
		ship2.setGame(game1);
		ship3.setGame(game1);
		ship4.setGame(game1);
		
		shipRepository.saveAll(Arrays.asList(ship1, ship2, ship3, ship4));
	
	*/	

	}
}
