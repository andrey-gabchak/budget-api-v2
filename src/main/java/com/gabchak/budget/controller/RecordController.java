package com.gabchak.budget.controller;

import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.dto.RecordResponseDto;
import com.gabchak.budget.service.RecordService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RecordController {

  RecordService recordService;

  @GetMapping("/records")
  public ResponseEntity<List<RecordResponseDto>> findAll() {
    return ResponseEntity.ok(recordService.findAll());
  }

  @GetMapping("/records/{id}")
  public ResponseEntity<RecordResponseDto> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(recordService.findById(id));
  }

  @PostMapping("/records")
  public ResponseEntity<RecordResponseDto> create(@RequestBody RecordRequestDto dto) {
    return ResponseEntity.ok(recordService.create(dto));
  }

  @PutMapping("/records/{id}")
  public ResponseEntity<RecordResponseDto> update(@PathVariable Integer id, @RequestBody RecordRequestDto dto) {
    return ResponseEntity.ok(recordService.update(id, dto));
  }
}
