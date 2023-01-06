package com.viktor.recipebackend.serializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Serializers {
    public static class DateTimeDeserializer extends StdDeserializer<Date> {

        public DateTimeDeserializer() {
            this(null);
        }

        public DateTimeDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            String date = jsonParser.getText();
            try {
                return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class DateTimeSerializer extends StdSerializer<Date> {

        public DateTimeSerializer() {
            this(null);
        }

        public DateTimeSerializer(Class t) {
            super(t);
        }

        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date));
        }
    }
}