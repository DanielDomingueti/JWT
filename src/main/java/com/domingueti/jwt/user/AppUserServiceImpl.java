package com.domingueti.jwt.user;

import java.util.List;

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
public class AppUserServiceImpl implements AppUserService {

	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	
	@Override
	public AppUser saveUser(AppUser user) {
		log.info("Saving new user {} to database", user.getName());
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
