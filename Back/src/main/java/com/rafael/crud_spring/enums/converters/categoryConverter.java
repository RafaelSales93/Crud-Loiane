package com.rafael.crud_spring.enums.converters;

import java.util.stream.Stream;

import com.rafael.crud_spring.enums.category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class categoryConverter  implements AttributeConverter<category, String> {

    @Override
    public String convertToDatabaseColumn(category category) {
       if (category == null) {
           return null;
       }
         return category.getValue();
       }

    @Override
    public category convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(category.values())
            .filter(c -> c.getValue().equals(value))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
    
    
}
