package org.jesperancinha.sftd.flash310.jsoncomponent.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.text.StringEscapeUtils;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash310.jsoncomponent.dto.Guitar;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class GuitarJsonConverter {

    public static class Serialize extends JsonSerializer<Guitar> {
        @Override
        public void serialize(Guitar guitar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
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

    public static class Deserialize extends JsonDeserializer<Guitar> {
        @Override
        public Guitar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            try {
                final var node = jsonParser.getCodec().readTree(jsonParser);
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
