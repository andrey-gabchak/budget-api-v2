package com.gabchak.budget.repository;

import com.gabchak.budget.model.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity, Integer> {
}
