package ntu.edu.truongdx.LibraryWeb.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("Dxt2004@");
        System.out.println("Mã hóa BCrypt của mật khẩu là:");
        System.out.println(hash);
    }
}
