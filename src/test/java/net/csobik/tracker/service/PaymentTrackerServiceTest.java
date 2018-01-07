package net.csobik.tracker.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import net.csobik.tracker.domain.Money;
import net.csobik.tracker.repository.IPaymentTrackerRepository;
import net.csobik.tracker.repository.PaymentTrackerRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author jirisobotik
 * @version 1.0.0
 * @since 07.01.18
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentTrackerServiceTest {

  @InjectMocks
  private PaymentTrackerService service;

  @Mock
  IPaymentTrackerRepository repository;

  @Test()
  public void savePaymentTest() {
    //when(repository.add(any(Money.class))).thenReturn(null);
    service.savePayment("USD", "123");
  }

  @Test(expected = ApplicationException.class)
  public void savePaymentWrongCurrencyTest() {
    service.savePayment("EUR", "123");
  }

}
