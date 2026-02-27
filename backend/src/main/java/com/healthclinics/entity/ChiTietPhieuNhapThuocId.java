package com.healthclinics.entity;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChiTietPhieuNhapThuocId implements Serializable {
    private Long idPhieuNhapThuoc;
    private Long idThuoc;
}
