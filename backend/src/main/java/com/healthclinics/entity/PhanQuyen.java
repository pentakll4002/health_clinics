package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "phan_quyen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(PhanQuyenId.class)
public class PhanQuyen {

    @Id
    @Column(name = "ID_Nhom")
    private Long idNhom;

    @Id
    @Column(name = "ID_ChucNang")
    private Long idChucNang;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Nhom", insertable = false, updatable = false)
    private NhomNguoiDung nhomNguoiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ChucNang", insertable = false, updatable = false)
    private ChucNang chucNang;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
