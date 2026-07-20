package org.jesperancinha.spring.flash418.controller.advice;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.*;
import org.apache.commons.text.StringEscapeUtils;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.jackson.JacksonComponent;

@JacksonComponent
public class SongConverter {

    public static class Serialize extends ValueSerializer<Song> {
        @Override
        public void serialize(Song song, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
            try {
                if (song == null) {
                    jsonGenerator.writeNull();
                } else {
                    ConsolerizerComposer.outSpace()
                            .none()
                            .green("The song value is:")
                            .magenta()
                            .jsonPrettyPrint(song)
                            .newLine()
                            .reset();
                    jsonGenerator.writeString(song.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Deserialize extends ValueDeserializer<Song> {
        @Override
        public Song deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            try {
                final var node = jsonParser.readValueAsTree();
                final var nodeString = node.toString();
                ConsolerizerComposer.outSpace()
                        .none()
                        .green("The node string is").magenta(nodeString).newLine().reset();
                final var song = StringEscapeUtils.unescapeJava(nodeString);
                ConsolerizerComposer.outSpace()
                        .none()
                        .green("The song string is").magenta(song).newLine().reset();
                return new ObjectMapper().readValue(song, Song.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
