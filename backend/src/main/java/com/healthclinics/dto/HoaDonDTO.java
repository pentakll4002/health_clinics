package com.healthclinics.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonDTO {
    private Long idHoaDon;
    private Long idPhieuKham;
    private Long idNhanVien;
    private String tenNhanVien;
    private LocalDate ngayHoaDon;
    private BigDecimal tienKham;
    private BigDecimal tienThuoc;
    private BigDecimal tienDichVu;
    private BigDecimal tongTien;
    
    // Thông tin bệnh nhân
    private Long idBenhNhan;
    private String tenBenhNhan;
}
