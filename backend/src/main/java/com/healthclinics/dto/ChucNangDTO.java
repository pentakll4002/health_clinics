package com.healthclinics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChucNangDTO {
    private Long idChucNang;
    private String tenChucNang;
    private String tenManHinhTuongUong;
}
