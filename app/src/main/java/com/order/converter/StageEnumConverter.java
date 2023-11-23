package com.order.converter;

import com.order.exception.BadRequestException;
import com.order.model.StageEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter
public class StageEnumConverter implements AttributeConverter<StageEnum, String> {

  @Override
  public String convertToDatabaseColumn(StageEnum attribute) {
    if (attribute == null) {
      throw new InternalError("error converting stage enum");
    }
    return attribute.toString();
  }

  @Override
  public StageEnum convertToEntityAttribute(String dbData) {
    List<StageEnum> stages = Arrays.asList(StageEnum.class.getEnumConstants());
    for (StageEnum stageEnum : stages) {
      if (dbData.equals(stageEnum.toString())) {
        return stageEnum;
      }
    }
    throw new BadRequestException("conversion failed");
  }
}
