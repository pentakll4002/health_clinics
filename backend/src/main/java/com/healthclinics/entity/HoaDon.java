package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HoaDon")
    private Long idHoaDon;

    @Column(name = "ID_PhieuKham", nullable = false)
    private Long idPhieuKham;

    @Column(name = "ID_NhanVien")
    private Long idNhanVien;

    @Column(name = "NgayHoaDon")
    private LocalDate ngayHoaDon;

    @Column(name = "TienKham", precision = 15, scale = 2)
    private BigDecimal tienKham;

    @Column(name = "TienThuoc", precision = 15, scale = 2)
    private BigDecimal tienThuoc;

    @Column(name = "TienDichVu", precision = 15, scale = 2)
    private BigDecimal tienDichVu;

    @Column(name = "TongTien", precision = 15, scale = 2)
    private BigDecimal tongTien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_NhanVien", insertable = false, updatable = false)
    private NhanVien nhanVien;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PhieuKham", insertable = false, updatable = false)
    private PhieuKham phieuKham;
}
