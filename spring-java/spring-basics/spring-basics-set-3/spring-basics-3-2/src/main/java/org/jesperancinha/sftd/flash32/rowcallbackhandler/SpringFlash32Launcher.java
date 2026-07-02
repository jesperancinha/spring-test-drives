package org.jesperancinha.sftd.flash32.rowcallbackhandler;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringFlash32Launcher implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.driverClassName:}")
    private String driver;

    public SpringFlash32Launcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash32Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        createTables();
        insertData();
        performRowCallbackHandler();
    }

    private void performRowCallbackHandler() {
        jdbcTemplate.query("SELECT * FROM WHEN_MUSIC", rs -> {
            ConsolerizerColor.MAGENTA.printGenericLn(
                    "The artist -> %s when -> %s", rs.getString("Artist"), rs.getString("When_Music"));
        });
    }

    private void insertData() {
        jdbcTemplate.update("INSERT INTO WHEN_MUSIC(ARTIST, WHEN_MUSIC) VALUES (?, ?)", "The Doors", "The Music's Over");
        jdbcTemplate.update("INSERT INTO WHEN_MUSIC(ARTIST, WHEN_MUSIC) VALUES (?, ?)", "Green Day", "I come around");
    }

    private void createTables() {
        if (driver.equals("org.h2.Driver")) {
            jdbcTemplate.execute("CREATE TABLE WHEN_MUSIC(\n" +
                    "   ID INT NOT NULL AUTO_INCREMENT,\n" +
                    "   ARTIST VARCHAR(255) NOT NULL,\n" +
                    "   WHEN_MUSIC VARCHAR(255) NOT NULL,\n" +
                    "   PRIMARY KEY (ID)\n" +
                    ");");
        } else {
            jdbcTemplate.execute("CREATE TABLE WHEN_MUSIC(\n" +
                    "   ID SERIAL PRIMARY KEY,\n" +
                    "   ARTIST VARCHAR(255) NOT NULL,\n" +
                    "   WHEN_MUSIC VARCHAR(255) NOT NULL\n" +
                    ");");
        }
    }


}
