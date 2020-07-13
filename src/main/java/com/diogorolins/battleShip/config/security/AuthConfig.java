package com.diogorolins.battleShip.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.model.Player;
import com.diogorolins.battleShip.repositories.PlayerRepository;

@Service
public class AuthConfig implements UserDetailsService {

	@Autowired
	public PlayerRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Player user = repository.findByEmail(username).get();
		if(user == null) {
			throw new UsernameNotFoundException("Dados Inválidos");
		} 
		return user;
	}

}
