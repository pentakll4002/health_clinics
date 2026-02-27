package com.healthclinics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "qui_dinh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuiDinh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_QuyDinh")
    private Long idQuyDinh;

    @Column(name = "TenQuyDinh", nullable = false)
    private String tenQuyDinh;

    @Column(name = "GiaTri", precision = 15, scale = 2)
    private BigDecimal giaTri;
}
