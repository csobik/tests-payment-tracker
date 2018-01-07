package net.csobik.tracker.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

import net.csobik.tracker.service.DataInitializerService;
import net.csobik.tracker.service.PaymentTrackerService;

/**
 * @author jirisobotik
 * @version 1.0.0
 * @since 07.01.18
 */
@Configuration
public class PaymentTrackerConfiguration {

  @Bean
  public PromptProvider myPromptProvider() {
    return () -> new AttributedString("my-shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
  }

  @Bean
  public PaymentTrackerService paymentTrackerService() {
    return new PaymentTrackerService();
  }

  @Bean
  public DataInitializerService dataInitializerService() {
    return new DataInitializerService();
  }
}
