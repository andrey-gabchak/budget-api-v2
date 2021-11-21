package com.gabchak.budget.repository;

import com.gabchak.budget.model.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {
}
