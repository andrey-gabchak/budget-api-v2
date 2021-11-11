package com.gabchak.budget.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "currency_exchange_rate")
@Entity
public class CurrencyExchangeRateEntity {

  @EmbeddedId
  CurrencyExchangeRatePK id;
  @Column(name = "exchange_rate", nullable = false)
  Double exchangeRate;
}
