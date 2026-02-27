package com.healthclinics.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThuocDTO {
    private Long idThuoc;
    private String tenThuoc;
    private Long idDvt;
    private String tenDvt;
    private Long idCachDung;
    private String moTaCachDung;
    private String thanhPhan;
    private String xuatXu;
    private Integer soLuongTon;
    private BigDecimal donGiaNhap;
    private String hinhAnh;
    private BigDecimal tyLeGiaBan;
    private BigDecimal donGiaBan;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
