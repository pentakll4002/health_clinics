package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ct_phieu_kham_dich_vu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CtPhieuKhamDichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CT")
    private Long idCt;

    @Column(name = "ID_PhieuKham", nullable = false)
    private Long idPhieuKham;

    @Column(name = "ID_DichVu", nullable = false)
    private Long idDichVu;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGiaApDung", precision = 15, scale = 2)
    private BigDecimal donGiaApDung;

    @Column(name = "ThanhTien", precision = 15, scale = 2)
    private BigDecimal thanhTien;

    @Column(name = "Is_Deleted")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PhieuKham", insertable = false, updatable = false)
    private PhieuKham phieuKham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DichVu", insertable = false, updatable = false)
    private DichVu dichVu;
}
