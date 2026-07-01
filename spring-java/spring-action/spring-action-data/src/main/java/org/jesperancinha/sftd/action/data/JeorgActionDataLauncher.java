package org.jesperancinha.sftd.action.data;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.CYAN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class JeorgActionDataLauncher implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public JeorgActionDataLauncher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(JeorgActionDataLauncher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final var dataSource = jdbcTemplate.getDataSource();
        ConsolerizerComposer
                .outSpace()
                .blue(title("Examining data sources. What do you do and what does the template/Spring does?"))
                .magenta("Check https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/data-access.html#jdbc for details");
        ConsolerizerComposer
                .outSpace()
                .cyan(title("1. Examining data sources. You define the data source"))
                .green("Take a look into application.properties")
                .yellow("You have defined this in order for Spring to create the DataSource")
                .green("The %s, does not create the %s", jdbcTemplate, dataSource)
                .yellow("You create the data source definition, but the %s itself is created by Spring", dataSource);


        assert dataSource != null;
        final Connection connection = dataSource.getConnection();
        ConsolerizerComposer
                .outSpace()
                .cyan(title("2. Opening the connection. Spring opens the connection"))
                .green("You've seen above how the %s is created", dataSource)
                .yellow("You did not intervene in anyway to create to create the %s", dataSource)
                .green("This is of course done seamlessly by Spring")
                .yellow("You create the data source definition, but the %s\nitself is created by Spring by means of the %s", connection, dataSource);

        final String query = "create table parasites(" +
                "name varchar(255)," +
                "quantity int" +
                ")";
        jdbcTemplate.execute(query);
        ConsolerizerComposer
                .outSpace()
                .cyan(title("3. Creating SQL statements. You create them!"))
                .green("Statements need to be defined by us")
                .yellow("The %s solely executes it", jdbcTemplate)
                .green("This is of course done seamlessly by Spring")
                .yellow("You create the \"%s\"\nwhich then gets executed by the %s\nby means of the %s\nwhich uses a %s\nto do it"
                        , query, jdbcTemplate, dataSource, connection);


        final String query2 = "insert into parasites(name, quantity)values(?,?)";
        final PreparedStatementCallback<String> preparedStatementCallback = new PreparedStatementCallback<>() {
            @Override
            public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, "Ophiocordyceps");
                ps.setLong(2, 1000);
                ps.execute();
                return "Ok";
            }
        };
        final String execute = jdbcTemplate.execute(query2, preparedStatementCallback);
        ConsolerizerComposer
                .outSpace()
                .cyan(title("4. Preparing and executing SQL statements. Spring prepares and executes them!"))
                .green("Statements need to be defined by us, but Spring prepares them and executes them!")
                .yellow("When we define a %s", jdbcTemplate)
                .green("We can then execute the query")
                .yellow("In the inner workings of the execution, our query %s"
                        , query2)
                .green("gets executed by the %s", jdbcTemplate)
                .yellow("which prepares the statement before hand")
                .green("We do define how to prepare it, but preparation means optimizing the query before execution")
                .yellow("and Spring does that for us by means of a PreparedStatement")
                .green("We access the PreparedStatement via the callback %s", preparedStatementCallback)
                .yellow("And this is the result %s", execute);


        final var query3 = "select * from parasites";
        final RowMapper<String> rowMapper = new RowMapper<>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("name").concat("" + rs.getLong("quantity"));
            }
        };
        final List<String> allParasites = jdbcTemplate.query(query3, rowMapper);
        ConsolerizerComposer
                .outSpace()
                .cyan(title("5. Setting up iterative loops. Spring sets up the iterative loops for you!"))
                .green("Spring sets up iterative loops for you")
                .yellow("When we use a %s", jdbcTemplate)
                .none()
                .green("we can use a").blue("query")
                .newLine()
                .yellow("This way when we create").blue(query3)
                .newLine()
                .green("We can finally get a list of results").blue(allParasites)
                .newLine()
                .reset();

        ConsolerizerComposer
                .outSpace()
                .cyan(title("6. Perform work on each iteration. You determine the work to be done on every iteration"))
                .none()
                .green("Although Spring sets up the iterators for you,").red("you are the one responsible to determine the iteration work")
                .newLine()
                .yellow("And this is why we use a RowMapper as an example ->").orange(rowMapper)
                .newLine()
                .reset();

        final Connection connection2 = dataSource.getConnection();
        connection2.close();
        final String query4 = "insert into parasites(name, quantity)values(?,?)";
        final PreparedStatementCallback<String> preparedStatementCallback2 = new PreparedStatementCallback<>() {
            @Override
            public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, "Emerald cockroach wasp");
                ps.setLong(2, 2000);
                ps.execute();
                return "Ok";
            }
        };
        final String execute4 = jdbcTemplate.execute(query4, preparedStatementCallback2);

        ConsolerizerComposer
                .outSpace()
                .cyan(title("7. Closing connections, the statement and the result set. Spring determines all of this"))
                .green("However you can determine this")
                .yellow("We have closed our connection")
                .green("and so, its closed status must be")
                .yellow(connection2.isClosed())
                .green("While this is true, if we attempt to run another query via the template")
                .yellow(query4)
                .green("We are still able to get results")
                .yellow(execute4)
                .green("And this is because a new connection has been opened")
                .reset();


        final AbstractFallbackSQLExceptionTranslator exceptionTranslator = new AbstractFallbackSQLExceptionTranslator() {
            @Override
            protected DataAccessException doTranslate(String task, String sql, SQLException ex) {
                return new DataAccessException(String.format("Task: %s, Sql: %s, Exception: %s", task, sql, ex.getMessage())) {
                };
            }
        };
        jdbcTemplate.setExceptionTranslator(exceptionTranslator);
        try {
            jdbcTemplate.execute("select * from non_parasites");
        } catch (final DataAccessException exception) {
            CYAN.printGenericTitleLn("Exception Handling");
            RED.printExpectedException("We are able to handle this exception since we have crated an Exception Translator", exception);
        }
        ConsolerizerComposer
                .outSpace()
                .cyan(title("8. Processing and handling exceptions. Spring determines all of this"))
                .green("We can very easily handle exceptions in Spring")
                .yellow("Spring can process and handle exceptions for you")
                .green("We just did that by using an exception translator")
                .yellow(exceptionTranslator)
                .green("Although we define how the handling is being done, the handling itself and the processing of exceptions is done by Spring")
                .yellow("Spring is ready to process all transactions into a DataAccessException")
                .reset();
    }
}
