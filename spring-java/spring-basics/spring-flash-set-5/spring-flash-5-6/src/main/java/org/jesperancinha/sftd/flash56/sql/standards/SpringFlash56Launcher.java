package org.jesperancinha.sftd.flash56.sql.standards;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class SpringFlash56Launcher implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public SpringFlash56Launcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        GREEN.printGenericTitleLn("Service is starting");
        SpringApplication.run(SpringFlash56Launcher.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        ConsolerizerComposer.outSpace().cyan(title("CRUD operation best practices with JdbcTemplate"));

        jdbcTemplate.execute("create table book(" +
                "id int not null auto_increment," +
                "title varchar(255)," +
                "author varchar(255)," +
                "publisher varchar(255)," +
                "`year` bigint" +
                ")");

        final var keyHolder = new GeneratedKeyHolder();

        final String query = "insert into book(title, author, publisher, `year`) values (?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(query, new String[]{"id"});
            stmt.setString(1, "Levantado do Chão");
            stmt.setString(2, "José Saramago");
            stmt.setString(3, "Caminho");
            stmt.setLong(4, 1980);
            return stmt;
        }, keyHolder);
        final long id = keyHolder.getKey().longValue();
        ConsolerizerComposer.outSpace()
                .none()
                .blue("By using an update in writing operations like")
                .cyan("insert")
                .blue("and")
                .cyan("update")
                .blue("and")
                .cyan("delete")
                .ln()
                .newLine()
                .green("we can get the actual id of the row we are inserting in a seamless way")
                .magenta("Our id is %d", id)
                .reset();

        final String query2 = "update book set title = ?, author = ?, publisher = ?, `year` = ? where id = ?";
        final int update = jdbcTemplate.update(query2, "O ano da Morte de Ricardo Reis", "José Samarago", "Caminho", "1984", id);
        ConsolerizerComposer.outSpace()
                .blue("An update should be performed with update")
                .cyan("And the main reason is that we get as a response a direct assessment")
                .blue("Of how many columns have been updated")
                .green(update)
                .reset();


        final List<String> stringList = jdbcTemplate.query("select * from book", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return String.format("Book %s was written by %s on the year %d and published by %s",
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getLong("year"),
                        rs.getString("publisher"));
            }
        });
        ConsolerizerComposer.outSpace()
                .magenta(stringList)
                .reset();

        final int deletedRows = jdbcTemplate.update("delete from book where id = ?", id);
        ConsolerizerComposer.outSpace()
                .blue("%d deleted!", deletedRows)
                .reset();
    }
}
