package com.gabchak.budget.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@EqualsAndHashCode(of = "mnemonic")
@Table(name = "currency")
@Entity
public class CurrencyEntity {
  @Id
  String mnemonic;

  @OneToMany(mappedBy = "currencyEntity")
  Set<WalletEntity> walletEntities;
}
