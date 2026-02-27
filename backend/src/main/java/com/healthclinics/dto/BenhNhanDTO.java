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
public class BenhNhanDTO {
    private Long idBenhNhan;
    private String hoTenBN;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String cccd;
    private String dienThoai;
    private String diaChi;
    private String avatar;
    private String email;
    private Boolean isDeleted;
    private LocalDateTime ngayDK;
    private Long userId;
}
