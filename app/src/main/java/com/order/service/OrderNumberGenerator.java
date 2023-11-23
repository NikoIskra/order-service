package com.order.service;

import com.order.persistence.repository.OrderRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderNumberGenerator {

  static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private final OrderRepository orderRepository;

  public String generateOrderNumber() {
    String orderNumber;
    do {
      Date date = new Date();
      String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
      String[] dates = dateString.split("-");
      String monthCode;
      switch (dates[1]) {
        case "01":
          monthCode = "F";
          break;
        case "02":
          monthCode = "G";
          break;
        case "03":
          monthCode = "H";
          break;
        case "04":
          monthCode = "J";
          break;
        case "05":
          monthCode = "K";
          break;
        case "06":
          monthCode = "M";
          break;
        case "07":
          monthCode = "N";
          break;
        case "08":
          monthCode = "Q";
          break;
        case "09":
          monthCode = "U";
          break;
        case "10":
          monthCode = "V";
          break;
        case "11":
          monthCode = "X";
          break;
        case "12":
          monthCode = "Z";
          break;
        default:
          throw new InternalError("error generating order number");
      }
      Random random = new Random();
      StringBuilder sb = new StringBuilder(4);

      for (int i = 0; i < 4; i++) {
        int randomIndex = random.nextInt(CHARACTERS.length());
        char randomChar = CHARACTERS.charAt(randomIndex);
        sb.append(randomChar);
      }

      orderNumber = monthCode + dates[0].charAt(dates[0].length() - 1) + "-" + dates[2] + sb;
    } while (orderRepository.existsByOrderNumber(orderNumber));
    return orderNumber;
  }
}
