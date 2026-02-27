package com.healthclinics.service;

import com.healthclinics.dto.ChucNangDTO;
import com.healthclinics.entity.ChucNang;
import com.healthclinics.repository.ChucNangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChucNangService {

    private final ChucNangRepository chucNangRepository;

    public List<ChucNangDTO> getAll() {
        return chucNangRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ChucNangDTO getById(Long id) {
        ChucNang cn = chucNangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChucNang not found"));
        return mapToDTO(cn);
    }

    @Transactional
    public ChucNangDTO create(ChucNangDTO dto) {
        ChucNang cn = ChucNang.builder()
                .tenChucNang(dto.getTenChucNang())
                .tenManHinhTuongUong(dto.getTenManHinhTuongUong())
                .build();
        return mapToDTO(chucNangRepository.save(cn));
    }

    @Transactional
    public ChucNangDTO update(Long id, ChucNangDTO dto) {
        ChucNang cn = chucNangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChucNang not found"));
        cn.setTenChucNang(dto.getTenChucNang());
        cn.setTenManHinhTuongUong(dto.getTenManHinhTuongUong());
        return mapToDTO(chucNangRepository.save(cn));
    }

    @Transactional
    public void delete(Long id) {
        chucNangRepository.deleteById(id);
    }

    private ChucNangDTO mapToDTO(ChucNang cn) {
        return ChucNangDTO.builder()
                .idChucNang(cn.getIdChucNang())
                .tenChucNang(cn.getTenChucNang())
                .tenManHinhTuongUong(cn.getTenManHinhTuongUong())
                .build();
    }
}
