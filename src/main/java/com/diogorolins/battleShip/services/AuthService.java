package com.diogorolins.battleShip.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.diogorolins.battleShip.model.dto.LoginDTO;

@Service
public class AuthService {
	
	public UsernamePasswordAuthenticationToken convertToLogin(LoginDTO objDTO) {
		return new UsernamePasswordAuthenticationToken(objDTO.getEmail(), objDTO.getPassword());
	}
	
}
