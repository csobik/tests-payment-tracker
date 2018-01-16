package net.csobik.tracker.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import net.csobik.tracker.domain.type.Currency;
import net.csobik.tracker.service.ApplicationException;
import net.csobik.tracker.service.DataInitializerService;
import net.csobik.tracker.service.PaymentTrackerService;

/**
 * Simple class to handle users inputs from spring shell
 *
 * @author jirisobotik
 * @version 1.0.0
 * @since 06.01.18
 */
@ShellComponent()
public class MyCommands {

  @Autowired
  PaymentTrackerService service;

  @Autowired
  DataInitializerService initService;

  @Value("${initFile:undefined}")
  private String initFile;


  @ShellMethod("add new payment record")
  public void add(@ShellOption(help = "Currency code (ie. USD)") String currency, @ShellOption(help = "Amount of new payment") String amount) {
    try {
      service.savePayment(currency, amount);
    } catch (ApplicationException ex) {
      System.out.println(ex.getMessage());
    }
  }

  @ShellMethod("summarize all payments grouped by currency")
  public void sum() {
    service.printSumPayments();
  }

  @ShellMethod("print all supported currencies")
  public void supportedCurrencies() {
    System.out.println(Currency.values());
  }

  @ShellMethod("initialize repository from given file \n(can be set when application starts as console parameter or here as optional parametr)")
  public void init(@ShellOption(defaultValue = "") String initFile) {
    if (!StringUtils.isEmpty(initFile)) {
      this.initFile = initFile;
    }
    initService.initialize(this.initFile);
  }
}