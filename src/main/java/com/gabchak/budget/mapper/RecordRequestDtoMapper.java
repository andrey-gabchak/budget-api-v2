package com.gabchak.budget.mapper;

import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.model.RecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RecordRequestDtoMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "value", source = "value")
  @Mapping(target = "income", source = "income")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "creationDate", source = "creationDate")
  @Mapping(target = "walletEntity", ignore = true)
  @Mapping(target = "categoryEntity", ignore = true)
  RecordEntity toEntity(RecordRequestDto dto);

  @Mapping(target = "recordEntity.id", ignore = true)
  @Mapping(target = "recordEntity.value", source = "dto.value")
  @Mapping(target = "recordEntity.income", source = "dto.income")
  @Mapping(target = "recordEntity.description", source = "dto.description")
  @Mapping(target = "recordEntity.creationDate", source = "dto.creationDate")
  @Mapping(target = "recordEntity.walletEntity", ignore = true)
  @Mapping(target = "recordEntity.categoryEntity", ignore = true)
  RecordEntity updateEntity(@MappingTarget RecordEntity recordEntity, RecordRequestDto dto);
}
