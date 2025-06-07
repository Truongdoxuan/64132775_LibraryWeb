package ntu.edu.truongdx.LibraryWeb.Services;

import ntu.edu.truongdx.LibraryWeb.Models.Sach;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SachService {
    Optional<Sach> findById(Integer id);
}
