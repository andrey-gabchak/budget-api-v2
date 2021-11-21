package com.gabchak.budget.mock;

import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.model.CategoryEntity;
import com.gabchak.budget.model.RecordEntity;
import com.gabchak.budget.model.WalletEntity;
import java.time.LocalDateTime;


public class MockEntityFactory {

  private MockEntityFactory() {
  }

  public static RecordEntity buildRecordEntity() {
    RecordEntity recordEntity = new RecordEntity();
    recordEntity.setCreationDate(LocalDateTime.now());
    recordEntity.setIncome(true);
    recordEntity.setDescription("description");
    recordEntity.setValue(1.2);
    recordEntity.setId(1);
    WalletEntity walletEntity = new WalletEntity();
    walletEntity.setId(2);
    recordEntity.setWalletEntity(walletEntity);
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setId(3);
    recordEntity.setCategoryEntity(categoryEntity);
    return recordEntity;
  }

  public static RecordEntity buildRecordEntity(RecordRequestDto requestDto) {
    RecordEntity expected = new RecordEntity();
    expected.setValue(requestDto.value());
    expected.setDescription(requestDto.description());
    expected.setIncome(requestDto.income());
    expected.setCreationDate(requestDto.creationDate());
    return expected;
  }
}
