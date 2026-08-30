package org.jesperancinha.spring.flash310.jsoncomponent.converter;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.*;
import org.apache.commons.text.StringEscapeUtils;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.spring.flash310.jsoncomponent.dto.Guitar;
import org.springframework.boot.jackson.JacksonComponent;

@JacksonComponent
public class GuitarJsonConverter {

    public static class Serialize extends ValueSerializer<Guitar> {
        @Override
        public void serialize(Guitar guitar, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
            try {
                if (guitar == null) {
                    jsonGenerator.writeNull();
                } else {
                    ConsolerizerComposer.outSpace()
                            .none()
                            .green("The guitar value is:")
                            .magenta()
                            .jsonPrettyPrint(guitar)
                            .newLine()
                            .reset();
                    jsonGenerator.writeString(guitar.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Deserialize extends ValueDeserializer<Guitar> {
        @Override
        public Guitar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            try {
                final var node = jsonParser.readValueAsTree();
                final var nodeString = node.toString();
                ConsolerizerComposer.outSpace()
                        .none()
                        .green("The node string is").magenta(nodeString).newLine().reset();
                final var guitar = StringEscapeUtils.unescapeJava(nodeString);
                ConsolerizerComposer.outSpace()
                        .none()
                        .green("The guitar string is").magenta(guitar).newLine().reset();
                return new ObjectMapper().readValue(guitar, Guitar.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
