package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "phieu_nhap_thuoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhieuNhapThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PhieuNhapThuoc")
    private Long idPhieuNhapThuoc;

    @Column(name = "ID_NhanVien")
    private Long idNhanVien;

    @Column(name = "NgayNhap")
    private LocalDateTime ngayNhap;

    @Column(name = "TongTienNhap", precision = 15, scale = 2)
    private BigDecimal tongTienNhap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_NhanVien", insertable = false, updatable = false)
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "phieuNhapThuoc", cascade = CascadeType.ALL)
    private java.util.List<ChiTietPhieuNhapThuoc> chiTiets;
}
