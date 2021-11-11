package com.gabchak.budget.models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@Table(name = "wallet")
@Entity
public class WalletEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @Column(unique = true, nullable = false)
  String name;
  String description;
  @Column(name = "sort_order")
  Integer sortOrder;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_id", nullable = false)
  CurrencyEntity currencyEntity;
  @OneToMany(mappedBy = "walletEntity")
  Set<RecordEntity> recordEntities;
}
