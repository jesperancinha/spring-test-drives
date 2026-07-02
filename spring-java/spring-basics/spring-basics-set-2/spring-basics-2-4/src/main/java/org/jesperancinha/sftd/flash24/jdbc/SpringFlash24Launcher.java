package org.jesperancinha.sftd.flash24.jdbc;

import jakarta.validation.constraints.NotNull;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash24.jdbc.template.Concert;
import org.jesperancinha.sftd.flash24.jdbc.template.CustomDataAccessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;

@SpringBootApplication
@RestController
public class SpringFlash24Launcher implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.driverClassName:}")
    private String driver;

    public SpringFlash24Launcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash24Launcher.class, args);
    }

    @Override
    public void run(String... strings) {
        try {
            testException();
        } catch (DataAccessException dataAccessException) {
            ConsolerizerComposer.outSpace()
                    .yellow(dataAccessException.getMessage())
                    .reset();
        }
        initializeDatabase();

        final var concerts = getData1();

        textCRUDCreate(concerts);
        testCRUDRead();

        final var concerts2 = getData2(concerts);

        testCRUDUpdate(concerts2);
        testCRUDRead2();
        testCRUDDelete(1);
        testCRUDRead();
    }

    /**
     * Modified dataset
     * Each {@link Concert} will be associated with an unknown venue. The Id is given at index 3 of each of the {@link Concert} array.
     *
     * @return A list of an array of data, which defines each {@link Concert} being updated into the database.
     */
    final List<Object[]> getData2(List<Object[]> concerts) {
        ORANGE.printGenericLn("Performing a CRUD operation -> Update = update");
        final Object[] concert1 = concerts.get(0);
        final Object[] concert2 = concerts.get(1);
        final Object[] newConcert1 = Arrays.copyOf(concert1, concert1.length + 1);
        final Object[] newConcert2 = Arrays.copyOf(concert2, concert2.length + 1);
        newConcert1[1] = "unknown venue";
        newConcert1[3] = 1;
        newConcert2[1] = "unknown venue";
        newConcert2[3] = 2;
        return Arrays.asList(
                newConcert1, newConcert2
        );
    }

    /**
     * Original data set
     *
     * @return A list of an array of data, which defines each {@link Concert} being inserted into the database.
     */
    private List<Object[]> getData1() {
        MAGENTA.printGenericLn("from: https://www.songkick.com/artists/1168629-roisin-murphy");
        final Object[] concert1 = {"6 Music Festival - By Night", "The Roundhouse, London, UK", LocalDateTime.of(2020, 3, 7, 17, 0, 0).toString()};
        final Object[] concert2 = {"Dekmantel festival 2019", "Amsterdam Forest / Amsterdamse Bos, Amstelveen, Netherlands", LocalDateTime.of(2019, 7, 31, 0, 0, 0).toString()};
        return Arrays.asList(
                concert1, concert2
        );
    }

    /**
     * Delete CRUD operation. Removes the element with id = 1 from the database
     *
     * @param id
     * @return The number of rows deleted. In this case, it should always be 1
     */
    final int testCRUDDelete(int id) {
        ORANGE.printGenericLn("Performing a CRUD operation -> Delete = delete");
        return jdbcTemplate.update("delete from concerts where id = ?", id);
    }

    /**
     * Reads the data from the database using a {@link org.springframework.jdbc.core.RowMapper}
     * <p>
     * In this case, it also returns as an output, the row number associated with the query result
     *
     * @return
     */
    final List<Concert> testCRUDRead2() {
        return jdbcTemplate.query(
                "select id, name, venue, local_date_time from concerts",
                (rs, rowNum) -> {
                    ORANGE.printGenericLn("This is row number %d", rowNum);
                    return new Concert(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("venue"),
                            rs.getString("local_date_time"));

                }
        ).stream()
                .peek(concert -> MAGENTA.printGenericLn(concert.toString()))
                .collect(Collectors.toList());
    }

    /**
     * Performs the CRUD update, using as input parameter a {@link List} of arrays containing all the concerts to be updated.
     *
     * @param concerts2
     * @return
     */
    int[] testCRUDUpdate(List<Object[]> concerts2) {
        final int[] ints = jdbcTemplate.batchUpdate("UPDATE concerts set name = ?, venue = ?, local_date_time = ? WHERE id = ?", concerts2);
        ORANGE.printGenericLn("Performing a CRUD operation -> Read = select");
        return ints;
    }

    /**
     * Reads the {@link Concert} data with a  {@link org.springframework.jdbc.core.RowMapper}
     * <p>
     * A row mapper can return a {@link List} of concerts. Each {@link Concert}, is parsed in a lambda mapper.
     *
     * @return
     */
    List<Concert> testCRUDRead() {
        ORANGE.printGenericLn("Performing a CRUD operation -> Read = select");
        return jdbcTemplate.query(
                "select id, name, venue, local_date_time from concerts",
                (rs, rowNum) ->
                        new Concert(rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("venue"),
                                rs.getString("local_date_time"))
        ).stream()
                .peek(concert -> MAGENTA.printGenericLn(concert.toString()))
                .collect(Collectors.toList());
    }


    /**
     * Creates the concert data
     *
     * @param concerts A list of object arrays ready for the {@link JdbcTemplate#batchUpdate(String...)} method
     * @return
     */
    int[] textCRUDCreate(List<Object[]> concerts) {
        GREEN.printGenericTitleLn("from: https://en.wikipedia.org/wiki/Create,_read,_update_and_delete");
        ORANGE.printGenericLn("Performing a CRUD operation -> Create = INSERT");
        return jdbcTemplate.batchUpdate("insert into concerts(name, venue, local_date_time) values (?,?,?)", concerts);
    }

    /**
     * Initialization of the table {@link Concert} database
     */
    void initializeDatabase() {
        jdbcTemplate.execute("drop table if exists concerts;");

        if(driver.equals("org.h2.Driver")){
            jdbcTemplate.execute("create table concerts(" +
                    "id int auto_increment primary key, name varchar(255), venue varchar(255), local_date_time varchar(255))");
        } else {
            jdbcTemplate.execute("create table concerts(" +
                    "id serial primary key, name varchar(255), venue varchar(255), local_date_time varchar(255))");
        }
    }

    /**
     * Test for the {@link DataAccessException}
     */
    void testException() {
        jdbcTemplate.setExceptionTranslator(new AbstractFallbackSQLExceptionTranslator() {
            @Override
            protected DataAccessException doTranslate(
                    @NotNull
                    final String task, final String sql,
                    @NotNull
                    final SQLException ex) {
                return new CustomDataAccessException(String.format("Task: %s, Sql: %s, Exception: %s", task, sql, ex.getMessage()));
            }
        });
        try {
            jdbcTemplate.execute("This is not and will never be a query");
        } catch (final DataAccessException exception) {
            BLUE.printGenericTitleLn("Exception Handling");
            RED.printExpectedException("We are able to handle this exception since we have crated an Exception Translator", exception);
            throw exception;
        }
    }
}
