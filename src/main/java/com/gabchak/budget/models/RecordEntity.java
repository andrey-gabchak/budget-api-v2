package com.gabchak.budget.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "record")
@Entity
public class RecordEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  Double value;
  LocalDateTime creationDate;
  String description;
  Boolean income;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id")
  WalletEntity walletEntity;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  CategoryEntity categoryEntity;
}
