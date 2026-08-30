package org.jesperancinha.spring.flash24.jdbc;

import org.jesperancinha.spring.flash24.jdbc.template.Concert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = {SpringFlash24LauncherTest.Initializer.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SpringFlash24LauncherTest {

    @MockitoSpyBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SpringFlash24Launcher springFlash24Launcher;

    @SuppressWarnings("unchecked")
    private final ArgumentCaptor<RowMapper<Concert>> rowMapperArgumentCaptor = (ArgumentCaptor<RowMapper<Concert>>) (ArgumentCaptor<?>) ArgumentCaptor.forClass(RowMapper.class);

        private final ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        private final ArgumentCaptor<AbstractFallbackSQLExceptionTranslator> abstractFallbackSQLExceptionTranslatorArgumentCaptor = ArgumentCaptor.forClass(AbstractFallbackSQLExceptionTranslator.class);

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
            .withUsername("postgres")
            .withPassword("admin")
            .withDatabaseName("db");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeEach
    public void setUp() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        springFlash24Launcher.initializeDatabase();
        final String[] concert1 = {"VH1 Divas Live", "New York's Beacon Theatre, US", LocalDateTime.of(1998, 4, 16, 0, 0, 0).toString()};
        final String[] concert2 = {"Lollapalooza 2012", "Chicago’s Grant Park, US", LocalDateTime.of(2012, 8, 12, 16, 0, 0).toString()};
        final List<Object[]> concerts = Arrays.asList(
                concert1, concert2
        );
        springFlash24Launcher.textCRUDCreate(concerts);
        Mockito.reset(jdbcTemplate);
    }

    @Test
    void testContext() {
    }

    @Test
    void testCRUDDeleteWhenCalledThenRemove1And2ButNot3() {
        final var concert1Id = springFlash24Launcher.testCRUDDelete(1);
        final var concert2Id = springFlash24Launcher.testCRUDDelete(2);
        final var concert3Id = springFlash24Launcher.testCRUDDelete(3);

        assertThat(concert1Id).isEqualTo(1);
        assertThat(concert2Id).isEqualTo(1);
        assertThat(concert3Id).isEqualTo(0);
    }

    @Test
    void testCRUDRead2_whenCalledThenReadAsNormal() {
        final var concerts = springFlash24Launcher.testCRUDRead2();

        assertThat(concerts).isNotNull();
        assertThat(concerts).hasSize(2);
        final var concert1 = concerts.get(0);
        final var concert2 = concerts.get(1);
        assertThat(concert1.getName()).isEqualTo("VH1 Divas Live");
        assertThat(concert1.getVenue()).isEqualTo("New York's Beacon Theatre, US");
        assertThat(concert2.getName()).isEqualTo("Lollapalooza 2012");
        assertThat(concert2.getVenue()).isEqualTo("Chicago’s Grant Park, US");
        verify(jdbcTemplate, times(1)).query(stringArgumentCaptor.capture(), rowMapperArgumentCaptor.capture());
        final var rowMapperArgumentCaptorValue = rowMapperArgumentCaptor.getValue();
        assertThat(rowMapperArgumentCaptorValue).isNotNull();
        final var stringArgumentCaptorValue = stringArgumentCaptor.getValue();
        assertThat(stringArgumentCaptorValue).isNotNull();
        assertThat(stringArgumentCaptorValue).isEqualTo("select id, name, venue, local_date_time from concerts");
    }

    @Test
    void testCRUDUpdateWhenCalledWith2RecordsThenUpdate2Records() {
        final Object[] concert1Update = {"<HIDDEN1>", "New York's Beacon Theatre, US", LocalDateTime.of(1998, 4, 16, 0, 0, 0).toString(), 1};
        final Object[] concert2Update = {"<HIDDEN2>", "Chicago’s Grant Park, US", LocalDateTime.of(2012, 8, 12, 16, 0, 0).toString(), 2};
        final List<Object[]> concertsUpdate = Arrays.asList(concert1Update, concert2Update);

        final int[] ints = springFlash24Launcher.testCRUDUpdate(concertsUpdate);
        final var concerts = springFlash24Launcher.testCRUDRead();

        assertThat(ints).hasSize(2);
        assertThat(ints).containsOnly(1);
        assertThat(concerts).isNotNull();
        assertThat(concerts).hasSize(2);
        final var concert1 = concerts.get(0);
        final var concert2 = concerts.get(1);
        assertThat(concert1.getName()).isEqualTo("<HIDDEN1>");
        assertThat(concert1.getVenue()).isEqualTo("New York's Beacon Theatre, US");
        assertThat(concert2.getName()).isEqualTo("<HIDDEN2>");
        assertThat(concert2.getVenue()).isEqualTo("Chicago’s Grant Park, US");
        verify(jdbcTemplate, times(1)).query(stringArgumentCaptor.capture(), rowMapperArgumentCaptor.capture());
        final var rowMapperArgumentCaptorValue = rowMapperArgumentCaptor.getValue();
        assertThat(rowMapperArgumentCaptorValue).isNotNull();
        final var stringArgumentCaptorValue = stringArgumentCaptor.getValue();
        assertThat(stringArgumentCaptorValue).isNotNull();
        assertThat(stringArgumentCaptorValue).isEqualTo("select id, name, venue, local_date_time from concerts");
        verify(jdbcTemplate, times(1)).batchUpdate("UPDATE concerts set name = ?, venue = ?, local_date_time = ? WHERE id = ?", concertsUpdate);
    }

    @Test
    void testCRUDReadWhenCalledThenReadAsNormal() {
        final var concerts = springFlash24Launcher.testCRUDRead();

        assertThat(concerts).isNotNull();
        assertThat(concerts).hasSize(2);
        final var concert1 = concerts.get(0);
        final var concert2 = concerts.get(1);
        assertThat(concert1.getName()).isEqualTo("VH1 Divas Live");
        assertThat(concert1.getVenue()).isEqualTo("New York's Beacon Theatre, US");
        assertThat(concert2.getName()).isEqualTo("Lollapalooza 2012");
        assertThat(concert2.getVenue()).isEqualTo("Chicago’s Grant Park, US");
        verify(jdbcTemplate, times(1)).query(stringArgumentCaptor.capture(), rowMapperArgumentCaptor.capture());
        final var rowMapperArgumentCaptorValue = rowMapperArgumentCaptor.getValue();
        assertThat(rowMapperArgumentCaptorValue).isNotNull();
        final var stringArgumentCaptorValue = stringArgumentCaptor.getValue();
        assertThat(stringArgumentCaptorValue).isNotNull();
        assertThat(stringArgumentCaptorValue).isEqualTo("select id, name, venue, local_date_time from concerts");
    }

    @Test
    void testCRUDCreateWhenCalledWith2RecordsThenInsertTwoRecordsIndependently() {
        final String[] concert1 = {"VH1 Divas Live", "New York's Beacon Theatre, US", LocalDateTime.of(1998, 4, 16, 0, 0, 0).toString()};
        final String[] concert2 = {"Lollapalooza 2012", "Chicago’s Grant Park, US", LocalDateTime.of(2012, 8, 12, 16, 0, 0).toString()};
        final List<Object[]> concerts = Arrays.asList(
                concert1, concert2
        );
        final int[] ints = springFlash24Launcher.textCRUDCreate(concerts);

        verify(jdbcTemplate, times(1)).batchUpdate("insert into concerts(name, venue, local_date_time) values (?,?,?)", concerts);
        assertThat(ints).hasSize(2);
        for (int anInt : ints) {
            assertThat(anInt).isEqualTo(1);
        }
    }


    @Test
    void testExceptionWhenDataAccessExceptionIsCustomThenThrowEnrichedDataAccessException() {
        assertThrows(DataAccessException.class, () -> springFlash24Launcher.testException());

        verify(jdbcTemplate, times(1)).setExceptionTranslator(abstractFallbackSQLExceptionTranslatorArgumentCaptor.capture());
        verify(jdbcTemplate, times(1)).execute(stringArgumentCaptor.capture());
        final var abstractFallbackSQLExceptionTranslatorArgumentCaptorValue = abstractFallbackSQLExceptionTranslatorArgumentCaptor.getValue();
        assertThat(abstractFallbackSQLExceptionTranslatorArgumentCaptorValue).isNotNull();
        final String value = stringArgumentCaptor.getValue();
        assertThat(value).isEqualTo("This is not and will never be a query");
    }
}