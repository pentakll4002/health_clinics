package com.healthclinics.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToaThuocDTO {
    private Long idPhieuKham;
    private Long idThuoc;
    private String tenThuoc;
    private Integer soLuong;
    private String cachDung;
    private BigDecimal donGiaBanLuocMua;
    private BigDecimal tienThuoc;
    private String tenDvt;
}
