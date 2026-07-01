package org.jesperancinha.sftd.flash214.transactions.model;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class MovieAppearanceConverter implements AttributeConverter<String[], String> {

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        if (Objects.isNull(attribute)) {
            return "";
        }
        return String.join(",", attribute);
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return new String[0];
        }
        return dbData.split(",");
    }
}
