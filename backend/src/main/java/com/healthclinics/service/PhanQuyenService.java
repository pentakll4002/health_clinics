package com.healthclinics.service;

import com.healthclinics.dto.ChucNangDTO;
import com.healthclinics.dto.PhanQuyenDTO;
import com.healthclinics.entity.ChucNang;
import com.healthclinics.entity.NhomNguoiDung;
import com.healthclinics.entity.PhanQuyen;
import com.healthclinics.entity.PhanQuyenId;
import com.healthclinics.repository.ChucNangRepository;
import com.healthclinics.repository.NhomNguoiDungRepository;
import com.healthclinics.repository.PhanQuyenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhanQuyenService {

    private final PhanQuyenRepository phanQuyenRepository;
    private final NhomNguoiDungRepository nhomNguoiDungRepository;
    private final ChucNangRepository chucNangRepository;

    public List<PhanQuyenDTO> getAll() {
        return phanQuyenRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PhanQuyenDTO> getByNhom(Long idNhom) {
        return phanQuyenRepository.findByIdNhomWithChucNang(idNhom).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PhanQuyenDTO> getByChucNang(Long idChucNang) {
        return phanQuyenRepository.findByIdChucNang(idChucNang).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PhanQuyenDTO create(Long idNhom, Long idChucNang) {
        NhomNguoiDung nhom = nhomNguoiDungRepository.findById(idNhom)
                .orElseThrow(() -> new RuntimeException("NhomNguoiDung not found"));
        ChucNang chucNang = chucNangRepository.findById(idChucNang)
                .orElseThrow(() -> new RuntimeException("ChucNang not found"));

        PhanQuyenId id = new PhanQuyenId(idNhom, idChucNang);
        if (phanQuyenRepository.existsById(id)) {
            throw new RuntimeException("PhanQuyen already exists");
        }

        PhanQuyen pq = PhanQuyen.builder()
                .idNhom(idNhom)
                .idChucNang(idChucNang)
                .build();
        
        return mapToDTO(phanQuyenRepository.save(pq));
    }

    @Transactional
    public void assignMultiple(Long idNhom, List<Long> idChucNangs) {
        // Delete existing
        List<PhanQuyen> existing = phanQuyenRepository.findByIdNhom(idNhom);
        phanQuyenRepository.deleteAll(existing);
        
        // Add new
        for (Long idChucNang : idChucNangs) {
            PhanQuyen pq = PhanQuyen.builder()
                    .idNhom(idNhom)
                    .idChucNang(idChucNang)
                    .build();
            phanQuyenRepository.save(pq);
        }
    }

    @Transactional
    public void delete(Long idNhom, Long idChucNang) {
        phanQuyenRepository.deleteByIdNhomAndIdChucNang(idNhom, idChucNang);
    }

    private PhanQuyenDTO mapToDTO(PhanQuyen pq) {
        PhanQuyenDTO dto = PhanQuyenDTO.builder()
                .idNhom(pq.getIdNhom())
                .idChucNang(pq.getIdChucNang())
                .build();
        
        if (pq.getNhomNguoiDung() != null) {
            dto.setTenNhom(pq.getNhomNguoiDung().getTenNhom());
            dto.setMaNhom(pq.getNhomNguoiDung().getMaNhom());
        }
        if (pq.getChucNang() != null) {
            dto.setTenChucNang(pq.getChucNang().getTenChucNang());
            dto.setTenManHinhTuongUong(pq.getChucNang().getTenManHinhTuongUong());
        }
        
        return dto;
    }
}
