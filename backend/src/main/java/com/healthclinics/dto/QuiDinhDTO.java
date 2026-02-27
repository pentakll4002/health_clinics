package com.healthclinics.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuiDinhDTO {
    private Long idQuyDinh;
    private String tenQuyDinh;
    private BigDecimal giaTri;
}
