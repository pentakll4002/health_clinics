package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "benh_nhan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenhNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BenhNhan")
    private Long idBenhNhan;

    @Column(name = "HoTenBN", nullable = false)
    private String hoTenBN;

    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "CCCD")
    private String cccd;

    @Column(name = "DienThoai")
    private String dienThoai;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "Email")
    private String email;

    @Column(name = "Is_Deleted")
    private Boolean isDeleted = false;

    @Column(name = "NgayDK")
    private LocalDateTime ngayDK;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "benhNhan", cascade = CascadeType.ALL)
    private java.util.List<DanhSachTiepNhan> danhSachTiepNhans;

    @OneToMany(mappedBy = "benhNhan", cascade = CascadeType.ALL)
    private java.util.List<LichKham> lichKhams;
}
