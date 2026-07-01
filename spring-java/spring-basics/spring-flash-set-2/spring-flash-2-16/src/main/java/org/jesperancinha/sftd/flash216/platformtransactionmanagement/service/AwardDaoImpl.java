package org.jesperancinha.sftd.flash216.platformtransactionmanagement.service;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash216.platformtransactionmanagement.domain.Award;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@Component
public class AwardDaoImpl implements AwardDao {
    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager transactionManager;

    /**
     * @param dataSource         {@link DataSource} The currently configured datasource
     * @param transactionManager {@link PlatformTransactionManager} The currently configured platform transaction manager
     */
    public AwardDaoImpl(final DataSource dataSource, final PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Creates the tables needed for this module
     */
    @Override
    public void createTables() {
        jdbcTemplate.execute("CREATE TABLE AWARDS(\n" +
                "   ID INT NOT NULL AUTO_INCREMENT,\n" +
                "   ARTIST VARCHAR(255) NOT NULL,\n" +
                "   AWARD VARCHAR(255) NOT NULL,\n" +
                "   AWARD_DATE TIMESTAMP NOT NULL,\n" +
                "   PRIMARY KEY (ID)\n" +
                ");");
    }

    /**
     * Removes all data from the table {@link Award}, to allow a fresh start for the following test
     */
    @Override
    public void resetDatabase() {
        jdbcTemplate.execute("delete from awards;");
    }


    /**
     * Creates an artist/award record in a typical fashion.
     * The {@link PlatformTransactionManager} allows for commits and rollbacks when needed.
     *
     * @param artist        The artist name
     * @param award         The award name
     * @param awardDateTime The date and time when it was awarded
     * @return The resulting {@link Award}
     */
    @Override
    public Award create(String artist, String award, LocalDateTime awardDateTime) {
        final var defaultTransactionDefinition = new DefaultTransactionDefinition();
        final var status = transactionManager.getTransaction(defaultTransactionDefinition);
        try {
            final int update = jdbcTemplate.update("insert into AWARDS (ARTIST, AWARD, AWARD_DATE) values (?, ?, ?)", artist, award, awardDateTime);
            ConsolerizerComposer.outSpace()
                    .green("Updated %d record(s)", update);
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            System.out.println("Error in creating record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return getAward();
    }

    /**
     * Tries to create an artist/name registration but it will purpously fail in order to demonstrat the rollback mechanism
     *
     * @param artist        The artist name
     * @param award         The award name
     * @param awardDateTime The date and time when it was awarded
     * @return null
     */
    @Override
    public Award createRollback(String artist, String award, LocalDateTime awardDateTime) {
        final var defaultTransactionDefinition = new DefaultTransactionDefinition();
        final var status = transactionManager.getTransaction(defaultTransactionDefinition);
        try {
            final int update = jdbcTemplate.update("insert into AWARDS (ARTIST, AWARD, AWARD_DATE) values (?, ?, ?)", artist, award, awardDateTime);
            ConsolerizerComposer.outSpace()
                    .green("Updated %d record(s)", update);
            transactionManager.rollback(status);
        } catch (DataAccessException e) {
            System.out.println("Error in creating record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return getAward();
    }

    /**
     * Creates an artist/award record in a failed fashion
     * The rollback will be purpously performed after the commit as been issued.
     * After the commit, it is evidently not possible to rollback.
     * The exception is cached and this returns the artist anyways.
     *
     * @param artist        The artist name
     * @param award         The award name
     * @param awardDateTime The date and time when it was awarded
     * @return The resulting {@link Award}
     */
    @Override
    public Award createFailRollback(String artist, String award, LocalDateTime awardDateTime) {
        final var defaultTransactionDefinition = new DefaultTransactionDefinition();
        final var status = transactionManager.getTransaction(defaultTransactionDefinition);
        try {
            final int update = jdbcTemplate.update("insert into AWARDS (ARTIST, AWARD, AWARD_DATE) values (?, ?, ?)", artist, award, awardDateTime);
            ConsolerizerComposer.outSpace()
                    .green("Updated %d record(s)", update);
            transactionManager.commit(status);
            transactionManager.rollback(status);
        } catch (final IllegalTransactionStateException exception) {
            ConsolerizerComposer.outSpace()
                    .red(title("Expected Exception"))
                    .red(exception);
        }
        return getAward();
    }

    /**
     * No transaction is required. However we will rely on the autocommit system of the {@link PlatformTransactionManager}.
     *
     * @param artist        The artist name
     * @param award         The award name
     * @param awardDateTime The date and time when it was awarded
     * @return The resulting {@link Award}
     */
    @Override
    public Award createNoTransaction(String artist, String award, LocalDateTime awardDateTime) {
        final int update = jdbcTemplate.update("insert into AWARDS (ARTIST, AWARD, AWARD_DATE) values (?, ?, ?)", artist, award, awardDateTime);
        ConsolerizerComposer.outSpace()
                .green("Updated %d record(s)", update);

        return getAward();
    }

    /**
     * Returns the current list of awards in the database
     *
     * @return {@link List<Award>} List of awards in the database
     */
    @Override
    public List<Award> listAwards() {
        return jdbcTemplate.query("select * from awards",
                (rs, rowNum) -> createMarble(rs));
    }

    /**
     * Gets the first found award record on the table.
     *
     * @return The resulting {@link Award}. In case of an empty result, it returns null.
     */
    @Nullable
    private Award getAward() {
        final var query = jdbcTemplate.query("select * from AWARDS",
                (rs, i) -> createMarble(rs));
        if (query.size() == 0) {
            return null;
        }
        return query.get(0);
    }

    private Award createMarble(ResultSet rs) throws SQLException {
        return Award
                .builder()
                .artist(rs.getString("ARTIST"))
                .award(rs.getString("AWARD"))
                .awardDate(rs.getTimestamp("AWARD_DATE").toLocalDateTime())
                .build();
    }
}
