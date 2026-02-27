package com.healthclinics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoaiBenhDTO {
    private Long idLoaiBenh;
    private String tenLoaiBenh;
    private String trieuChung;
    private String huongDieuTri;
    private String moTa;
}
