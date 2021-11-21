package com.gabchak.budget.mapper;

import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.mock.MockDtoFactory;
import com.gabchak.budget.mock.MockEntityFactory;
import com.gabchak.budget.model.RecordEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecordRequestDtoMapperTest {

  RecordRequestDtoMapper mapper;

  @BeforeAll
  void setUp() {
    mapper = new RecordRequestDtoMapperImpl();
  }

  @Test
  void test_ToEntity_success() {
    RecordRequestDto requestDto = MockDtoFactory.buildRecordRequestDto();
    RecordEntity expected = MockEntityFactory.buildRecordEntity(requestDto);

    RecordEntity actual = mapper.toEntity(requestDto);

    Assertions
        .assertThat(actual)
        .isEqualTo(expected);
  }

  @Test
  void test_UpdateEntity_success() {
    RecordRequestDto requestDto = MockDtoFactory.buildRecordRequestDto();
    RecordEntity toUpdate = new RecordEntity();
    RecordEntity expected = MockEntityFactory.buildRecordEntity(requestDto);

    RecordEntity actual = mapper.updateEntity(toUpdate, requestDto);

    Assertions
        .assertThat(actual)
        .isEqualTo(expected);
  }
}