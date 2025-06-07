package ntu.edu.truongdx.LibraryWeb.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "muon_sach")
public class MuonSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nguoi_dung", insertable = false, updatable = false)
    private NguoiDung nguoiDung;

    @Column(name = "id_nguoi_dung")
    private int idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sach", referencedColumnName = "id", insertable = false, updatable = false)
    private Sach sach;

    @Column(name = "id_sach")
    private int idSach;

    @Column(name = "so_luong")
    private int sluong;

    @Column(name = "ngay_muon")
    private LocalDate ngayMuon;

    @Column(name = "han_tra")
    private LocalDate hanTra;

    @Column(name = "ngay_tra_thuc_te")
    private LocalDate ngayTra;

    @Column(name = "id_trang_thai")
    private int idTrangThai;

    public MuonSach() {

    }

    public MuonSach(int id, int idUser, int idSach, int sluong, LocalDate ngayMuon, LocalDate hanTra, LocalDate ngayTra, int idTrangThai) {
        this.id = id;
        this.idUser = idUser;
        this.idSach = idSach;
        this.sluong = sluong;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.ngayTra = ngayTra;
        this.idTrangThai = idTrangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public LocalDate getHanTra() {
        return hanTra;
    }

    public void setHanTra(LocalDate hanTra) {
        this.hanTra = hanTra;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getIdTrangThai() {
        return idTrangThai;
    }

    public void setIdTrangThai(int idTrangThai) {
        this.idTrangThai = idTrangThai;
    }

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
