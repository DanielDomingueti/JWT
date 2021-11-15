package com.domingueti.jwt.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domingueti.jwt.role.Role;
import com.domingueti.jwt.role.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService{

	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserRepository.findByUsername(username);
		if (user == null) {
			 log.error("User not found in the DB");
			 throw new UsernameNotFoundException("User not found in the DB");
		} else {
			log.info("User found in the DB: {}", username);			
		}
		Collection<SimpleGrantedAuthority> auth = new ArrayList<>();
		user.getRoles().forEach(role -> {
			auth.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auth);
	}
	
	@Override
	public AppUser saveUser(AppUser user) {
		log.info("Saving new user {} to database", user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return appUserRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} to database", role.getName());
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String rolee) {
		log.info("Saving new role {} to user {}", rolee, username);
		AppUser user = appUserRepository.findByUsername(username);
		Role role = roleRepository.findByName(rolee);
		user.getRoles().add(role);
	}

	@Override
	public AppUser getUser(String username) {
		log.info("Fetching user {} by username", username);
		return appUserRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> getUsers() {
		log.info("Fetching all users");
		return appUserRepository.findAll();
	}

}
