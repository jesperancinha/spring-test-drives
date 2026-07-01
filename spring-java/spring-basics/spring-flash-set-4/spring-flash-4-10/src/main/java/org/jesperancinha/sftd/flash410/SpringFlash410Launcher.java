package org.jesperancinha.sftd.flash410;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Profile("!encode")
public class SpringFlash410Launcher implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    public SpringFlash410Launcher(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{SpringFlash410Launcher.class}, args);
    }

    @Override
    public void run(String... args) {
        jdbcTemplate.update(
                "insert into users(username, password, enabled)\n" +
                        "values (?,?,?);", "admin", passwordEncoder.encode("admin"), true);
        jdbcTemplate.update(
                "insert into users(username, password, enabled)\n" +
                        "values (?,?,?);", "user", passwordEncoder.encode("user"), true);
        jdbcTemplate.update("insert into user_roles (username, role)\n" +
                "values (?,?)", "admin", "ROLE_USER");
        jdbcTemplate.update("insert into user_roles (username, role)\n" +
                "values (?,?)", "user", "ROLE_USER");
        jdbcTemplate.update("insert into user_roles (username, role)\n" +
                "values (?,?)", "admin", "ROLE_ADMIN");

        jdbcTemplate.query("select * from users", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                ConsolerizerComposer.out(" ")
                        .green("User")
                        .brightGreen(rs.getString("username"))
                        .green("Password")
                        .brightGreen(rs.getString("password"))
                        .green("Enabled")
                        .brightGreen(rs.getBoolean("enabled"))
                        .toConsoleLn();
            }
        });
        final List<String> query = jdbcTemplate.query("select * from users", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                ConsolerizerComposer.out(" ")
                        .green("User")
                        .brightGreen(rs.getString("username"))
                        .green("Password")
                        .brightGreen(rs.getString("password"))
                        .green("Enabled")
                        .brightGreen(rs.getBoolean("enabled"))
                        .toConsoleLn();
                return rs.getString("username");
            }
        });
        ConsolerizerColor.MAGENTA.printGenericLn(query);

        final List<String> collecting = jdbcTemplate.query("select * from users", new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                final var collection = new ArrayList<String>();
                while (rs.next()) {
                    ConsolerizerComposer.out(" ")
                            .green("User")
                            .brightGreen(rs.getString("username"))
                            .green("Password")
                            .brightGreen(rs.getString("password"))
                            .green("Enabled")
                            .brightGreen(rs.getBoolean("enabled"))
                            .toConsoleLn();
                    collection.add(rs.getString("username"));
                }
                return collection;
            }
        });
        ConsolerizerColor.MAGENTA.printGenericLn(collecting);

    }
}