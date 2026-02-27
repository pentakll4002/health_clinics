package com.healthclinics.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DichVuDTO {
    private Long idDichVu;
    private String tenDichVu;
    private BigDecimal donGia;
    private Boolean isDeleted;
}
