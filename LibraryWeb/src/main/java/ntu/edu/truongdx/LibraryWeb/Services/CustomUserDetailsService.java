package ntu.edu.truongdx.LibraryWeb.Services;

import ntu.edu.truongdx.LibraryWeb.Models.NguoiDung;
import ntu.edu.truongdx.LibraryWeb.Repositories.NguoiDungRepository;
import ntu.edu.truongdx.LibraryWeb.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        NguoiDung user = nguoiDungRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng");
        }
        return new CustomUserDetails(user);
    }
}
