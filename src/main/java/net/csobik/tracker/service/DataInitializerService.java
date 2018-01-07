package net.csobik.tracker.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.csobik.tracker.repository.IPaymentTrackerRepository;


/**
 * Initialization of repository from given file
 *
 * run before server starts to fill repository from given resources
 * if {@link CommandLineRunner} is implemented it will start automatically
 * when application starts
 *
 */
@Component()
public class DataInitializerService {

  @Autowired
  IPaymentTrackerRepository paymentTrackerRepository;

  @Autowired
  PaymentTrackerService paymentTrackerService;

  public void initialize(String fileName) {
    if (paymentTrackerRepository.size() > 0) {
      System.out.println("INFO: Repository already contains data");
      return;
    }

    File initialFile = new File(fileName);
    if (!initialFile.exists()) {
      System.out.println(String.format("ERROR: File %s does not exists", fileName));
      return;
    }

    try (InputStream stream = new FileInputStream(initialFile)) {
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));

      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
        String[] splitted = line.split(" ");
        if (splitted.length == 2) {
          paymentTrackerService.savePayment(splitted[0], splitted[1]);
        }
        // else inform user about wrong data
      }
      br.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
