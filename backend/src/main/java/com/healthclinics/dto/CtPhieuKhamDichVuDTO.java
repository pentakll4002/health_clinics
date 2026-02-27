package com.healthclinics.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CtPhieuKhamDichVuDTO {
    private Long idCt;
    private Long idPhieuKham;
    private Long idDichVu;
    private String tenDichVu;
    private Integer soLuong;
    private BigDecimal donGiaApDung;
    private BigDecimal thanhTien;
}
