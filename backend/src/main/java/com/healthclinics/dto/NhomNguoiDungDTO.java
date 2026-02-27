package com.healthclinics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhomNguoiDungDTO {
    private Long idNhom;
    private String tenNhom;
    private String maNhom;
    private String slug;
}
