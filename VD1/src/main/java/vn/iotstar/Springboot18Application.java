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
public class Springboot18Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot18Application.class, args);
    }

    @Bean
    CommandLineRunner initData(
            RoleRepository roles,
            UserRepository users,
            PasswordEncoder encoder
    ) {
        return args -> {
            Role userRole = roles.findByNameIgnoreCase("USER")
                    .orElseGet(() -> roles.save(new Role("USER")));

            Role adminRole = roles.findByNameIgnoreCase("ADMIN")
                    .orElseGet(() -> roles.save(new Role("ADMIN")));

            if (!users.existsByEmailIgnoreCase("admin@example.com")) {
                User admin = new User();
                admin.setEmail("admin@example.com");
                admin.setFullName("System Administrator");
                admin.setPassword(encoder.encode("123456"));
                admin.setRole(adminRole);
                admin.setEnabled(true);
                users.save(admin);
            }

            if (!users.existsByEmailIgnoreCase("user@example.com")) {
                User user = new User();
                user.setEmail("user@example.com");
                user.setFullName("Nguyễn Hữu Trung");
                user.setPassword(encoder.encode("123456"));
                user.setRole(userRole);
                user.setEnabled(true);
                users.save(user);
            }
        };
    }
}
