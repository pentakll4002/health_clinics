package com.healthclinics.service;

import com.healthclinics.dto.NhomNguoiDungDTO;
import com.healthclinics.entity.NhomNguoiDung;
import com.healthclinics.repository.NhomNguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NhomNguoiDungService {

    private final NhomNguoiDungRepository nhomNguoiDungRepository;

    public List<NhomNguoiDungDTO> getAll() {
        return nhomNguoiDungRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public NhomNguoiDungDTO getById(Long id) {
        NhomNguoiDung nn = nhomNguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NhomNguoiDung not found"));
        return mapToDTO(nn);
    }

    public NhomNguoiDungDTO getByMaNhom(String maNhom) {
        NhomNguoiDung nn = nhomNguoiDungRepository.findByMaNhom(maNhom)
                .orElseThrow(() -> new RuntimeException("NhomNguoiDung not found"));
        return mapToDTO(nn);
    }

    @Transactional
    public NhomNguoiDungDTO create(NhomNguoiDungDTO dto) {
        if (nhomNguoiDungRepository.existsByMaNhom(dto.getMaNhom())) {
            throw new RuntimeException("MaNhom already exists");
        }
        
        NhomNguoiDung nn = NhomNguoiDung.builder()
                .tenNhom(dto.getTenNhom())
                .maNhom(dto.getMaNhom())
                .slug(dto.getSlug())
                .build();
        return mapToDTO(nhomNguoiDungRepository.save(nn));
    }

    @Transactional
    public NhomNguoiDungDTO update(Long id, NhomNguoiDungDTO dto) {
        NhomNguoiDung nn = nhomNguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NhomNguoiDung not found"));
        nn.setTenNhom(dto.getTenNhom());
        nn.setMaNhom(dto.getMaNhom());
        nn.setSlug(dto.getSlug());
        return mapToDTO(nhomNguoiDungRepository.save(nn));
    }

    @Transactional
    public void delete(Long id) {
        nhomNguoiDungRepository.deleteById(id);
    }

    private NhomNguoiDungDTO mapToDTO(NhomNguoiDung nn) {
        return NhomNguoiDungDTO.builder()
                .idNhom(nn.getIdNhom())
                .tenNhom(nn.getTenNhom())
                .maNhom(nn.getMaNhom())
                .slug(nn.getSlug())
                .build();
    }
}
