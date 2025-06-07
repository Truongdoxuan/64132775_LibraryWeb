package ntu.edu.truongdx.LibraryWeb.Models;
import jakarta.persistence.*;
@Entity
@Table(name = "the_loai")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_the_loai")
    private String tenTheLoai;
    @Column(name = "mo_ta")
    private String mota;
    public TheLoai() {}

    public TheLoai(int id, String tenTheLoai, String mota) {
        this.id = id;
        this.tenTheLoai = tenTheLoai;
        this.mota = mota;}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
    public String getMota() { return mota; }
    public void setMota(String mota) { this.mota = mota; }
    public String getTenTheLoai() {
        return tenTheLoai;
    }
}
