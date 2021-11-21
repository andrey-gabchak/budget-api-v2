package com.gabchak.budget.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
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
