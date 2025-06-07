package ntu.edu.truongdx.LibraryWeb.Repositories;

import ntu.edu.truongdx.LibraryWeb.Models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    NguoiDung findByEmail(String email);
}
