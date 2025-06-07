package ntu.edu.truongdx.LibraryWeb.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ten;
    private String email;

    @Column(name = "vai_tro")
    private String vaitro;

    @Column(name = "mat_khau")
    private String matkhau;

    public NguoiDung() {
    }

    public NguoiDung(int id, String ten, String email, String vaitro, String matkhau) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.vaitro = vaitro;
        this.matkhau = matkhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
