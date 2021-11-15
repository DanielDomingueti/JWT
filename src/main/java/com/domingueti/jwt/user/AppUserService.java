package com.domingueti.jwt.user;

import java.util.List;

import com.domingueti.jwt.role.Role;

public interface AppUserService {

	AppUser saveUser(AppUser user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String role);
	AppUser getUser(String username);
	List<AppUser> getUsers();
	
}
