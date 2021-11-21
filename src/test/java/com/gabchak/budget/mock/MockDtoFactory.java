package com.gabchak.budget.mock;

import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.dto.RecordResponseDto;
import com.gabchak.budget.model.RecordEntity;
import java.time.LocalDateTime;


public class MockDtoFactory {

  private MockDtoFactory() {
  }

  public static RecordRequestDto buildRecordRequestDto() {
    return new RecordRequestDto(
        1.1,
        LocalDateTime.now(),
        "description",
        true,
        1,
        1
    );
  }

  public static RecordResponseDto buildRecordResponseDto() {
    return new RecordResponseDto(
        1,
        1.2,
        LocalDateTime.now(),
        "description",
        true,
        2,
        3
    );
  }

  public static RecordResponseDto buildRecordResponseDto(RecordEntity recordEntity) {
    return new RecordResponseDto(
        recordEntity.getId(),
        recordEntity.getValue(),
        recordEntity.getCreationDate(),
        recordEntity.getDescription(),
        recordEntity.getIncome(),
        recordEntity.getWalletEntity().getId(),
        recordEntity.getCategoryEntity().getId()
    );
  }
}
