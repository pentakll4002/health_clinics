package com.healthclinics.controller;

import com.healthclinics.entity.CachDung;
import com.healthclinics.repository.CachDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cach-dung")
@RequiredArgsConstructor
public class CachDungController {

    private final CachDungRepository cachDungRepository;

    @GetMapping
    public ResponseEntity<List<CachDung>> getAll() {
        return ResponseEntity.ok(cachDungRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CachDung> create(@RequestBody CachDung cachDung) {
        return ResponseEntity.ok(cachDungRepository.save(cachDung));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CachDung> update(@PathVariable Long id, @RequestBody CachDung cachDung) {
        cachDung.setIdCachDung(id);
        return ResponseEntity.ok(cachDungRepository.save(cachDung));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cachDungRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
