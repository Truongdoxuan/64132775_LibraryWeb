package ntu.edu.truongdx.LibraryWeb.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import ntu.edu.truongdx.LibraryWeb.Models.DatSach;
import ntu.edu.truongdx.LibraryWeb.Models.MuonSach;
import ntu.edu.truongdx.LibraryWeb.Models.Sach;
import ntu.edu.truongdx.LibraryWeb.Models.TheLoai;
import ntu.edu.truongdx.LibraryWeb.Repositories.*;
import ntu.edu.truongdx.LibraryWeb.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SachRepository sachRepository;

    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @Autowired
    private DatSachRepository datSachRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private MuonSachRepository muonSachRepository;

    @GetMapping("/trangchu")
    public String adminHome(
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

        return "views/admin/trangchu";
    }

    @GetMapping("/duyet_dat_sach")
    public String viewDuyet_dat_sach(HttpServletRequest request, Model model,Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("");
        List<DatSach> datSachListNotApproved = datSachRepository.findByIdTrangThai(1);
        List<DatSach> datSachListApproved = datSachRepository.findByIdTrangThai(2);

        model.addAttribute("datSachListNotApproved", datSachListNotApproved);
        model.addAttribute("datSachListApproved", datSachListApproved);
        model.addAttribute("role", role);
        model.addAttribute("currentUri", request.getRequestURI());
        return "views/admin/duyet_dat_sach";
    }

    @GetMapping("/duyet_dat_sach/{id}")
    public String duyetMuon(@PathVariable("id") int id) {
        Optional<DatSach> optionalDatSach = datSachRepository.findById(id);
        if (optionalDatSach.isPresent()) {
            DatSach datSach = optionalDatSach.get();
            Sach sach = datSach.getSach();
            if (sach != null) {
                int soLuongTonHienTai = sach.getSluong();
                int soLuongDat = datSach.getSluong();

                if (soLuongTonHienTai >= soLuongDat) {
                    sach.setSluong(soLuongTonHienTai - soLuongDat);
                    sachRepository.save(sach);

                    datSach.setIdTrangThai(2);
                    datSachRepository.save(datSach);
                }
            }
        }
        return "redirect:/admin/duyet_dat_sach";
    }

    @GetMapping("/muon_tra_sach")
    public String viewMuon_tra_sach(HttpServletRequest request, Model model, Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("");

        List<MuonSach> muonSachList = muonSachRepository.findAll();
        model.addAttribute("muonSachList", muonSachList);
        model.addAttribute("users", nguoiDungRepository.findAll());
        model.addAttribute("sachs", sachRepository.findAll());
        model.addAttribute("muonList", muonSachRepository.findAll());
        model.addAttribute("role", role);
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("role", role);
        model.addAttribute("currentUri", request.getRequestURI());
        return "views/admin/muon_tra_sach";
    }

    @PostMapping("/muon_sach")
    public String themMuonSach(@RequestParam("idUser") int idUser,
                               @RequestParam("idSach") int idSach,
                               @RequestParam("sluong") int sluong,
                               @RequestParam("ngayMuon") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayMuon,
                               @RequestParam("hanTra") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hanTra,
                               @RequestParam("idTrangThai") int idTrangThai) {

        MuonSach muonSach = new MuonSach();
        muonSach.setIdUser(idUser);
        muonSach.setIdSach(idSach);
        muonSach.setSluong(sluong);
        muonSach.setNgayMuon(ngayMuon);
        muonSach.setHanTra(hanTra);
        muonSach.setIdTrangThai(idTrangThai);
        muonSach.setNgayTra(null);

        muonSachRepository.save(muonSach);

        Optional<Sach> optionalSach = sachRepository.findById(idSach);
        if (optionalSach.isPresent()) {
            Sach sach = optionalSach.get();
            int soLuongHienTai = sach.getSluong();
            int soLuongConLai = soLuongHienTai - sluong;

            // Đảm bảo không cho mượn quá số lượng
            if (soLuongConLai >= 0) {
                sach.setSluong(soLuongConLai);
                sachRepository.save(sach);
            }
        }

        return "redirect:/admin/sach_dang_muon";
    }


    @GetMapping("/sach_dang_muon")
    public String viewListSach_dang_muon(HttpServletRequest request, Model model, Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("");

        List<MuonSach> muonSachList = muonSachRepository.findAll();
        model.addAttribute("muonSachList", muonSachList);
        model.addAttribute("role", role);
        model.addAttribute("currentUri", request.getRequestURI());
        return "views/admin/sach_dang_muon";
    }

    @PostMapping("/tra_sach")
    public String xuLyTraSach(@RequestParam("muonSachId") int muonSachId) {
        Optional<MuonSach> optionalMuon = muonSachRepository.findById(muonSachId);
        if (optionalMuon.isPresent()) {
            MuonSach muon = optionalMuon.get();

            Optional<Sach> optionalSach = sachRepository.findById(muon.getIdSach());
            if (optionalSach.isPresent()) {
                Sach sach = optionalSach.get();
                int soLuongHienTai = sach.getSluong();
                int soLuongMuon = muon.getSluong();
                int soLuongMoi = soLuongHienTai + soLuongMuon;
                sach.setSluong(soLuongMoi);
                sachRepository.save(sach);
            }

            muonSachRepository.deleteById(muonSachId);
        }

        return "redirect:/admin/sach_dang_muon";
    }
}
