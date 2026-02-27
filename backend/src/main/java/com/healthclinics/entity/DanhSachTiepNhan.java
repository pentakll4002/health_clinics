package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "danh_sach_tiep_nhan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DanhSachTiepNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TiepNhan")
    private Long idTiepNhan;

    @Column(name = "ID_BenhNhan", nullable = false)
    private Long idBenhNhan;

    @Column(name = "NgayTN")
    private LocalDateTime ngayTN;

    @Column(name = "CaTN")
    private String caTN;

    @Column(name = "ID_NhanVien")
    private Long idNhanVien;

    @Column(name = "ID_LeTanDuyet")
    private Long idLeTanDuyet;

    @Column(name = "Is_Deleted")
    private Boolean isDeleted = false;

    @Column(name = "TrangThaiTiepNhan")
    private String trangThaiTiepNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BenhNhan", insertable = false, updatable = false)
    private BenhNhan benhNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_NhanVien", insertable = false, updatable = false)
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LeTanDuyet", insertable = false, updatable = false)
    private NhanVien leTanDuyet;

    @OneToMany(mappedBy = "tiepNhan", cascade = CascadeType.ALL)
    private java.util.List<PhieuKham> phieuKhams;
}
