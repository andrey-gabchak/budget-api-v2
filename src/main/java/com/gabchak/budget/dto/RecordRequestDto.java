package com.gabchak.budget.dto;

import java.time.LocalDateTime;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record RecordRequestDto(@NonNull Double value,
                               @NonNull LocalDateTime creationDate,
                               @Nullable String description,
                               @NonNull Boolean income,
                               @NonNull Integer walletId,
                               @NonNull Integer categoryId) {
}
