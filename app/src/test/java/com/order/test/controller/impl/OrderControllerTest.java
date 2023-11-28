package com.order.test.controller.impl;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.controller.impl.OrderController;
import com.order.exception.NotFoundException;
import com.order.model.OrderGetReturnModel;
import com.order.model.OrderGetReturnModelResult;
import com.order.model.OrderPostDetailsModel;
import com.order.model.OrderPostRequestModel;
import com.order.model.OrderPostReturnModel;
import com.order.model.OrderPostReturnModelResult;
import com.order.model.StageEnum;
import com.order.model.StatusEnum;
import com.order.service.impl.OrderServiceImpl;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@WebMvcTest(OrderController.class)
@EnableWebMvc
public class OrderControllerTest {
  @Autowired MockMvc mvc;

  @MockBean OrderServiceImpl orderServiceImpl;

  private static final UUID accountID = UUID.fromString("45c01a73-5058-4135-84a7-01b964377ef8");

  ObjectMapper mapper = new ObjectMapper();

  private static final String orderNumber = "X3-23ZEEG";

  private static OrderPostRequestModel createOrderPostRequestModel() {
    OrderPostRequestModel model = new OrderPostRequestModel(1L, "1st street 123", 2L);
    return model;
  }

  private static OrderPostReturnModel createOrderPostReturnModel() {
    OrderPostDetailsModel orderPostDetailsModel =
        new OrderPostDetailsModel().providerItemId(2L).quantity(1).priceCents(1500);
    OrderPostReturnModelResult result =
        new OrderPostReturnModelResult()
            .orderNumber(orderNumber)
            .providerId(1L)
            .clientId(accountID)
            .comment("comment")
            .totalPriceCents(1500)
            .clientContact("1st street 123")
            .deliveryAddress("1st street 123")
            .stage(StageEnum.NEW)
            .status(StatusEnum.IN_PROGRESS)
            .details(orderPostDetailsModel);
    return new OrderPostReturnModel().ok(true).result(result);
  }

  private static OrderGetReturnModel createOrderGetReturnModel() {
    OrderPostDetailsModel orderPostDetailsModel =
        new OrderPostDetailsModel().providerItemId(2L).quantity(1).priceCents(1500);
    OrderGetReturnModelResult result =
        new OrderGetReturnModelResult()
            .id(1L)
            .orderNumber(orderNumber)
            .providerId(1L)
            .clientId(accountID)
            .comment("comment")
            .totalPriceCents(1500)
            .clientContact("1st street 123")
            .deliveryAddress("1st street 123")
            .stage(StageEnum.NEW)
            .status(StatusEnum.IN_PROGRESS)
            .details(orderPostDetailsModel);
    return new OrderGetReturnModel().ok(true).result(result);
  }

  @Test
  void createOrderTest() throws Exception {
    OrderPostRequestModel orderPostRequestModel = createOrderPostRequestModel();
    OrderPostReturnModel orderPostReturnModel = createOrderPostReturnModel();
    when(orderServiceImpl.createOrder(accountID, orderPostRequestModel))
        .thenReturn(orderPostReturnModel);
    mvc.perform(
            MockMvcRequestBuilders.post("/api/v1/order")
                .header("X-ACCOUNT-ID", accountID.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(orderPostRequestModel)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.result.orderNumber").value(orderNumber));
  }

  @Test
  void createOrderTest_badRequest() throws Exception {
    OrderPostRequestModel orderPostRequestModel = createOrderPostRequestModel();
    OrderPostReturnModel orderPostReturnModel = createOrderPostReturnModel();
    when(orderServiceImpl.createOrder(accountID, orderPostRequestModel))
        .thenReturn(orderPostReturnModel);
    mvc.perform(
            MockMvcRequestBuilders.post("/api/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(orderPostRequestModel)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void getOrderTest() throws Exception {
    OrderGetReturnModel orderGetReturnModel = createOrderGetReturnModel();
    when(orderServiceImpl.getOrder(accountID, 1L)).thenReturn(orderGetReturnModel);
    mvc.perform(
            MockMvcRequestBuilders.get("/api/v1/order/1")
                .header("X-ACCOUNT-ID", accountID.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.result.id").value(1));
  }

  @Test
  void getOrderTest_validatorException() throws Exception {
    when(orderServiceImpl.getOrder(accountID, 1L)).thenThrow(NotFoundException.class);
    mvc.perform(
            MockMvcRequestBuilders.get("/api/v1/order/1")
                .header("X-ACCOUNT-ID", accountID.toString()))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value(false));
  }
}
