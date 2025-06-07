package ntu.edu.truongdx.LibraryWeb.Services;

import ntu.edu.truongdx.LibraryWeb.Models.Sach;
import ntu.edu.truongdx.LibraryWeb.Repositories.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SachServiceImpl implements SachService{
    @Autowired
    private SachRepository sachRepository;

    @Override
    public Optional<Sach> findById(Integer id) {

        return sachRepository.findById(id);
    }
}
