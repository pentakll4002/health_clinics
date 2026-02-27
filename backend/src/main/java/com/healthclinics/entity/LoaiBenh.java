package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loai_benh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoaiBenh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LoaiBenh")
    private Long idLoaiBenh;

    @Column(name = "TenLoaiBenh", nullable = false)
    private String tenLoaiBenh;

    @Column(name = "TrieuChung", columnDefinition = "TEXT")
    private String trieuChung;

    @Column(name = "HuongDieuTri", columnDefinition = "TEXT")
    private String huongDieuTri;

    @Column(name = "MoTa", columnDefinition = "TEXT")
    private String moTa;

    @OneToMany(mappedBy = "loaiBenh", cascade = CascadeType.ALL)
    private java.util.List<PhieuKham> phieuKhams;
}
