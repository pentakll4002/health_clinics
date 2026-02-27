package com.healthclinics.controller;

import com.healthclinics.dto.ApiResponse;
import com.healthclinics.dto.NhomNguoiDungDTO;
import com.healthclinics.service.NhomNguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nhom-nguoi-dung")
@RequiredArgsConstructor
public class NhomNguoiDungController {

    private final NhomNguoiDungService nhomNguoiDungService;

    @GetMapping
    public ResponseEntity<List<NhomNguoiDungDTO>> getAll() {
        return ResponseEntity.ok(nhomNguoiDungService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NhomNguoiDungDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(nhomNguoiDungService.getById(id));
    }

    @GetMapping("/ma-nhom/{maNhom}")
    public ResponseEntity<NhomNguoiDungDTO> getByMaNhom(@PathVariable String maNhom) {
        return ResponseEntity.ok(nhomNguoiDungService.getByMaNhom(maNhom));
    }

    @PostMapping
    public ResponseEntity<NhomNguoiDungDTO> create(@RequestBody NhomNguoiDungDTO dto) {
        return ResponseEntity.ok(nhomNguoiDungService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NhomNguoiDungDTO> update(@PathVariable Long id, @RequestBody NhomNguoiDungDTO dto) {
        return ResponseEntity.ok(nhomNguoiDungService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        nhomNguoiDungService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("NhomNguoiDung deleted successfully", null));
    }
}
