package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "phieu_kham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhieuKham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PhieuKham")
    private Long idPhieuKham;

    @Column(name = "ID_TiepNhan")
    private Long idTiepNhan;

    @Column(name = "ID_BacSi")
    private Long idBacSi;

    @Column(name = "CaKham")
    private String caKham;

    @Column(name = "TrieuChung", columnDefinition = "TEXT")
    private String trieuChung;

    @Column(name = "ChanDoan", columnDefinition = "TEXT")
    private String chanDoan;

    @Column(name = "ID_LoaiBenh")
    private Long idLoaiBenh;

    @Column(name = "ID_DichVu")
    private Long idDichVu;

    @Column(name = "TienKham", precision = 15, scale = 2)
    private BigDecimal tienKham;

    @Column(name = "TongTienThuoc", precision = 15, scale = 2)
    private BigDecimal tongTienThuoc;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "Is_Deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TiepNhan", insertable = false, updatable = false)
    private DanhSachTiepNhan tiepNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BacSi", insertable = false, updatable = false)
    private NhanVien bacSi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LoaiBenh", insertable = false, updatable = false)
    private LoaiBenh loaiBenh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DichVu", insertable = false, updatable = false)
    private DichVu dichVu;

    @OneToMany(mappedBy = "phieuKham", cascade = CascadeType.ALL)
    private java.util.List<ToaThuoc> toaThuocs;

    @OneToMany(mappedBy = "phieuKham", cascade = CascadeType.ALL)
    private java.util.List<CtPhieuKhamDichVu> ctDichVuPhus;

    @OneToOne(mappedBy = "phieuKham", cascade = CascadeType.ALL)
    private HoaDon hoaDon;

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
