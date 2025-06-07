package ntu.edu.truongdx.LibraryWeb.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dat_truoc")
public class DatSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nguoi_dung", insertable = false, updatable = false)
    private NguoiDung nguoiDung;
    @Column(name = "id_nguoi_dung")
    private int idUser;

    @Column(name = "id_sach")
    private int idSach;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sach", referencedColumnName = "id", insertable = false, updatable = false)
    private Sach sach;

    @Column(name = "so_luong")
    private int sluong;

    @Column(name = "ngay_dat")
    private LocalDate ngayDat;

    @Column(name = "id_trang_thai")
    private int idTrangThai;

    // Constructors
    public DatSach() {}

    public DatSach(int idUser, int idSach, int sluong, LocalDate ngayDat, int idTrangThai) {
        this.idUser = idUser;
        this.idSach = idSach;
        this.sluong = sluong;
        this.ngayDat = ngayDat;
        this.idTrangThai = idTrangThai;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public int getIdSach() { return idSach; }
    public void setIdSach(int idSach) { this.idSach = idSach; }

    public Sach getSach() { return sach; }
    public void setSach(Sach sach) { this.sach = sach; }

    public int getSluong() { return sluong; }
    public void setSluong(int sluong) { this.sluong = sluong; }

    public LocalDate getNgayDat() { return ngayDat; }
    public void setNgayDat(LocalDate ngayDat) { this.ngayDat = ngayDat; }

    public int getIdTrangThai() { return idTrangThai; }
    public void setIdTrangThai(int idTrangThai) { this.idTrangThai = idTrangThai; }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
