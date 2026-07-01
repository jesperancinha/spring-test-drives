package org.jesperancinha.sftd.mastery3.plants.dao;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.mastery3.plants.model.Plant;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlantDao {

    private final JdbcTemplate jdbcTemplate;

    private final PlatformTransactionManager transactionManager;

    public PlantDao(JdbcTemplate jdbcTemplate, PlatformTransactionManager transactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionManager = transactionManager;
    }

    @PostFilter("filterObject.owner == authentication.name")
    public List<Plant> getExamplePlants() {
        final var plants = new ArrayList<Plant>();
        plants.add(Plant.builder()
                .name("Yucca")
                .owner("Joao")
                .scientificName("Yucca filamentosa")
                .build());
        plants.add(Plant
                .builder()
                .name("Sansevieria")
                .owner("Sabino")
                .scientificName("Sansevieria")
                .build());
        return plants;
    }

    public Plant createPlant(final Plant plant) {
        final var defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setName("A plant transaction");
        final var transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);
        jdbcTemplate.execute("drop table if exists plant");
        jdbcTemplate.execute("create table if not exists plant(" +
                "id int not null auto_increment," +
                "name VARCHAR(255)," +
                "scientificName VARCHAR(255)" +
                ")");
        try {
            final int update = jdbcTemplate.update("insert into plant(id, name, scientificName) values ( ? , ? , ? )", 1, plant.getName(), plant.getScientificName());
            ConsolerizerComposer.outSpace()
                    .green("Updated %d record(s)", update);
            transactionManager.rollback(transactionStatus);
            ConsolerizerComposer.outSpace()
                    .green("Transaction is rolled back!");
        } catch (DataAccessException e) {
            ConsolerizerColor.RED.printThrowableAndExit(e);
            throw e;
        }
        final List<Plant> query = jdbcTemplate.query("select * from plant", new RowMapper<Plant>() {
            @Override
            public Plant mapRow(ResultSet resultSet, int i) throws SQLException {
                return Plant
                        .builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .scientificName(resultSet.getString("scientificName"))
                        .build();
            }
        });
        return query.size() == 0 ? null : query.get(0);
    }
}
