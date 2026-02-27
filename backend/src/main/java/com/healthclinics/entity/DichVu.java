package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "dich_vu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DichVu")
    private Long idDichVu;

    @Column(name = "TenDichVu", nullable = false)
    private String tenDichVu;

    @Column(name = "DonGia", precision = 15, scale = 2)
    private BigDecimal donGia;

    @Column(name = "Is_Deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "dichVu", cascade = CascadeType.ALL)
    private java.util.List<PhieuKham> phieuKhams;

    @OneToMany(mappedBy = "dichVu", cascade = CascadeType.ALL)
    private java.util.List<CtPhieuKhamDichVu> ctPhieuKhamDichVus;

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
