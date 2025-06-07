package ntu.edu.truongdx.LibraryWeb.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "trang_thai")
public class TrangThai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ten_trang_thai")
    private String tenTrangThai;

    public TrangThai() {

    }

    public TrangThai(int id, String tenTrangThai) {
        this.id = id;
        this.tenTrangThai = tenTrangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }
}
