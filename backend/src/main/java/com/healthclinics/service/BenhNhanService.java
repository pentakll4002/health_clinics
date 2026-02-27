package com.healthclinics.service;

import com.healthclinics.dto.BenhNhanDTO;
import com.healthclinics.entity.BenhNhan;
import com.healthclinics.repository.BenhNhanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenhNhanService {

    private final BenhNhanRepository benhNhanRepository;

    public List<BenhNhanDTO> getAll() {
        return benhNhanRepository.findByIsDeletedFalse().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Page<BenhNhanDTO> getAll(Pageable pageable) {
        return benhNhanRepository.findByIsDeletedFalse(pageable)
                .map(this::mapToDTO);
    }

    public BenhNhanDTO getById(Long id) {
        BenhNhan bn = benhNhanRepository.findByIdBenhNhanAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("BenhNhan not found"));
        return mapToDTO(bn);
    }

    public List<BenhNhanDTO> search(String keyword) {
        return benhNhanRepository.searchByKeyword(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BenhNhanDTO create(BenhNhanDTO dto) {
        BenhNhan bn = BenhNhan.builder()
                .hoTenBN(dto.getHoTenBN())
                .ngaySinh(dto.getNgaySinh())
                .gioiTinh(dto.getGioiTinh())
                .cccd(dto.getCccd())
                .dienThoai(dto.getDienThoai())
                .diaChi(dto.getDiaChi())
                .email(dto.getEmail())
                .avatar(dto.getAvatar())
                .isDeleted(false)
                .ngayDK(LocalDateTime.now())
                .userId(dto.getUserId())
                .build();
        
        return mapToDTO(benhNhanRepository.save(bn));
    }

    @Transactional
    public BenhNhanDTO update(Long id, BenhNhanDTO dto) {
        BenhNhan bn = benhNhanRepository.findByIdBenhNhanAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("BenhNhan not found"));
        
        bn.setHoTenBN(dto.getHoTenBN());
        bn.setNgaySinh(dto.getNgaySinh());
        bn.setGioiTinh(dto.getGioiTinh());
        bn.setCccd(dto.getCccd());
        bn.setDienThoai(dto.getDienThoai());
        bn.setDiaChi(dto.getDiaChi());
        bn.setEmail(dto.getEmail());
        bn.setAvatar(dto.getAvatar());
        
        return mapToDTO(benhNhanRepository.save(bn));
    }

    @Transactional
    public void delete(Long id) {
        BenhNhan bn = benhNhanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BenhNhan not found"));
        bn.setIsDeleted(true);
        benhNhanRepository.save(bn);
    }

    private BenhNhanDTO mapToDTO(BenhNhan bn) {
        return BenhNhanDTO.builder()
                .idBenhNhan(bn.getIdBenhNhan())
                .hoTenBN(bn.getHoTenBN())
                .ngaySinh(bn.getNgaySinh())
                .gioiTinh(bn.getGioiTinh())
                .cccd(bn.getCccd())
                .dienThoai(bn.getDienThoai())
                .diaChi(bn.getDiaChi())
                .avatar(bn.getAvatar())
                .email(bn.getEmail())
                .isDeleted(bn.getIsDeleted())
                .ngayDK(bn.getNgayDK())
                .userId(bn.getUserId())
                .build();
    }
}
