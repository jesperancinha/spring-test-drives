package org.jesperancinha.sftd.flash418.controller.advice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.text.StringEscapeUtils;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class SongConverter {

    public static class Serialize extends JsonSerializer<Song> {
        @Override
        public void serialize(Song song, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
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

    public static class Deserialize extends JsonDeserializer<Song> {
        @Override
        public Song deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            try {
                final var node = jsonParser.getCodec().readTree(jsonParser);
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
