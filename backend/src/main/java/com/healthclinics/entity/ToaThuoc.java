package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "toa_thuoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ToaThuocId.class)
public class ToaThuoc {

    @Id
    @Column(name = "ID_PhieuKham")
    private Long idPhieuKham;

    @Id
    @Column(name = "ID_Thuoc")
    private Long idThuoc;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "CachDung")
    private String cachDung;

    @Column(name = "DonGiaBan_LuocMua", precision = 15, scale = 2)
    private BigDecimal donGiaBanLuocMua;

    @Column(name = "TienThuoc", precision = 15, scale = 2)
    private BigDecimal tienThuoc;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PhieuKham", insertable = false, updatable = false)
    private PhieuKham phieuKham;

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
