package org.jesperancinha.std.flash24.jdbc;

import org.jesperancinha.std.flash24.jdbc.template.Concert;
import org.jesperancinha.std.flash24.jdbc.template.CustomDataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BLUE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.MAGENTA;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;

@SpringBootApplication
@RestController
public class SpringFlash24Launcher implements CommandLineRunner {
    final JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(SpringFlash24Launcher.class);

    public SpringFlash24Launcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash24Launcher.class, args);
    }

    @Override
    public void run(String... strings) {
        jdbcTemplate.setExceptionTranslator(new AbstractFallbackSQLExceptionTranslator() {
            @Override
            protected DataAccessException doTranslate(String task, String sql, SQLException ex) {
                return new CustomDataAccessException(String.format("Task: %s, Sql: %s, Exception: %s", task, sql, ex.getMessage()));
            }
        });
        try {
            jdbcTemplate.execute("This is not and will never be a query");
        } catch (final DataAccessException exception) {
            BLUE.printGenericTitleLn("Exception Handling");
            RED.printExpectedException("We are able to handle this exception since we have crated an Exception Translator", exception);
        }
        jdbcTemplate.execute("drop table concerts if exists ");
        jdbcTemplate.execute("create table concerts(" +
                "id SERIAL, name varchar(255), venue varchar(255), local_date_time varchar(255))");

        MAGENTA.printGenericLn("from: https://www.songkick.com/artists/1168629-roisin-murphy");

        final String[] concert1 = {"6 Music Festival - By Night", "The Roundhouse, London, UK", LocalDateTime.of(2020, 3, 7, 17, 0, 0).toString()};
        final String[] concert2 = {"Dekmantel festival 2019", "Amsterdam Forest / Amsterdamse Bos, Amstelveen, Netherlands", LocalDateTime.of(2019, 7, 31, 0, 0, 0).toString()};
        List<Object[]> concerts = Arrays.asList(
                concert1, concert2
        );
        GREEN.printGenericTitleLn("from: https://en.wikipedia.org/wiki/Create,_read,_update_and_delete");
        ORANGE.printGenericLn("Performing a CRUD operation -> Create = INSERT");
        jdbcTemplate.batchUpdate("insert into concerts(name, venue, local_date_time) values (?,?,?)", concerts);
        ORANGE.printGenericLn("Performing a CRUD operation -> Read = select");
        jdbcTemplate.query(
                "select id, name, venue, local_date_time from concerts",
                (rs, rowNum) ->
                        new Concert(rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("venue"),
                                rs.getString("local_date_time"))
        ).forEach(concert -> MAGENTA.printGenericLn(concert.toString()));
        ORANGE.printGenericLn("Performing a CRUD operation -> Update = update");
        final String[] newConcert1 = Arrays.copyOf(concert1, concert1.length + 1);
        final String[] newConcert2 = Arrays.copyOf(concert2, concert2.length + 1);
        newConcert1[1] = "unknown venue";
        newConcert1[3] = "1";
        newConcert2[1] = "unknown venue";
        newConcert2[3] = "2";
        List<Object[]> concerts2 = Arrays.asList(
                newConcert1, newConcert2
        );
        jdbcTemplate.batchUpdate("UPDATE concerts set name = ?, venue = ?, local_date_time = ? WHERE id = ?", concerts2);
        ORANGE.printGenericLn("Performing a CRUD operation -> Read = select");
        jdbcTemplate.query(
                "select id, name, venue, local_date_time FROM concerts",
                (rs, rowNum) -> {
                    ORANGE.printGenericLn("This is row number %d", rowNum);
                    return new Concert(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("venue"),
                            rs.getString("local_date_time"));

                }
        ).forEach(concert -> MAGENTA.printGenericLn(concert.toString()));
        ORANGE.printGenericLn("Performing a CRUD operation -> Delete = delete");
        jdbcTemplate.update("delete from concerts where id = ?", 1);
        ORANGE.printGenericLn("Performing a CRUD operation -> Read = select");
        jdbcTemplate.query(
                "select id, name, venue, local_date_time FROM concerts",
                (rs, rowNum) ->
                        new Concert(rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("venue"),
                                rs.getString("local_date_time"))
        ).forEach(concert -> MAGENTA.printGenericLn(concert.toString()));
    }
}
