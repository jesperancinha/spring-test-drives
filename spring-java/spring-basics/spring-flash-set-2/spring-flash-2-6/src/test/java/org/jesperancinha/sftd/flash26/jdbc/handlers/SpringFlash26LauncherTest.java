package org.jesperancinha.sftd.flash26.jdbc.handlers;

import org.jesperancinha.sftd.flash26.jdbc.handlers.model.Shell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringFlash26LauncherTest {

    @SpyBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SpringFlash26Launcher springFlash26Launcher;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<RowMapper<Shell>> rowMapperArgumentCaptor;

    @Captor
    private ArgumentCaptor<ResultSetExtractor<String>> resultSetExtractorArgumentCaptor;

    @BeforeEach
    public void setUp() {
        springFlash26Launcher.initializeDatabase();
        final var data = Arrays.asList(
                new Object[]{"Berbigão", "Cerastoderma edule", "white"},
                new Object[]{"Conquilha", "Donax trunculus", "blue"}
        );
        springFlash26Launcher.insertData(data);
        reset(jdbcTemplate);

    }

    @Test
    void testContext() {
    }

    @Test
    void testRowMapperWhenMakingRequestThenReturnElements() {
        final var shells = springFlash26Launcher.testRowMapper();

        assertThat(shells).hasSize(2);
        final var shell1 = shells.get(0);
        final var shell2 = shells.get(1);
        assertThat(shell1.getName()).isEqualTo("Berbigão");
        assertThat(shell1.getScientificName()).isEqualTo("Cerastoderma edule");
        assertThat(shell1.getPredominantColor()).isEqualTo("white");
        assertThat(shell2.getName()).isEqualTo("Conquilha");
        assertThat(shell2.getScientificName()).isEqualTo("Donax trunculus");
        assertThat(shell2.getPredominantColor()).isEqualTo("blue");

        verify(jdbcTemplate, times(1)).query(stringArgumentCaptor.capture(), rowMapperArgumentCaptor.capture());
        final var query = stringArgumentCaptor.getValue();
        assertThat(query).isNotNull();
        assertThat(query).isEqualTo("select name, scientificName, predominentColor from shells");
        assertThat(rowMapperArgumentCaptor.getValue()).isNotNull();

    }

    @Test
    void testResultSetExtractorWhenCallingThenGetOKResultAndPrint2Records() {
        final var shells = springFlash26Launcher.testResultSetExtractor();

        assertThat(shells).isNotNull();
        assertThat(shells).isEqualTo("OK!");

        verify(jdbcTemplate, times(1)).query(stringArgumentCaptor.capture(), resultSetExtractorArgumentCaptor.capture());
        final var query = stringArgumentCaptor.getValue();
        assertThat(query).isNotNull();
        assertThat(query).isEqualTo("select name, scientificName, predominentColor from shells");
        assertThat(resultSetExtractorArgumentCaptor.getValue()).isNotNull();
    }

    @Test
    void testInsertDataWhenInserting2RowsThenCallBatchUpdateFor2Records() {
        final var data = Arrays.asList(
                new Object[]{"Berbigão", "Cerastoderma edule", "white"},
                new Object[]{"Conquilha", "Donax trunculus", "blue"}
        );

        final int[] ints = springFlash26Launcher.insertData(data);

        for (int i : ints) {
            assertThat(ints[i]).isEqualTo(1);
        }
        verify(jdbcTemplate, times(1)).batchUpdate("insert into shells(name, scientificName, predominentColor) values (?,?,?)", data);
    }

    @Test
    void testNoResultQueryOnResultSetExtractorWhenMakingQueryWithNoResultsThenThrowException() {
        assertThrows(UncategorizedSQLException.class,
                () -> springFlash26Launcher.testNoResultQueryOnResultSetExtractor());

        verify(jdbcTemplate, times(1)).query(stringArgumentCaptor.capture(), resultSetExtractorArgumentCaptor.capture());
        final var query = stringArgumentCaptor.getValue();
        assertThat(query).isNotNull();
        assertThat(query).isEqualTo("create table shells(id serial, name varchar(255), scientificName varchar(255), predominentColor varchar(255))");
        final var resultSetExtractorArgumentCaptorValue = resultSetExtractorArgumentCaptor.getValue();
        assertThat(resultSetExtractorArgumentCaptorValue).isNotNull();
    }
}