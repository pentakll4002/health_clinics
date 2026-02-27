package com.healthclinics.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LichKhamDTO {
    private Long idLichKham;
    private Long idBenhNhan;
    private String tenBenhNhan;
    private String dienThoaiBenhNhan;
    private Long idBacSi;
    private String tenBacSi;
    private LocalDate ngayKhamDuKien;
    private String caKham;
    private String trangThai;
    private String ghiChu;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
