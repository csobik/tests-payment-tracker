package net.csobik.tracker.repository;

import net.csobik.tracker.domain.Money;
import net.csobik.tracker.domain.type.Currency;

/**
 * @author jirisobotik
 * @version 1.0.0
 * @since 07.01.18
 */
public interface IPaymentTrackerRepository {

  /**
   * Add new record to repository
   *
   * @param payment
   */
  void add(Money payment);

  /**
   * summarize data from repository aggregated by passed currency
   *
   * @param currrency
   * @return
   */
  Money sumByCurrency(Currency currrency);

  /**
   * size of the repository
   *
   * @return
   */
  int size();
}
