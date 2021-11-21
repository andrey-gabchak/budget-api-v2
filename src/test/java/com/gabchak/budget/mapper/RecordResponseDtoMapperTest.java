package com.gabchak.budget.mapper;

import com.gabchak.budget.dto.RecordResponseDto;
import com.gabchak.budget.mock.MockDtoFactory;
import com.gabchak.budget.mock.MockEntityFactory;
import com.gabchak.budget.model.RecordEntity;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecordResponseDtoMapperTest {

  RecordResponseDtoMapper mapper;

  @BeforeAll
  void setUp() {
    mapper = new RecordResponseDtoMapperImpl();
  }

  @Test
  void test_ToDto_success() {
    RecordEntity recordEntity = MockEntityFactory.buildRecordEntity();
    RecordResponseDto expected = MockDtoFactory.buildRecordResponseDto(recordEntity);

    RecordResponseDto actual = mapper.toDto(recordEntity);

    Assertions
        .assertThat(actual)
        .isEqualTo(expected);
  }

  @Test
  void test_ToList_success() {
    RecordEntity recordEntity = MockEntityFactory.buildRecordEntity();
    List<RecordResponseDto> expected = List.of(MockDtoFactory.buildRecordResponseDto(recordEntity));

    List<RecordResponseDto> actual = mapper.toList(List.of(recordEntity));

    Assertions
        .assertThat(actual)
        .isEqualTo(expected);
  }
}