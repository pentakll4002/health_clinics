package com.healthclinics.controller;

import com.healthclinics.dto.ApiResponse;
import com.healthclinics.dto.HoaDonDTO;
import com.healthclinics.service.HoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class HoaDonController {

    private final HoaDonService hoaDonService;

    @GetMapping
    public ResponseEntity<List<HoaDonDTO>> getAll() {
        return ResponseEntity.ok(hoaDonService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoaDonDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hoaDonService.getById(id));
    }

    @GetMapping("/preview/{phieuKhamId}")
    public ResponseEntity<HoaDonDTO> preview(@PathVariable Long phieuKhamId) {
        return ResponseEntity.ok(hoaDonService.preview(phieuKhamId));
    }

    @PostMapping
    public ResponseEntity<HoaDonDTO> create(@RequestBody Map<String, Long> body,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        Long phieuKhamId = body.get("phieuKhamId");
        Long nhanVienId = body.get("nhanVienId");
        return ResponseEntity.ok(hoaDonService.create(phieuKhamId, nhanVienId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {
        hoaDonService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("HoaDon deleted successfully", null));
    }
}
