package vn.iotstar.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Getter;
import vn.iotstar.dto.UserDTO;

@Getter
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final String fullName;
    private final String images;
    private final String role;
    private final boolean enabled;

    public CustomUserDetails(UserDTO user, String password) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = password;
        this.fullName = user.getFullName();
        this.images = user.getImages();
        this.role = user.getRoleName();
        this.enabled = user.isEnabled();
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getImages() { return images; }
    public String getRole() { return role; }
    public boolean isEnabled() { return enabled; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }
}
