package org.jesperancinha.sftd.flash415.http.converter.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash415.http.converter.domain.TearDrop;
import org.jesperancinha.sftd.flash415.http.converter.domain.TearDropReport;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@Service
public class TearDropReportConverter extends AbstractHttpMessageConverter<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> clazz) {
        return TearDropReport.class.isAssignableFrom(clazz);
    }

    public TearDropReportConverter() {
        super(new MediaType("application", "json"));
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        final var contentString = new String(inputMessage.getBody().readAllBytes());
        try {
            final TearDropReport test = objectMapper.readValue(contentString, TearDropReport.class);
            if (Objects.nonNull(test)) {
                return test;
            }
        } catch (final MismatchedInputException exception) {
            ConsolerizerColor.RED.printExpectedException("We couldn't read a report. Can we read a tear drop list?", exception.toString());
        }

        final List<TearDrop> tearDrops = objectMapper.readValue(contentString, objectMapper.getTypeFactory().constructCollectionType(List.class, TearDrop.class));
        ConsolerizerComposer.outSpace()
                .cyan(title("Received drops!"))
                .jsonPrettyPrint(tearDrops)
                .reset();
        if (Objects.nonNull(tearDrops)) {
            final var total = tearDrops.stream().map(TearDrop::getCount).mapToLong(x -> x).sum();
            final var average = tearDrops.stream().map(TearDrop::getCount).mapToLong(x -> x).average().orElse(0);
            final var numberOfDropObjects = tearDrops.size();
            final TearDropReport tearDropReport = TearDropReport.builder()
                    .stdDeviation(numberOfDropObjects == 1 ? 0 : (long) Math.sqrt(
                            tearDrops.stream().map(tearDrop -> tearDrop.getCount())
                                    .mapToLong(x -> x).map(x -> (long) Math.pow(x - average, 2)).sum() / (numberOfDropObjects - 1))
                    )
                    .average((long) average)
                    .totalCount(total)
                    .tearDropTypeSet(tearDrops.stream().map(TearDrop::getTearDropType).collect(Collectors.toSet()))
                    .build();
            ConsolerizerComposer.outSpace()
                    .cyan(title("TearDropReport"))
                    .jsonPrettyPrint(tearDropReport)
                    .reset();
            return tearDropReport;
        }

        return null;
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        final var outputStream = outputMessage.getBody();
        outputStream.write(objectMapper.writeValueAsBytes(object));
        outputStream.close();
    }
}
