package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "thuoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Thuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Thuoc")
    private Long idThuoc;

    @Column(name = "TenThuoc", nullable = false)
    private String tenThuoc;

    @Column(name = "ID_DVT")
    private Long idDvt;

    @Column(name = "ID_CachDung")
    private Long idCachDung;

    @Column(name = "ThanhPhan")
    private String thanhPhan;

    @Column(name = "XuatXu")
    private String xuatXu;

    @Column(name = "SoLuongTon")
    private Integer soLuongTon = 0;

    @Column(name = "DonGiaNhap", precision = 15, scale = 2)
    private BigDecimal donGiaNhap;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "TyLeGiaBan", precision = 5, scale = 2)
    private BigDecimal tyLeGiaBan;

    @Column(name = "DonGiaBan", precision = 15, scale = 2)
    private BigDecimal donGiaBan;

    @Column(name = "Is_Deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DVT", insertable = false, updatable = false)
    private DVT dvt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CachDung", insertable = false, updatable = false)
    private CachDung cachDung;

    @OneToMany(mappedBy = "thuoc", cascade = CascadeType.ALL)
    private java.util.List<ToaThuoc> toaThuocs;

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
