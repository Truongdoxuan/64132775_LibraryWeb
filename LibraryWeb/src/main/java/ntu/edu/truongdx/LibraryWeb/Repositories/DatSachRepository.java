package ntu.edu.truongdx.LibraryWeb.Repositories;

import ntu.edu.truongdx.LibraryWeb.Models.DatSach;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatSachRepository extends JpaRepository<DatSach, Integer> {
    @EntityGraph(attributePaths = "sach")
    List<DatSach> findByIdUser(int idUser);
    List<DatSach> findByIdTrangThai(int idTrangThai);
}
