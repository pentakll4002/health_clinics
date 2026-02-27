package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chuc_nang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChucNang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ChucNang")
    private Long idChucNang;

    @Column(name = "TenChucNang", nullable = false)
    private String tenChucNang;

    @Column(name = "TenManHinhTuongUong")
    private String tenManHinhTuongUong;

    @OneToMany(mappedBy = "chucNang", cascade = CascadeType.ALL)
    private java.util.List<PhanQuyen> phanQuyens;
}
