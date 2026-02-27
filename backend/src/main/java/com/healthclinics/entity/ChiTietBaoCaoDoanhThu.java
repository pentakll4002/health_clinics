package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "chi_tiet_bao_cao_doanh_thu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietBaoCaoDoanhThu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CTBCDT")
    private Long idCtBcdt;

    @Column(name = "ID_BCDT")
    private Long idBcdt;

    @Column(name = "Ngay")
    private Integer ngay;

    @Column(name = "SoBenhNhan")
    private Integer soBenhNhan;

    @Column(name = "DoanhThuThuoc", precision = 15, scale = 2)
    private BigDecimal doanhThuThuoc;

    @Column(name = "DoanhThuKham", precision = 15, scale = 2)
    private BigDecimal doanhThuKham;

    @Column(name = "DoanhThuDichVu", precision = 15, scale = 2)
    private BigDecimal doanhThuDichVu;

    @Column(name = "TongDoanhThuNgay", precision = 15, scale = 2)
    private BigDecimal tongDoanhThuNgay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BCDT", insertable = false, updatable = false)
    private BaoCaoDoanhThu baoCaoDoanhThu;
}
