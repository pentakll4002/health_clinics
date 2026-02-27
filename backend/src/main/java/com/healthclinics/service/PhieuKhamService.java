package com.healthclinics.service;

import com.healthclinics.dto.DanhSachTiepNhanDTO;
import com.healthclinics.dto.PhieuKhamDTO;
import com.healthclinics.dto.ToaThuocDTO;
import com.healthclinics.dto.CtPhieuKhamDichVuDTO;
import com.healthclinics.entity.*;
import com.healthclinics.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhieuKhamService {

    private final PhieuKhamRepository phieuKhamRepository;
    private final DanhSachTiepNhanRepository danhSachTiepNhanRepository;
    private final ThuocRepository thuocRepository;
    private final DichVuRepository dichVuRepository;
    private final ToaThuocRepository toaThuocRepository;
    private final CtPhieuKhamDichVuRepository ctPhieuKhamDichVuRepository;
    private final QuiDinhRepository quiDinhRepository;

    public List<PhieuKhamDTO> getAll() {
        return phieuKhamRepository.findByIsDeletedFalse().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Page<PhieuKhamDTO> getAll(Pageable pageable) {
        return phieuKhamRepository.findByIsDeletedFalse(pageable)
                .map(this::mapToDTO);
    }

    public PhieuKhamDTO getById(Long id) {
        PhieuKham pk = phieuKhamRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("PhieuKham not found"));
        PhieuKhamDTO dto = mapToDTO(pk);
        
        // Load toa thuoc
        dto.setToaThuocs(toaThuocRepository.findByIdPhieuKham(id).stream()
                .map(this::mapToaThuocDTO)
                .collect(Collectors.toList()));
        
        // Load dich vu phu
        dto.setDichVuPhus(ctPhieuKhamDichVuRepository.findByIdPhieuKhamAndIsDeletedFalse(id).stream()
                .map(this::mapCtDichVuDTO)
                .collect(Collectors.toList()));
        
        return dto;
    }

    public List<PhieuKhamDTO> getByTiepNhan(Long idTiepNhan) {
        return phieuKhamRepository.findByIdTiepNhan(idTiepNhan).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PhieuKhamDTO> getByBacSi(Long idBacSi) {
        return phieuKhamRepository.findByIdBacSi(idBacSi).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PhieuKhamDTO> getByTrangThai(String trangThai) {
        return phieuKhamRepository.findByTrangThai(trangThai).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PhieuKhamDTO create(Long idTiepNhan) {
        DanhSachTiepNhan tiepNhan = danhSachTiepNhanRepository.findById(idTiepNhan)
                .orElseThrow(() -> new RuntimeException("TiepNhan not found"));

        // Get default examination fee
        BigDecimal tienKham = quiDinhRepository.findByTenQuyDinh("TienKham")
                .map(QuiDinh::getGiaTri)
                .orElse(BigDecimal.ZERO);

        PhieuKham pk = PhieuKham.builder()
                .idTiepNhan(idTiepNhan)
                .tienKham(tienKham)
                .tongTienThuoc(BigDecimal.ZERO)
                .trangThai("cho_kham")
                .isDeleted(false)
                .build();
        
        return mapToDTO(phieuKhamRepository.save(pk));
    }

    @Transactional
    public PhieuKhamDTO update(Long id, PhieuKhamDTO dto) {
        PhieuKham pk = phieuKhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PhieuKham not found"));
        
        pk.setIdBacSi(dto.getIdBacSi());
        pk.setCaKham(dto.getCaKham());
        pk.setTrieuChung(dto.getTrieuChung());
        pk.setChanDoan(dto.getChanDoan());
        pk.setIdLoaiBenh(dto.getIdLoaiBenh());
        pk.setIdDichVu(dto.getIdDichVu());
        
        if (pk.getTrangThai().equals("cho_kham")) {
            pk.setTrangThai("dang_kham");
        }
        
        return mapToDTO(phieuKhamRepository.save(pk));
    }

    @Transactional
    public ToaThuocDTO addToaThuoc(Long phieuKhamId, ToaThuocDTO dto) {
        Thuoc thuoc = thuocRepository.findByIdThuocAndIsDeletedFalse(dto.getIdThuoc())
                .orElseThrow(() -> new RuntimeException("Thuoc not found"));

        BigDecimal tienThuoc = thuoc.getDonGiaBan().multiply(BigDecimal.valueOf(dto.getSoLuong()));

        ToaThuoc toaThuoc = ToaThuoc.builder()
                .idPhieuKham(phieuKhamId)
                .idThuoc(dto.getIdThuoc())
                .soLuong(dto.getSoLuong())
                .cachDung(dto.getCachDung())
                .donGiaBanLuocMua(thuoc.getDonGiaBan())
                .tienThuoc(tienThuoc)
                .build();
        
        toaThuocRepository.save(toaThuoc);
        updateTongTienThuoc(phieuKhamId);
        
        return mapToaThuocDTO(toaThuoc);
    }

    @Transactional
    public void removeToaThuoc(Long phieuKhamId, Long thuocId) {
        toaThuocRepository.deleteByIdPhieuKhamAndIdThuoc(phieuKhamId, thuocId);
        updateTongTienThuoc(phieuKhamId);
    }

    @Transactional
    public CtPhieuKhamDichVuDTO addDichVuPhu(Long phieuKhamId, CtPhieuKhamDichVuDTO dto) {
        DichVu dichVu = dichVuRepository.findByIdDichVuAndIsDeletedFalse(dto.getIdDichVu())
                .orElseThrow(() -> new RuntimeException("DichVu not found"));

        BigDecimal thanhTien = dichVu.getDonGia().multiply(BigDecimal.valueOf(dto.getSoLuong()));

        CtPhieuKhamDichVu ct = CtPhieuKhamDichVu.builder()
                .idPhieuKham(phieuKhamId)
                .idDichVu(dto.getIdDichVu())
                .soLuong(dto.getSoLuong())
                .donGiaApDung(dichVu.getDonGia())
                .thanhTien(thanhTien)
                .isDeleted(false)
                .build();
        
        return mapCtDichVuDTO(ctPhieuKhamDichVuRepository.save(ct));
    }

    @Transactional
    public void removeDichVuPhu(Long phieuKhamId, Long dichVuId) {
        ctPhieuKhamDichVuRepository.deleteByIdPhieuKhamAndIdDichVu(phieuKhamId, dichVuId);
    }

    @Transactional
    public PhieuKhamDTO complete(Long id) {
        PhieuKham pk = phieuKhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PhieuKham not found"));
        
        pk.setTrangThai("hoan_thanh");
        
        // Update drug quantities
        List<ToaThuoc> toaThuocs = toaThuocRepository.findByIdPhieuKham(id);
        for (ToaThuoc tt : toaThuocs) {
            Thuoc thuoc = thuocRepository.findById(tt.getIdThuoc())
                    .orElseThrow(() -> new RuntimeException("Thuoc not found"));
            thuoc.setSoLuongTon(thuoc.getSoLuongTon() - tt.getSoLuong());
            thuocRepository.save(thuoc);
        }
        
        return mapToDTO(phieuKhamRepository.save(pk));
    }

    private void updateTongTienThuoc(Long phieuKhamId) {
        List<ToaThuoc> toaThuocs = toaThuocRepository.findByIdPhieuKham(phieuKhamId);
        BigDecimal tongTien = toaThuocs.stream()
                .map(ToaThuoc::getTienThuoc)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        PhieuKham pk = phieuKhamRepository.findById(phieuKhamId).orElseThrow();
        pk.setTongTienThuoc(tongTien);
        phieuKhamRepository.save(pk);
    }

    private PhieuKhamDTO mapToDTO(PhieuKham pk) {
        PhieuKhamDTO dto = PhieuKhamDTO.builder()
                .idPhieuKham(pk.getIdPhieuKham())
                .idTiepNhan(pk.getIdTiepNhan())
                .idBacSi(pk.getIdBacSi())
                .caKham(pk.getCaKham())
                .trieuChung(pk.getTrieuChung())
                .chanDoan(pk.getChanDoan())
                .idLoaiBenh(pk.getIdLoaiBenh())
                .idDichVu(pk.getIdDichVu())
                .tienKham(pk.getTienKham())
                .tongTienThuoc(pk.getTongTienThuoc())
                .trangThai(pk.getTrangThai())
                .isDeleted(pk.getIsDeleted())
                .createdAt(pk.getCreatedAt())
                .updatedAt(pk.getUpdatedAt())
                .build();
        
        if (pk.getBacSi() != null) {
            dto.setTenBacSi(pk.getBacSi().getHoTenNV());
        }
        if (pk.getLoaiBenh() != null) {
            dto.setTenLoaiBenh(pk.getLoaiBenh().getTenLoaiBenh());
        }
        if (pk.getDichVu() != null) {
            dto.setTenDichVu(pk.getDichVu().getTenDichVu());
        }
        if (pk.getTiepNhan() != null) {
            DanhSachTiepNhan tn = pk.getTiepNhan();
            dto.setIdBenhNhan(tn.getIdBenhNhan());
            if (tn.getBenhNhan() != null) {
                dto.setTenBenhNhan(tn.getBenhNhan().getHoTenBN());
                dto.setDienThoaiBenhNhan(tn.getBenhNhan().getDienThoai());
            }
        }
        
        return dto;
    }

    private ToaThuocDTO mapToaThuocDTO(ToaThuoc tt) {
        ToaThuocDTO dto = ToaThuocDTO.builder()
                .idPhieuKham(tt.getIdPhieuKham())
                .idThuoc(tt.getIdThuoc())
                .soLuong(tt.getSoLuong())
                .cachDung(tt.getCachDung())
                .donGiaBanLuocMua(tt.getDonGiaBanLuocMua())
                .tienThuoc(tt.getTienThuoc())
                .build();
        
        if (tt.getThuoc() != null) {
            dto.setTenThuoc(tt.getThuoc().getTenThuoc());
            if (tt.getThuoc().getDvt() != null) {
                dto.setTenDvt(tt.getThuoc().getDvt().getTenDvt());
            }
        }
        
        return dto;
    }

    private CtPhieuKhamDichVuDTO mapCtDichVuDTO(CtPhieuKhamDichVu ct) {
        CtPhieuKhamDichVuDTO dto = CtPhieuKhamDichVuDTO.builder()
                .idCt(ct.getIdCt())
                .idPhieuKham(ct.getIdPhieuKham())
                .idDichVu(ct.getIdDichVu())
                .soLuong(ct.getSoLuong())
                .donGiaApDung(ct.getDonGiaApDung())
                .thanhTien(ct.getThanhTien())
                .build();
        
        if (ct.getDichVu() != null) {
            dto.setTenDichVu(ct.getDichVu().getTenDichVu());
        }
        
        return dto;
    }
}
