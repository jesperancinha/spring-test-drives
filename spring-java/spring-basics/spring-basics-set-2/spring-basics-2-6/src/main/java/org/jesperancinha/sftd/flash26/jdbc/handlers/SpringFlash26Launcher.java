package org.jesperancinha.sftd.flash26.jdbc.handlers;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash26.jdbc.handlers.model.Shell;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.Arrays;
import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;
import static org.jesperancinha.console.consolerizer.console.Consolerizer.printRainbowTitleLn;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class SpringFlash26Launcher implements CommandLineRunner {

    final JdbcTemplate jdbcTemplate;

    public SpringFlash26Launcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash26Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            testNoResultQueryOnResultSetExtractor();
        } catch (final UncategorizedSQLException uncategorizedSQLException) {
            ConsolerizerComposer.outSpace()
                    .red(uncategorizedSQLException)
                    .red();
        }
        initializeDatabase();
        final List<Object[]> data = Arrays.asList(
                new Object[]{"Berbigão", "Cerastoderma edule", "white"},
                new Object[]{"Conquilha", " Donax trunculus", "blue"}
        );
        insertData(data);
        final String query = testResultSetExtractor();
        ConsolerizerComposer.outSpace()
                .cyan(title("Testing ResultSetExtractor"))
                .magenta(query)
                .reset();
        final List<Shell> allShells = testRowMapper();
        ConsolerizerComposer.outSpace()
                .cyan(title("Testing RowMapper"))
                .magenta(allShells)
                .reset();
        ConsolerizerComposer.outSpace()
                .blue("All Shells -> %s", allShells)
                .blue("The result is %s", query)
                .magenta("Just like RowMapper, a ResultSetExtractor gives us the possibility to get all results")
                .magenta("The Difference is that while RowMapper allows us to map a row to its index number from the result set, a ResultSetExtractor just returns the result set that we then have to iterate.")
                .reset();
    }

    List<Shell> testRowMapper() {
        return jdbcTemplate.query("select name, scientificName, predominentColor from shells"
                , (rs, rowNum) -> {
                    GREEN.printGenericLn("The shell best known as %s, has a scientific name of %s and is mostly found with color %s",
                            rs.getString("name"), rs.getString("scientificName"), rs.getString("predominentColor"));

                    return new Shell(rs.getString("name"), rs.getString("scientificName"), rs.getString("predominentColor"));
                });
    }

    String testResultSetExtractor() {
        return jdbcTemplate.query("select name, scientificName, predominentColor from shells"
                , rs -> {
                    while (rs.next()) {
                        printRainbowTitleLn("The shell best known as %s, has a scientific name of %s and is mostly found with color %s",
                                rs.getString("name"), rs.getString("scientificName"), rs.getString("predominentColor"));
                    }
                    return "OK!";
                });
    }

    int[] insertData(List<Object[]> data) {
        return jdbcTemplate.batchUpdate("insert into shells(name, scientificName, predominentColor) values (?,?,?)",
                data);
    }

    void initializeDatabase() {
        jdbcTemplate.execute("drop table shells if exists");
        jdbcTemplate.execute("create table shells(id serial, name varchar(255), scientificName varchar(255), predominentColor varchar(255))");
    }

    void testNoResultQueryOnResultSetExtractor() {
        try {
            jdbcTemplate.query("create table shells(id serial, name varchar(255), scientificName varchar(255), predominentColor varchar(255))"
                    , (ResultSetExtractor<String>) rs -> {
                        ConsolerizerComposer.outSpace()
                                .red("A create query will not yield any results")
                                .red("This means that if the code enters this scope,")
                                .red("we should end the program, since this isn't necessary")
                                .reset();
                        System.exit(1);
                        return null;
                    });
        } catch (final UncategorizedSQLException uncategorizedSQLException) {
            RED.printExpectedException("This is an expected exception, because only queries are allowed. Although a create is sometimes referred to as a query, in this context, it must return results.", uncategorizedSQLException);
            throw uncategorizedSQLException;
        }
    }
}
