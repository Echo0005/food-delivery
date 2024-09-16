package com.eco.fooddelivery;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eco.fooddelivery.jwt.model.Role;
import com.eco.fooddelivery.jwt.model.User;
import com.eco.fooddelivery.jwt.service.UserService;

@SpringBootApplication
public class FooddeliveryApplication implements CommandLineRunner
{
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(FooddeliveryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> adminAccount = userService.findByRole(Role.ADMIN);

		if (adminAccount.isEmpty()) {
			User user = new User();

			user.setName("admin");
			user.setEmail("admin@gmail.com");
			user.setPassword("admin");

			userService.createUserAdmin(user);
		}
	}

}