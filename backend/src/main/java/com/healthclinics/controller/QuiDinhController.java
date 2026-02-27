package com.healthclinics.controller;

import com.healthclinics.dto.ApiResponse;
import com.healthclinics.dto.QuiDinhDTO;
import com.healthclinics.service.QuiDinhService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qui-dinh")
@RequiredArgsConstructor
public class QuiDinhController {

    private final QuiDinhService quiDinhService;

    @GetMapping
    public ResponseEntity<List<QuiDinhDTO>> getAll() {
        return ResponseEntity.ok(quiDinhService.getAll());
    }

    @GetMapping("/{tenQuyDinh}")
    public ResponseEntity<QuiDinhDTO> getByTen(@PathVariable String tenQuyDinh) {
        return ResponseEntity.ok(quiDinhService.getByTen(tenQuyDinh));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> update(@RequestBody List<QuiDinhDTO> dtos) {
        for (QuiDinhDTO dto : dtos) {
            if (dto.getIdQuyDinh() != null) {
                quiDinhService.update(dto.getIdQuyDinh(), dto);
            }
        }
        return ResponseEntity.ok(ApiResponse.success("QuiDinh updated successfully", null));
    }
}
