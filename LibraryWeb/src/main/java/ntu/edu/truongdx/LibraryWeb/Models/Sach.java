package ntu.edu.truongdx.LibraryWeb.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "sach")
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ten_sach")
    private String tenSach; // đổi từ ten_sach thành tenSach

    private String tacGia;  // đổi tac_gia thành tacGia

    @ManyToOne
    @JoinColumn(name = "id_the_loai")
    private TheLoai idTheLoai;

    @Column(name = "nam_xuat_ban")
    private int namxb;

    @Column(name = "so_luong_con")
    private int sluong;

    @Column(name = "chi_tiet")
    private String chitiet;

    @Column(name = "hinh_anh_sach")
    private String anhsach;

    public Sach() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public TheLoai getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(TheLoai idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public int getNamxb() {
        return namxb;
    }

    public void setNamxb(int namxb) {
        this.namxb = namxb;
    }

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getAnhsach() {
        return anhsach;
    }

    public void setAnhsach(String anhsach) {
        this.anhsach = anhsach;
    }
}
