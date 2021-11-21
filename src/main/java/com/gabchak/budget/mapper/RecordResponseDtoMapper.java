package com.gabchak.budget.mapper;

import com.gabchak.budget.dto.RecordResponseDto;
import com.gabchak.budget.model.RecordEntity;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecordResponseDtoMapper {

  @Mapping(source = "walletEntity.id", target = "walletId")
  @Mapping(source = "categoryEntity.id", target = "categoryId")
  RecordResponseDto toDto(RecordEntity recordEntity);

  List<RecordResponseDto> toList(Collection<RecordEntity> entities);
}
