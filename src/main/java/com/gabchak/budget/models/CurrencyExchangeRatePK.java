package com.gabchak.budget.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class CurrencyExchangeRatePK implements Serializable {

  @Column(name = "from_currency_mnemonic")
  Integer fromCurrencyMnemonic;
  @Column(name = "to_currency_mnemonic")
  Integer toCurrencyMnemonic;
}
