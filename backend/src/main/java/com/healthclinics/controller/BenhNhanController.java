package com.healthclinics.controller;

import com.healthclinics.dto.ApiResponse;
import com.healthclinics.dto.BenhNhanDTO;
import com.healthclinics.service.BenhNhanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/benh-nhan")
@RequiredArgsConstructor
public class BenhNhanController {

    private final BenhNhanService benhNhanService;

    @GetMapping
    public ResponseEntity<List<BenhNhanDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idBenhNhan") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        
        if (page == 0 && size == 10) {
            return ResponseEntity.ok(benhNhanService.getAll());
        }
        
        Sort.Direction dir = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Page<BenhNhanDTO> result = benhNhanService.getAll(PageRequest.of(page, size, Sort.by(dir, sortBy)));
        return ResponseEntity.ok(result.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenhNhanDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(benhNhanService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BenhNhanDTO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(benhNhanService.search(keyword));
    }

    @PostMapping
    public ResponseEntity<BenhNhanDTO> create(@RequestBody BenhNhanDTO dto) {
        return ResponseEntity.ok(benhNhanService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BenhNhanDTO> update(@PathVariable Long id, @RequestBody BenhNhanDTO dto) {
        return ResponseEntity.ok(benhNhanService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        benhNhanService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("BenhNhan deleted successfully", null));
    }
}
