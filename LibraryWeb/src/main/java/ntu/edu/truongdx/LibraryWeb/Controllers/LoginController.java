package ntu.edu.truongdx.LibraryWeb.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String viewLogin() {
        return "views/Login";
    }
}
