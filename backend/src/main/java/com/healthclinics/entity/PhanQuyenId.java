package com.healthclinics.entity;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PhanQuyenId implements Serializable {
    private Long idNhom;
    private Long idChucNang;
}
