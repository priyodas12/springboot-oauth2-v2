package io.oauth2.learn;

import io.oauth2.learn.model.ProductManager;
import io.oauth2.learn.model.ProductManagerRole;
import io.oauth2.learn.repository.ProductManagerRepository;
import io.oauth2.learn.repository.ProductManagerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Oauth2Application  implements CommandLineRunner {

	@Autowired
	private ProductManagerRepository productManagerRepository;

	@Autowired
	private ProductManagerRoleRepository productManagerRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("command line runner...");
/*
		ProductManagerRole pr1=new ProductManagerRole();
		pr1.setRId(10L);
		pr1.setRoleNames("ADMIN");

		productManagerRoleRepository.save(pr1);

		Set<ProductManagerRole> s1=new HashSet<ProductManagerRole>();
		s1.add(pr1);

		ProductManager p1=new ProductManager();
		p1.setPId(10L);
		p1.setUserName("priyo");
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(4);
		p1.setPassword(passwordEncoder.encode("priyo123"));
		p1.setRoles(s1);
		p1.setProductOwner("PO_FURNITURE");

		productManagerRepository.save(p1);*/
	}
}
