package com.healthclinics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private BenhNhanDTO benhNhan;
    private NhanVienDTO nhanVien;
}
