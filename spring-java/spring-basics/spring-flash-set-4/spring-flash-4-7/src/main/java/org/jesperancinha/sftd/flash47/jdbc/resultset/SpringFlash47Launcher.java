package org.jesperancinha.sftd.flash47.jdbc.resultset;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringFlash47Launcher implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public SpringFlash47Launcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{SpringFlash47Launcher.class}, args);
    }

    @Override
    public void run(String... args) {
        initializeDatabase();

        final var flowers = createData();

        ConsolerizerComposer.outSpace()
                .random("There are in total %d💐 flowers in the database", flowers)
                .reset();

        final String fullMessageStatus = getFlowerSummary();

        ConsolerizerComposer.out(" ")
                .magenta("We got the flowers result from the database using")
                .green("ResultSetExtractor")
                .magenta("and here they are:")
                .newLine()
                .brightCyan(fullMessageStatus)
                .toConsoleLn();

    }

    private String getFlowerSummary() {
        return jdbcTemplate.query("select * from flowers", rs -> {
            final var sb = new StringBuilder();
            while (rs.next()) {
                sb.append(String.format("There are %d %s available\n",
                        rs.getInt("available"),
                        rs.getString("species")));
            }
            return sb.toString();
        });
    }

    private int createData() {
        final int tulip = jdbcTemplate.update("insert into flowers (species, available) values ( ?,? )", "tulip", 10);
        final int rose = jdbcTemplate.update("insert into flowers (species, available) values ( ?,? )", "rose", 100);
        final int carnations = jdbcTemplate.update("insert into flowers (species, available) values ( ?,? )", "carnations", 1000);
        return tulip + rose + carnations;
    }

    private void initializeDatabase() {
        jdbcTemplate.execute("create table flowers(" +
                "ID int not null auto_increment," +
                "SPECIES VARCHAR(255)," +
                "AVAILABLE INT" +
                ")");
    }
}
