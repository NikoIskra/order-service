package com.order.test.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.order.persistence.repository.OrderRepository;
import com.order.service.OrderNumberGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderNumberGeneratorTest {

  @Mock OrderRepository orderRepository;

  @InjectMocks OrderNumberGenerator orderNumberGenerator;

  @Test
  void testRandomNumberGeneration() {
    when(orderRepository.existsByOrderNumber(anyString())).thenReturn(false);
    String firstNumber = orderNumberGenerator.generateOrderNumber();
    String secondNumber = orderNumberGenerator.generateOrderNumber();
    assertNotEquals(firstNumber, secondNumber);
  }
}
