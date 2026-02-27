package com.healthclinics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhanQuyenDTO {
    private Long idNhom;
    private Long idChucNang;
    private String tenNhom;
    private String maNhom;
    private String tenChucNang;
    private String tenManHinhTuongUong;
}
