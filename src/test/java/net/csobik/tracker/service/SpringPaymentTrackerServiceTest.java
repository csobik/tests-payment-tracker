package net.csobik.tracker.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.csobik.tracker.IntegrationTest;
import net.csobik.tracker.PaymentTrackerApplication;
import net.csobik.tracker.domain.Money;
import net.csobik.tracker.domain.type.Currency;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Basic test to check whether {@link PaymentTrackerApplication} is usable as expected.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
public class SpringPaymentTrackerServiceTest {

	@Autowired
	private PaymentTrackerService service;

  @Test
  public void savePaymentTest() {
    service.savePayment("USD", "123");
    Money summary = service.getCurrencySum(Currency.USD);
    Money expected = new Money(Currency.USD, BigDecimal.valueOf(123));
    assertThat(summary, is(expected));
  }

	@Test
  public void savePaymentSubTest() {
    service.savePayment("GBP", "123");
    service.savePayment("GBP", "-123.0000");
    Money summary = service.getCurrencySum(Currency.GBP);
    assertNull(summary);
  }
}
