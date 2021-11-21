package com.gabchak.budget.service.impl;

import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.dto.RecordResponseDto;
import com.gabchak.budget.mapper.RecordRequestDtoMapper;
import com.gabchak.budget.mapper.RecordResponseDtoMapper;
import com.gabchak.budget.mock.MockDtoFactory;
import com.gabchak.budget.mock.MockEntityFactory;
import com.gabchak.budget.model.CategoryEntity;
import com.gabchak.budget.model.RecordEntity;
import com.gabchak.budget.model.WalletEntity;
import com.gabchak.budget.repository.CategoryRepository;
import com.gabchak.budget.repository.RecordRepository;
import com.gabchak.budget.repository.WalletRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecordServiceImplTest {

  @Mock RecordRepository recordRepository;
  @Mock CategoryRepository categoryRepository;
  @Mock WalletRepository walletRepository;
  @Mock RecordResponseDtoMapper recordResponseDtoMapper;
  @Mock RecordRequestDtoMapper recordRequestDtoMapper;
  @InjectMocks RecordServiceImpl recordService;

  @Test
  void findAll_success() {
    List<RecordEntity> recordEntities = List.of(Mockito.mock(RecordEntity.class));
    RecordResponseDto responseDto = MockDtoFactory.buildRecordResponseDto();
    List<RecordResponseDto> expected = List.of(responseDto);

    Mockito
        .when(recordRepository.findAll())
        .thenReturn(recordEntities);
    Mockito
        .when(recordResponseDtoMapper.toList(recordEntities))
        .thenReturn(expected);

    List<RecordResponseDto> actual = recordService.findAll();

    Assertions
        .assertThat(actual)
        .isEqualTo(expected);
  }

  @Test
  void findById_success() {
    RecordResponseDto expected = MockDtoFactory.buildRecordResponseDto();
    RecordEntity recordEntity = new RecordEntity();
    Integer id = expected.id();
    recordEntity.setId(id);

    Mockito
        .when(recordRepository.findById(id))
        .thenReturn(Optional.of(recordEntity));
    Mockito
        .when(recordResponseDtoMapper.toDto(recordEntity))
        .thenReturn(expected);

    RecordResponseDto actual = recordService.findById(id);

    Assertions
        .assertThat(actual)
        .isEqualTo(expected);
  }

  @Test
  void findById_notFound() {
    Integer id = 1;

    Assertions
        .assertThatThrownBy(() -> recordService.findById(id))
        .isInstanceOf(NoSuchElementException.class)
        .hasMessage("No record was found by id=" + id);
  }

  @Test
  void update_success() {
    Integer id = 1;
    RecordRequestDto dto = MockDtoFactory.buildRecordRequestDto();
    RecordEntity recordEntity = MockEntityFactory.buildRecordEntity(dto);
    recordEntity.setId(id);
    WalletEntity walletEntity = new WalletEntity();
    walletEntity.setId(2);
    recordEntity.setWalletEntity(walletEntity);
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setId(3);
    recordEntity.setCategoryEntity(categoryEntity);
    RecordResponseDto expected = MockDtoFactory.buildRecordResponseDto(recordEntity);

    Mockito
        .when(recordRepository.findById(id))
        .thenReturn(Optional.of(recordEntity));
    Mockito
        .when(recordRequestDtoMapper.updateEntity(recordEntity, dto))
        .thenReturn(recordEntity);
    Mockito
        .when(walletRepository.findById(dto.walletId()))
        .thenReturn(Optional.of(walletEntity));
    Mockito
        .when(categoryRepository.findById(dto.categoryId()))
        .thenReturn(Optional.of(categoryEntity));
    Mockito
        .when(recordResponseDtoMapper.toDto(recordEntity))
        .thenReturn(expected);

    RecordResponseDto actual = recordService.update(id, dto);

    Assertions
        .assertThat(actual)
        .isEqualTo(expected);
  }

  @Test
  void update_walletNotFound() {
    Integer id = 1;
    RecordRequestDto dto = MockDtoFactory.buildRecordRequestDto();
    RecordEntity recordEntity = MockEntityFactory.buildRecordEntity(dto);
    recordEntity.setId(id);
    WalletEntity walletEntity = new WalletEntity();
    walletEntity.setId(2);
    recordEntity.setWalletEntity(walletEntity);
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setId(3);
    recordEntity.setCategoryEntity(categoryEntity);

    Mockito
        .when(recordRepository.findById(id))
        .thenReturn(Optional.of(recordEntity));
    Mockito
        .when(recordRequestDtoMapper.updateEntity(recordEntity, dto))
        .thenReturn(recordEntity);

    Assertions
        .assertThatThrownBy(() -> recordService.update(id, dto))
        .isInstanceOf(NoSuchElementException.class)
        .hasMessage("No wallet was found by id=" + dto.walletId());
  }

  @Test
  void update_categoryNotFound() {
    Integer id = 1;
    RecordRequestDto dto = MockDtoFactory.buildRecordRequestDto();
    RecordEntity recordEntity = MockEntityFactory.buildRecordEntity(dto);
    recordEntity.setId(id);
    WalletEntity walletEntity = new WalletEntity();
    walletEntity.setId(2);
    recordEntity.setWalletEntity(walletEntity);
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setId(3);
    recordEntity.setCategoryEntity(categoryEntity);

    Mockito
        .when(recordRepository.findById(id))
        .thenReturn(Optional.of(recordEntity));
    Mockito
        .when(recordRequestDtoMapper.updateEntity(recordEntity, dto))
        .thenReturn(recordEntity);
    Mockito
        .when(walletRepository.findById(dto.walletId()))
        .thenReturn(Optional.of(walletEntity));

    Assertions
        .assertThatThrownBy(() -> recordService.update(id, dto))
        .isInstanceOf(NoSuchElementException.class)
        .hasMessage("No category was found by id=" + dto.categoryId());
  }

  @Test
  void update_notFound() {
    Integer id = 1;

    Assertions
        .assertThatThrownBy(() -> recordService.update(id, MockDtoFactory.buildRecordRequestDto()))
        .isInstanceOf(NoSuchElementException.class)
        .hasMessage("No record was found by id=" + id);
  }

  @Test
  void create_success() {
    RecordRequestDto dto = MockDtoFactory.buildRecordRequestDto();
    RecordEntity recordEntity = MockEntityFactory.buildRecordEntity(dto);
    WalletEntity walletEntity = new WalletEntity();
    walletEntity.setId(2);
    recordEntity.setWalletEntity(walletEntity);
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setId(3);
    recordEntity.setCategoryEntity(categoryEntity);
    RecordResponseDto expected = MockDtoFactory.buildRecordResponseDto(recordEntity);

    Mockito
        .when(recordRequestDtoMapper.toEntity(dto))
        .thenReturn(recordEntity);
    Mockito
        .when(walletRepository.findById(dto.walletId()))
        .thenReturn(Optional.of(walletEntity));
    Mockito
        .when(categoryRepository.findById(dto.categoryId()))
        .thenReturn(Optional.of(categoryEntity));
    Mockito
        .when(recordRepository.save(recordEntity))
        .thenReturn(recordEntity);
    Mockito
        .when(recordResponseDtoMapper.toDto(recordEntity))
        .thenReturn(expected);

    RecordResponseDto actual = recordService.create(dto);

    Assertions
        .assertThat(actual)
        .isEqualTo(expected);
  }
}