package net.csobik.tracker.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Component;

import net.csobik.tracker.domain.Money;
import net.csobik.tracker.domain.type.Currency;

/**
 * @author jirisobotik
 * @version 1.0.0
 * @since 07.01.18
 */
@Component
public class PaymentTrackerRepository implements IPaymentTrackerRepository {
  private final List repository = new Vector<Money>();

  @Override
  public void add(Money payment) {
    repository.add(payment);
  }

  @Override
  public Money sumByCurrency(Currency currency) {

    Vector<Money> repo = (Vector<Money>) repository;
    BigDecimal retAmount = BigDecimal.ZERO;

    for (Money m : repo) {
      if (m == null) {
        continue;
      }

      if ((m.getCurrency() == null && currency == null) ||
              (m.getCurrency() != null && m.getCurrency().equals(currency))) {
        retAmount = retAmount.add(m.getAmount());

      }
    }

    return new Money(currency, retAmount);
  }

  @Override
  public int size() {
    return repository.size();
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("PaymentTrackerRepository{");
    sb.append("repository=").append(repository);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PaymentTrackerRepository that = (PaymentTrackerRepository) o;

    return repository != null ? repository.equals(that.repository) : that.repository == null;

  }

  @Override
  public int hashCode() {
    return repository != null ? repository.hashCode() : 0;
  }
}
