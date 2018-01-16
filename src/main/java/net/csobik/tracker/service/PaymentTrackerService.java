package net.csobik.tracker.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import net.csobik.tracker.domain.Money;
import net.csobik.tracker.domain.type.Currency;
import net.csobik.tracker.repository.IPaymentTrackerRepository;

@Service
public class PaymentTrackerService {

  private static final Logger log = LoggerFactory.getLogger(PaymentTrackerService.class);
  private static final String PAYMENT_WRONG_VALUE = "PAYMENT.WRONG_VALUE";

  @Autowired
  IPaymentTrackerRepository paymentTrackerRepository;

  /**
   * Add new record of simple payment to local repository
   *
   * @param currency
   * @param amount
   */
  public void savePayment(String currency, String amount) {
    try {
      Currency curr = Currency.valueOf(currency);
      BigDecimal val = new BigDecimal(amount);
      Money money = new Money(curr, val);
      paymentTrackerRepository.add(money);

    } catch (IllegalArgumentException ex) {
//      log.error(PAYMENT_WRONG_VALUE, ex);
      throw new ApplicationException(PAYMENT_WRONG_VALUE, "ERROR: Wrong value of Currency or Number format error" + "\nSupported currencies: " + Arrays.toString(Currency.values()));
    }
  }

  /**
   * separated here for that we have possibility to test repository summary
   *
   * @param currency
   * @return
   */
  public Money getCurrencySum(Currency currency) {
    Money sum = paymentTrackerRepository.sumByCurrency(currency);
    if (sum.getAmount().compareTo(BigDecimal.ZERO) != 0) {
      return sum;
    }
    return null;
  }

  /**
   * Simply print sum of all payments aggregated by currency
   */
  public void printSumPayments() {
      final String sb = Arrays.stream(Currency.values())
              .map(this::getCurrencySum)
              .filter(Objects::nonNull)
              .map(sum -> sum.getCurrency() + " " + sum.getAmount() + "\n")
              .collect(Collectors.joining());

      System.out.println();
    System.out.println("**********************");
    System.out.println(String.format("Summarize time: %s", LocalDateTime.now()));
    System.out.println(sb);
    System.out.println("**********************");
    System.out.println();
  }

  /**
   * Every minute prins out summary of all payments aggregated by currency
   * {@see {@link PaymentTrackerService#printSumPayments}}
   *
   */
  @Scheduled(fixedRate = 60000) // one minute
  public void reportCurrentSum() {
    printSumPayments();
  }
}
