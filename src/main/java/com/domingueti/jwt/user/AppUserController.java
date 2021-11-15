package com.domingueti.jwt.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class AppUserController {
	
	private final AppUserService appUserService;

	@GetMapping("/users")
	public ResponseEntity<List<AppUser>> getUsers() {
		return ResponseEntity.ok().body(appUserService.getUsers());
	}
	
}
