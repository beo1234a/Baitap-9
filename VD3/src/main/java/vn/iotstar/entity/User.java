package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="users", indexes={@Index(name="idx_users_username", columnList="username"), @Index(name="idx_users_email", columnList="email")})
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=50) private String username;
    @Column(nullable=false, unique=true, length=150) private String email;
    @Column(nullable=false) private String password;
    @Column(columnDefinition="nvarchar(500)") private String fullName;
    @Column(nullable=false) private boolean enabled=false;
    @ManyToOne(fetch=FetchType.EAGER, optional=false) @JoinColumn(name="role_id", nullable=false) private Role role;
    @OneToMany(mappedBy="user", fetch=FetchType.LAZY) private List<Product> products=new ArrayList<>();
    public User() {}
    public User(Long id,String username,String email,String password,String fullName,boolean enabled,Role role,List<Product> products){this.id=id;this.username=username;this.email=email;this.password=password;this.fullName=fullName;this.enabled=enabled;this.role=role;this.products=products;}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public boolean isEnabled(){return enabled;} public void setEnabled(boolean v){enabled=v;}
    public Role getRole(){return role;} public void setRole(Role v){role=v;}
    public List<Product> getProducts(){return products;} public void setProducts(List<Product> v){products=v;}
}
