package com.healthclinics.service;

import com.healthclinics.dto.DichVuDTO;
import com.healthclinics.entity.DichVu;
import com.healthclinics.repository.DichVuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DichVuService {

    private final DichVuRepository dichVuRepository;

    public List<DichVuDTO> getAll() {
        return dichVuRepository.findByIsDeletedFalse().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Page<DichVuDTO> getAll(Pageable pageable) {
        return dichVuRepository.findByIsDeletedFalse(pageable)
                .map(this::mapToDTO);
    }

    public DichVuDTO getById(Long id) {
        DichVu dv = dichVuRepository.findByIdDichVuAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("DichVu not found"));
        return mapToDTO(dv);
    }

    public List<DichVuDTO> search(String keyword) {
        return dichVuRepository.searchByKeyword(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DichVuDTO create(DichVuDTO dto) {
        DichVu dv = DichVu.builder()
                .tenDichVu(dto.getTenDichVu())
                .donGia(dto.getDonGia())
                .isDeleted(false)
                .build();
        return mapToDTO(dichVuRepository.save(dv));
    }

    @Transactional
    public DichVuDTO update(Long id, DichVuDTO dto) {
        DichVu dv = dichVuRepository.findByIdDichVuAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("DichVu not found"));
        dv.setTenDichVu(dto.getTenDichVu());
        dv.setDonGia(dto.getDonGia());
        return mapToDTO(dichVuRepository.save(dv));
    }

    @Transactional
    public void delete(Long id) {
        DichVu dv = dichVuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DichVu not found"));
        dv.setIsDeleted(true);
        dichVuRepository.save(dv);
    }

    private DichVuDTO mapToDTO(DichVu dv) {
        return DichVuDTO.builder()
                .idDichVu(dv.getIdDichVu())
                .tenDichVu(dv.getTenDichVu())
                .donGia(dv.getDonGia())
                .isDeleted(dv.getIsDeleted())
                .build();
    }
}
