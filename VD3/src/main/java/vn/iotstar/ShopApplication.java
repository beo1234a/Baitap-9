package vn.iotstar;
import org.springframework.boot.*;import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.context.annotation.Bean;import org.springframework.security.crypto.password.PasswordEncoder;import vn.iotstar.entity.*;import vn.iotstar.repository.*;
@SpringBootApplication public class ShopApplication{
 public static void main(String[] args){SpringApplication.run(ShopApplication.class,args);}
 @Bean CommandLineRunner init(RoleRepository rr,UserRepository ur,PasswordEncoder enc){return args->{Role user=rr.findByName("ROLE_USER").orElseGet(()->rr.save(new Role(null,"ROLE_USER")));Role admin=rr.findByName("ROLE_ADMIN").orElseGet(()->rr.save(new Role(null,"ROLE_ADMIN")));if(!ur.existsByUsername("admin"))ur.save(new User(null,"admin","admin@example.com",enc.encode("123456"),"System Administrator",true,admin,new java.util.ArrayList<>()));if(!ur.existsByUsername("user"))ur.save(new User(null,"user","user@example.com",enc.encode("123456"),"Nguoi dung mau",true,user,new java.util.ArrayList<>()));};}
}
