package org.jesperancinha.std.flash214.transactions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringFlash214Launcher implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public SpringFlash214Launcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash214Launcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        jdbcTemplate.execute("SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL READ UNCOMMITTED");
//        jdbcTemplate.execute("SET LOCK_MODE 0");
        jdbcTemplate.execute("SET GLOBAL TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;");
        jdbcTemplate.execute("SET SESSION tx_isolation='READ-UNCOMMITTED';");
        jdbcTemplate.execute("DELETE FROM CAR;");
    }
}
