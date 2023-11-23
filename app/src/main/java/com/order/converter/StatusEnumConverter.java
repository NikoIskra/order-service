package com.order.converter;

import com.order.exception.BadRequestException;
import com.order.model.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter
public class StatusEnumConverter implements AttributeConverter<StatusEnum, String> {
  @Override
  public String convertToDatabaseColumn(StatusEnum attribute) {
    if (attribute == null) {
      throw new InternalError("error converting status enum");
    }
    return attribute.toString();
  }

  @Override
  public StatusEnum convertToEntityAttribute(String dbData) {
    List<StatusEnum> statuses = Arrays.asList(StatusEnum.class.getEnumConstants());
    for (StatusEnum statusEnum : statuses) {
      if (dbData.equals(statusEnum.toString())) {
        return statusEnum;
      }
    }
    throw new BadRequestException("conversion failed");
  }
}
