package com.gabchak.budget.service;

import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.dto.RecordResponseDto;
import java.util.List;

public interface RecordService {
  List<RecordResponseDto> findAll();

  RecordResponseDto findById(Integer id);

  RecordResponseDto create(RecordRequestDto dto);

  RecordResponseDto update(Integer id, RecordRequestDto dto);
}
