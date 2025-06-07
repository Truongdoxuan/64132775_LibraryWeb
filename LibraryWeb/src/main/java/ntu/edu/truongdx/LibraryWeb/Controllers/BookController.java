package ntu.edu.truongdx.LibraryWeb.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import ntu.edu.truongdx.LibraryWeb.Models.Sach;
import ntu.edu.truongdx.LibraryWeb.Services.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private SachService sachService;

    @GetMapping("/sach/{id}")
    public String chiTietSach(@PathVariable("id") int id, Model model, Authentication authentication, HttpServletRequest request) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("");
        model.addAttribute("role", role);
        model.addAttribute("currentUri", request.getRequestURI());
        System.out.println("Request chi tiết sách với id = " + id);
        Optional<Sach> optionalSach = sachService.findById(id);

        if (optionalSach.isPresent()) {
            Sach sach = optionalSach.get();
            System.out.println("Tìm thấy sách: " + sach.getTenSach());
            model.addAttribute("sach", sach);
            return "views/chi_tiet_sach";
        } else {
            System.out.println("Không tìm thấy sách với id = " + id);
            return "redirect:/admin/trangchu";
        }
    }
}
