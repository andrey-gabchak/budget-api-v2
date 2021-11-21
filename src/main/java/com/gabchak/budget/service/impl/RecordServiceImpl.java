package com.gabchak.budget.service.impl;

import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.dto.RecordResponseDto;
import com.gabchak.budget.mapper.RecordRequestDtoMapper;
import com.gabchak.budget.mapper.RecordResponseDtoMapper;
import com.gabchak.budget.model.CategoryEntity;
import com.gabchak.budget.model.RecordEntity;
import com.gabchak.budget.model.WalletEntity;
import com.gabchak.budget.repository.CategoryRepository;
import com.gabchak.budget.repository.RecordRepository;
import com.gabchak.budget.repository.WalletRepository;
import com.gabchak.budget.service.RecordService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

  RecordRepository recordRepository;
  CategoryRepository categoryRepository;
  WalletRepository walletRepository;
  RecordResponseDtoMapper recordResponseDtoMapper;
  RecordRequestDtoMapper recordRequestDtoMapper;

  @Transactional(readOnly = true)
  @Override
  public List<RecordResponseDto> findAll() {
    return recordResponseDtoMapper.toList(recordRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public RecordResponseDto findById(Integer id) {
    RecordEntity recordEntity = findRecordEntityById(id);
    return recordResponseDtoMapper.toDto(recordEntity);
  }

  @Transactional
  @Override
  public RecordResponseDto update(Integer id, RecordRequestDto dto) {
    RecordEntity recordEntity = findRecordEntityById(id);
    RecordEntity updated = recordRequestDtoMapper.updateEntity(recordEntity, dto);
    updated.setWalletEntity(findWalletEntity(dto));
    updated.setCategoryEntity(findCategoryEntity(dto));
    return recordResponseDtoMapper.toDto(updated);
  }

  private RecordEntity findRecordEntityById(Integer id) {
    return recordRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("No record was found by id=" + id));
  }

  @Override
  public RecordResponseDto create(RecordRequestDto dto) {
    CategoryEntity categoryEntity = findCategoryEntity(dto);
    WalletEntity walletEntity = findWalletEntity(dto);
    RecordEntity recordEntity = recordRequestDtoMapper.toEntity(dto);
    recordEntity.setCategoryEntity(categoryEntity);
    recordEntity.setWalletEntity(walletEntity);
    RecordEntity saved = recordRepository.save(recordEntity);
    return recordResponseDtoMapper.toDto(saved);
  }

  private WalletEntity findWalletEntity(RecordRequestDto dto) {
    return walletRepository.findById(dto.walletId())
        .orElseThrow(() -> new NoSuchElementException("No wallet was found by id=" + dto.walletId()));
  }

  private CategoryEntity findCategoryEntity(RecordRequestDto dto) {
    return categoryRepository.findById(dto.categoryId())
        .orElseThrow(() -> new NoSuchElementException("No category was found by id=" + dto.categoryId()));
  }
}
