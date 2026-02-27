package com.healthclinics.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DanhSachTiepNhanDTO {
    private Long idTiepNhan;
    private Long idBenhNhan;
    private String tenBenhNhan;
    private String dienThoaiBenhNhan;
    private String cccdBenhNhan;
    private LocalDateTime ngayTN;
    private String caTN;
    private Long idNhanVien;
    private String tenNhanVien;
    private Long idLeTanDuyet;
    private String tenLeTanDuyet;
    private Boolean isDeleted;
    private String trangThaiTiepNhan;
}
