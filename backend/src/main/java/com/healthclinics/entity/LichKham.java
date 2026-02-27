package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lich_kham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LichKham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LichKham")
    private Long idLichKham;

    @Column(name = "ID_BenhNhan", nullable = false)
    private Long idBenhNhan;

    @Column(name = "ID_BacSi")
    private Long idBacSi;

    @Column(name = "NgayKhamDuKien")
    private LocalDate ngayKhamDuKien;

    @Column(name = "CaKham")
    private String caKham;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "GhiChu", columnDefinition = "TEXT")
    private String ghiChu;

    @Column(name = "Is_Deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BenhNhan", insertable = false, updatable = false)
    private BenhNhan benhNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BacSi", insertable = false, updatable = false)
    private NhanVien bacSi;

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
