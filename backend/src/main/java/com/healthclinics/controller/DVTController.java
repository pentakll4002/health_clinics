package com.healthclinics.controller;

import com.healthclinics.entity.DVT;
import com.healthclinics.repository.DVTRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dvt")
@RequiredArgsConstructor
public class DVTController {

    private final DVTRepository dvtRepository;

    @GetMapping
    public ResponseEntity<List<DVT>> getAll() {
        return ResponseEntity.ok(dvtRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<DVT> create(@RequestBody DVT dvt) {
        return ResponseEntity.ok(dvtRepository.save(dvt));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DVT> update(@PathVariable Long id, @RequestBody DVT dvt) {
        dvt.setIdDvt(id);
        return ResponseEntity.ok(dvtRepository.save(dvt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        dvtRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
