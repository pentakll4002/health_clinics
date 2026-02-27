package com.healthclinics.entity;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ToaThuocId implements Serializable {
    private Long idPhieuKham;
    private Long idThuoc;
}
