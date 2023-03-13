package com.example.checkers.checkers.models.entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StringToCharConverter extends JsonSerializer<Character> {
    public StringToCharConverter() {
        super();
    }

    @Override
    public void serialize(Character value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStringField("color", String.valueOf(value));
    }
}