package com.bff.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bff.reservation.common.model.Role;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.repository.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
@ComponentScan(basePackages = "com.bff.reservation")
public class SpringSecurityApplication implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
        Users adminAccount = userRepository.findByRole(Role.admin);
        if (null == adminAccount) {
            Users user = new Users();

            user.setEmail("admin@gmail.com");
            user.setName("admin");
            user.setNo_telp("081");
            user.setRole(Role.admin);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }
}
