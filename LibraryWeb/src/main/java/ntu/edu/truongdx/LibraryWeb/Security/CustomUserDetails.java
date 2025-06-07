package ntu.edu.truongdx.LibraryWeb.Security;

import ntu.edu.truongdx.LibraryWeb.Models.NguoiDung;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final NguoiDung nguoiDung;

    public CustomUserDetails(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public String getTen() {
        return nguoiDung.getTen();
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public int getId() {
        return nguoiDung.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + nguoiDung.getVaitro().toUpperCase());
    }

    @Override
    public String getPassword() {
        return nguoiDung.getMatkhau();
    }

    @Override
    public String getUsername() {
        return nguoiDung.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
