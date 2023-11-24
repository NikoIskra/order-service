package com.order.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.order.persistence.repository.OrderRepository;
import com.order.service.OrderNumberGenerator;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class OrderNumberGeneratorTest {

  @Mock OrderRepository orderRepository;

  @Mock NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @InjectMocks OrderNumberGenerator orderNumberGenerator;

  @Test
  void testRandomNumberGeneration() {
    when(namedParameterJdbcTemplate.queryForObject(
            anyString(),
            ArgumentMatchers.any(MapSqlParameterSource.class),
            ArgumentMatchers.<Class<Integer>>any()))
        .thenReturn(0);
    String firstNumber = orderNumberGenerator.generateOrderNumber();
    String secondNumber = orderNumberGenerator.generateOrderNumber();
    assertNotEquals(firstNumber, secondNumber);
  }

  @Test
  void testRandomNumberGenerationValidity() {
    when(namedParameterJdbcTemplate.queryForObject(
            anyString(),
            ArgumentMatchers.any(MapSqlParameterSource.class),
            ArgumentMatchers.<Class<Integer>>any()))
        .thenReturn(0);
    String generatedNumber = orderNumberGenerator.generateOrderNumber();
    LocalDateTime localDateTime = LocalDateTime.now();
    String monthCode = orderNumberGenerator.getMonthCode(localDateTime.getMonthValue());
    String year = String.valueOf(localDateTime.getYear() % 10);
    assertEquals(String.valueOf(generatedNumber.charAt(0)), monthCode);
    assertEquals(String.valueOf(generatedNumber.charAt(1)), year);
    assertEquals(generatedNumber.substring(3, 5), String.valueOf(localDateTime.getDayOfMonth()));
  }

  @Test
  void testGetMonthCode() {
    List<String> monthCodes = List.of("F", "G", "H", "J", "K", "M", "N", "Q", "U", "V", "X", "Z");
    for (int i = 1; i < 13; i++) {
      String monthCode = orderNumberGenerator.getMonthCode(i);
      assertEquals(monthCodes.get(i - 1), monthCode);
    }
  }
}
