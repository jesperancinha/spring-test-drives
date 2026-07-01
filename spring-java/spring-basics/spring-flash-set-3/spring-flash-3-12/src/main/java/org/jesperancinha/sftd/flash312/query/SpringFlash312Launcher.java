package org.jesperancinha.sftd.flash312.query;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class SpringFlash312Launcher implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public SpringFlash312Launcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash312Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        initializeDatabase();
        insertDemoData();
        ConsolerizerComposer
                .outSpace()
                .green(title("Starting test with a single query that fails"))
                .reset();
        final var rancidObject = getRancidObject();
        ConsolerizerComposer
                .outSpace()
                .blue(title("queryForObject - RancidObject"))
                .magenta()
                .jsonPrettyPrint(rancidObject)
                .reset();
        ConsolerizerComposer
                .outSpace()
                .green(title("Starting test with a single query with a single result that works"))
                .reset();
        final var rancidObject2 = getObject();
        ConsolerizerComposer
                .outSpace()
                .blue(title("queryForObject - RancidObject 2"))
                .magenta(rancidObject2)
                .jsonPrettyPrint(rancidObject2)
                .reset();
        final var maps = getMaps();
        ConsolerizerComposer.outSpace().blue(title("queryForList - List"))
                .magenta(maps)
                .jsonPrettyPrint(maps)
                .reset();
    }

    private void insertDemoData() {
        final var allRancidObjects = Arrays.asList(new Object[]{"Black Coat"}, new Object[]{"Black Shoes"}, new Object[]{"Black Hat"}, new Object[]{"Cadillac"});
        jdbcTemplate.batchUpdate("INSERT INTO rancid_objects (name) VALUES (?)", allRancidObjects);
    }

    private void initializeDatabase() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS rancid_objects(\n" +
                "ID INT NOT NULL AUTO_INCREMENT,\n" +
                "NAME VARCHAR(255) NOT NULL)");
    }

    private List<Map<String, Object>> getMaps() {
        return jdbcTemplate.queryForList("SELECT * FROM rancid_objects");
    }

    private RancidObject getObject() {
        return jdbcTemplate.queryForObject("SELECT * FROM rancid_objects WHERE ID = 1",
                (rs, rowNum) -> {
                    ORANGE.printGenericLn("Row number is -> %s", rowNum);
                    ORANGE.printGenericLn("Name is -> %s", rs.getString("name"));
                    return new RancidObject(rs.getString("name"));
                });
    }

    private RancidObject getRancidObject() {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM rancid_objects",
                    (rs, rowNum) -> {
                        ORANGE.printGenericLn("Row number is -> %s", rowNum);
                        ORANGE.printGenericLn("Name is -> %s", rs.getString("name"));
                        return new RancidObject(rs.getString("name"));
                    });
        } catch (final IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException) {
            YELLOW.printExpectedException("We have returned all 4 rancid objects! This will not work for queryForObject", incorrectResultSizeDataAccessException.getMessage());
            return null;
        }
    }
}