package com.domingueti.jwt;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.domingueti.jwt.role.Role;
import com.domingueti.jwt.user.AppUser;
import com.domingueti.jwt.user.AppUserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CommandLineRunnerClass implements CommandLineRunner{

	private final AppUserService appUserService;
	
	@Override
	public void run(String... args) throws Exception {
		appUserService.saveRole(new Role(null, "ROLE_USER"));
		appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
		appUserService.saveRole(new Role(null, "ROLE_MANAGER"));
		appUserService.saveRole(new Role(null, "ROLE_SUPER_ADM"));

		appUserService.saveUser(new AppUser(null, "Dianho", "dianho123", "123", new ArrayList<>()));
		appUserService.saveUser(new AppUser(null, "Will smith", "will", "123", new ArrayList<>()));
		appUserService.saveUser(new AppUser(null, "Arnold schwarzenegger", "arnoldzinho", "123", new ArrayList<>()));
		appUserService.saveUser(new AppUser(null, "Jim carry", "jim", "123", new ArrayList<>()));
		
		appUserService.addRoleToUser("dianho123", "ROLE_USER");
		appUserService.addRoleToUser("dianho123", "ROLE_MANAGER");
		appUserService.addRoleToUser("dianho123", "ROLE_ADMIN");
		appUserService.addRoleToUser("dianho123", "ROLE_SUPER_ADM");
		appUserService.addRoleToUser("will", "ROLE_ADMIN");
		appUserService.addRoleToUser("arnoldzinho", "ROLE_MANAGER");
		appUserService.addRoleToUser("jim", "ROLE_SUPER_ADM");


	}

}
