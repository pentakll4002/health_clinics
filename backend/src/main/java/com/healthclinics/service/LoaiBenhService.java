package com.healthclinics.service;

import com.healthclinics.dto.LoaiBenhDTO;
import com.healthclinics.entity.LoaiBenh;
import com.healthclinics.repository.LoaiBenhRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoaiBenhService {

    private final LoaiBenhRepository loaiBenhRepository;

    public List<LoaiBenhDTO> getAll() {
        return loaiBenhRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public LoaiBenhDTO getById(Long id) {
        LoaiBenh lb = loaiBenhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LoaiBenh not found"));
        return mapToDTO(lb);
    }

    public List<LoaiBenhDTO> search(String keyword) {
        return loaiBenhRepository.searchByKeyword(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public LoaiBenhDTO create(LoaiBenhDTO dto) {
        LoaiBenh lb = LoaiBenh.builder()
                .tenLoaiBenh(dto.getTenLoaiBenh())
                .trieuChung(dto.getTrieuChung())
                .huongDieuTri(dto.getHuongDieuTri())
                .moTa(dto.getMoTa())
                .build();
        return mapToDTO(loaiBenhRepository.save(lb));
    }

    @Transactional
    public LoaiBenhDTO update(Long id, LoaiBenhDTO dto) {
        LoaiBenh lb = loaiBenhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LoaiBenh not found"));
        lb.setTenLoaiBenh(dto.getTenLoaiBenh());
        lb.setTrieuChung(dto.getTrieuChung());
        lb.setHuongDieuTri(dto.getHuongDieuTri());
        lb.setMoTa(dto.getMoTa());
        return mapToDTO(loaiBenhRepository.save(lb));
    }

    @Transactional
    public void delete(Long id) {
        loaiBenhRepository.deleteById(id);
    }

    private LoaiBenhDTO mapToDTO(LoaiBenh lb) {
        return LoaiBenhDTO.builder()
                .idLoaiBenh(lb.getIdLoaiBenh())
                .tenLoaiBenh(lb.getTenLoaiBenh())
                .trieuChung(lb.getTrieuChung())
                .huongDieuTri(lb.getHuongDieuTri())
                .moTa(lb.getMoTa())
                .build();
    }
}
