package ntu.edu.truongdx.LibraryWeb.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import ntu.edu.truongdx.LibraryWeb.Models.DatSach;
import ntu.edu.truongdx.LibraryWeb.Models.Sach;
import ntu.edu.truongdx.LibraryWeb.Models.TheLoai;
import ntu.edu.truongdx.LibraryWeb.Repositories.DatSachRepository;
import ntu.edu.truongdx.LibraryWeb.Repositories.SachRepository;
import ntu.edu.truongdx.LibraryWeb.Repositories.TheLoaiRepository;
import ntu.edu.truongdx.LibraryWeb.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SachRepository sachRepository;

    @Autowired
    TheLoaiRepository theLoaiRepository;

    @Autowired
    DatSachRepository datSachRepository;

    @GetMapping("/trangchu")
    public String userHome(
            HttpServletRequest request,
            Model model,
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String tenSach,
            @RequestParam(required = false, defaultValue = "0") int theLoai) {

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("ten", userDetails.getTen());

            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(a -> a.getAuthority().replace("ROLE_", ""))
                    .orElse("");
            model.addAttribute("role", role);
        } else {
            model.addAttribute("role", "");
        }

        int pageSize = 8;
        Page<Sach> sachPage;
        List<TheLoai> dsTheLoai = theLoaiRepository.findAll();

        String tenSachSearch = (tenSach != null) ? tenSach : "";

        if (theLoai > 0) {
            sachPage = sachRepository.findByTenSachContainingIgnoreCaseAndIdTheLoai(tenSachSearch, theLoai, PageRequest.of(page, pageSize));
        } else {
            sachPage = sachRepository.findByTenSachContainingIgnoreCase(tenSachSearch, PageRequest.of(page, pageSize));
        }

        model.addAttribute("currentUri", request.getRequestURI());

        model.addAttribute("dsSach", sachPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sachPage.getTotalPages());

        model.addAttribute("tenSach", tenSach);
        model.addAttribute("theLoai", theLoai);
        model.addAttribute("dsTheLoai", dsTheLoai);
        return "views/user/trangchu";
    }

    @GetMapping("/dat_truoc_sach")
    public String DatSach(@RequestParam("id") int idSach,
                              @RequestParam("soLuong") int soLuong,
                              Authentication authentication) {

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            return "redirect:/login";
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        DatSach datSach = new DatSach();
        datSach.setIdUser(userDetails.getId());
        datSach.setIdSach(idSach);
        datSach.setSluong(soLuong);
        datSach.setNgayDat(LocalDate.now());
        datSach.setIdTrangThai(1);

        datSachRepository.save(datSach);
        return "redirect:/user/ds_dat_truoc";

    }

    @GetMapping("ds_dat_truoc")
    public String viewDatSach(HttpServletRequest request, Authentication authentication, Model model) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("");

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            return "redirect:/login";
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<DatSach> datSachList = datSachRepository.findByIdUser(userDetails.getId());

        model.addAttribute("datSachList", datSachList);
        model.addAttribute("role", role);
        model.addAttribute("currentUri", request.getRequestURI());
        return "views/user/dat_truoc_sach";
    }

    @GetMapping("/lich_su_muon")
    public String viewLichSuMuon(HttpServletRequest request, Model model, Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("");
        model.addAttribute("role", role);
        model.addAttribute("currentUri", request.getRequestURI());
        return "views/user/lich_su_muon";
    }
}
