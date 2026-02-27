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
public class PhieuKhamDTO {
    private Long idPhieuKham;
    private Long idTiepNhan;
    private Long idBacSi;
    private String tenBacSi;
    private String caKham;
    private String trieuChung;
    private String chanDoan;
    private Long idLoaiBenh;
    private String tenLoaiBenh;
    private Long idDichVu;
    private String tenDichVu;
    private BigDecimal tienKham;
    private BigDecimal tongTienThuoc;
    private String trangThai;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Thông tin bệnh nhân từ tiepNhan
    private Long idBenhNhan;
    private String tenBenhNhan;
    private String dienThoaiBenhNhan;
    
    // Danh sách toa thuốc
    private List<ToaThuocDTO> toaThuocs;
    
    // Danh sách dịch vụ phụ
    private List<CtPhieuKhamDichVuDTO> dichVuPhus;
}
