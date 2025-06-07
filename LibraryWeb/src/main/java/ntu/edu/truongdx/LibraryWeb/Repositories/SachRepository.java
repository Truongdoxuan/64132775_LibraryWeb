package ntu.edu.truongdx.LibraryWeb.Repositories;

import ntu.edu.truongdx.LibraryWeb.Models.Sach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SachRepository extends JpaRepository<Sach, Integer> {
    Page<Sach> findByTenSachContainingIgnoreCaseAndIdTheLoai(String tenSach, int idTheLoai, Pageable pageable);

    Page<Sach> findByTenSachContainingIgnoreCase(String tenSach, Pageable pageable);
}
