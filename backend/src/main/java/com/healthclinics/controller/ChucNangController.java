package com.healthclinics.controller;

import com.healthclinics.dto.ApiResponse;
import com.healthclinics.dto.ChucNangDTO;
import com.healthclinics.service.ChucNangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chuc-nang")
@RequiredArgsConstructor
public class ChucNangController {

    private final ChucNangService chucNangService;

    @GetMapping
    public ResponseEntity<List<ChucNangDTO>> getAll() {
        return ResponseEntity.ok(chucNangService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChucNangDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(chucNangService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ChucNangDTO> create(@RequestBody ChucNangDTO dto) {
        return ResponseEntity.ok(chucNangService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChucNangDTO> update(@PathVariable Long id, @RequestBody ChucNangDTO dto) {
        return ResponseEntity.ok(chucNangService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        chucNangService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("ChucNang deleted successfully", null));
    }
}
