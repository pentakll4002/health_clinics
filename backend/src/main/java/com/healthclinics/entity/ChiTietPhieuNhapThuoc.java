package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "chi_tiet_phieu_nhap_thuoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ChiTietPhieuNhapThuocId.class)
public class ChiTietPhieuNhapThuoc {

    @Id
    @Column(name = "ID_PhieuNhapThuoc")
    private Long idPhieuNhapThuoc;

    @Id
    @Column(name = "ID_Thuoc")
    private Long idThuoc;

    @Column(name = "HanSuDung")
    private LocalDate hanSuDung;

    @Column(name = "SoLuongNhap")
    private Integer soLuongNhap;

    @Column(name = "DonGiaNhap", precision = 15, scale = 2)
    private BigDecimal donGiaNhap;

    @Column(name = "ThanhTien", precision = 15, scale = 2)
    private BigDecimal thanhTien;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PhieuNhapThuoc", insertable = false, updatable = false)
    private PhieuNhapThuoc phieuNhapThuoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Thuoc", insertable = false, updatable = false)
    private Thuoc thuoc;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
