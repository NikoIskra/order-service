package com.order.service;

import java.time.LocalDateTime;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderNumberGenerator {

  static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private final NamedParameterJdbcTemplate jdbcTemplate;

  private static final String queryString =
      "SELECT COUNT(1) FROM orders WHERE order_number = :orderNumber";

  public String generateOrderNumber() {
    String orderNumber;
    int result;
    do {
      LocalDateTime localDateTime = LocalDateTime.now();
      String monthCode = getMonthCode(localDateTime.getMonthValue());
      Random random = new Random();
      StringBuilder sb = new StringBuilder(4);
      for (int i = 0; i < 5; i++) {
        int randomIndex = random.nextInt(CHARACTERS.length());
        char randomChar = CHARACTERS.charAt(randomIndex);
        sb.append(randomChar);
      }
      int year = localDateTime.getYear() % 10;
      orderNumber = monthCode + String.valueOf(year) + "-" + localDateTime.getDayOfMonth() + sb;
      result =
          jdbcTemplate.queryForObject(
              queryString,
              new MapSqlParameterSource().addValue("orderNumber", orderNumber),
              Integer.class);
    } while (result == 1);
    return orderNumber;
  }

  public String getMonthCode(Integer monthValue) {
    String monthCode =
        switch (monthValue) {
          case 1 -> "F";
          case 2 -> "G";
          case 3 -> "H";
          case 4 -> "J";
          case 5 -> "K";
          case 6 -> "M";
          case 7 -> "N";
          case 8 -> "Q";
          case 9 -> "U";
          case 10 -> "V";
          case 11 -> "X";
          case 12 -> "Z";
          default -> throw new IllegalArgumentException("Unexpected value: " + monthValue);
        };
    return monthCode;
  }
}
