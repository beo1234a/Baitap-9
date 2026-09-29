package vn.iotstar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.repository.RoleRepository;
import vn.iotstar.repository.UserRepository;

@SpringBootApplication
public class Springboot19Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot19Application.class, args);
    }

    @Bean
    CommandLineRunner init(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            Role userRole = roleRepository
                    .findByName("ROLE_USER")
                    .orElseGet(() ->
                            roleRepository.save(
                                    Role.builder()
                                            .name("ROLE_USER")
                                            .build()
                            )
                    );

            User user = userRepository
                    .findByUsername("user01")
                    .orElseGet(User::new);

            user.setUsername("user01");
            user.setEmail("user01@gmail.com");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setFullName("Nguyễn Anh Tuấn");
            user.setImages("/images/user.png");
            user.setRole(userRole);
            user.setEnabled(true);

            userRepository.save(user);
        };
    }
}