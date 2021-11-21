package com.gabchak.budget.dto;

import java.time.LocalDateTime;

public record RecordResponseDto(Integer id,
                                Double value,
                                LocalDateTime creationDate,
                                String description,
                                Boolean income,
                                Integer walletId,
                                Integer categoryId) {
}
