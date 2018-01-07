package net.csobik.tracker.domain;

import java.math.BigDecimal;

import net.csobik.tracker.domain.type.Currency;

/**
 * @author jirisobotik
 * @version 1.0.0
 * @since 07.01.18
 */
public class Money {

  private final Currency currency;
  private final BigDecimal amount;

  /**
   * Immutable instantion of payment
   */
  public Money(Currency currency, BigDecimal amount) {
    this.currency = currency;
    this.amount = amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Money money = (Money) o;

    if (currency != money.currency) return false;
    return amount != null ? amount.equals(money.amount) : money.amount == null;

  }

  @Override
  public int hashCode() {
    int result = currency != null ? currency.hashCode() : 0;
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Money{");
    sb.append("currency=").append(currency);
    sb.append(", amount=").append(amount);
    sb.append('}');
    return sb.toString();
  }
}
