package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NhanVien")
    private Long idNhanVien;

    @Column(name = "HoTenNV", nullable = false)
    private String hoTenNV;

    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "CCCD")
    private String cccd;

    @Column(name = "DienThoai")
    private String dienThoai;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Email")
    private String email;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "ID_Nhom")
    private Long idNhom;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Nhom", insertable = false, updatable = false)
    private NhomNguoiDung nhomNguoiDung;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private java.util.List<DanhSachTiepNhan> danhSachTiepNhans;

    @OneToMany(mappedBy = "bacSi", cascade = CascadeType.ALL)
    private java.util.List<PhieuKham> phieuKhams;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private java.util.List<HoaDon> hoaDons;
}
