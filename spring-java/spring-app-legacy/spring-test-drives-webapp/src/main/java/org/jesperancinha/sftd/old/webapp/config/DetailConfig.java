package org.jesperancinha.sftd.old.webapp.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by joao on 15-5-16.
 */

@Configuration
@PropertySource({"classpath:config.properties", "classpath:db.properties"})
@Profile("!test")
public class DetailConfig {

    @Bean(name = "dataSource")
    @Profile("!test")
    public DataSource dataSourcePSQL() {
        DataSource dataSource = createDataSource("test");
        DatabasePopulatorUtils.execute(createDatabasePopulator("create.sql"), dataSource);
        DatabasePopulatorUtils.execute(createDatabasePopulator("schema.sql"), dataSource);
        return dataSource;
    }

    @Bean(name = "dataSource")
    @Profile("test")
    public DataSource dataSourceH2() {
        DataSource dataSource = createTestDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator("create.sql"), dataSource);
        DatabasePopulatorUtils.execute(createDatabasePopulator("schema.sql"), dataSource);
        return dataSource;
    }

    private DataSource createTestDataSource() {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(org.postgresql.Driver.class);
        simpleDriverDataSource.setUrl("jdbc:h2:mem:test;LOCK_MODE=0;SCHEMA=TEST");
        simpleDriverDataSource.setUsername("sa");
        simpleDriverDataSource.setPassword("sa");
        return simpleDriverDataSource;
    }


    private DatabasePopulator createDatabasePopulator(String script) {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource(script));
        return databasePopulator;
    }

    public SimpleDriverDataSource createDataSource(String database) {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(org.postgresql.Driver.class);
        simpleDriverDataSource.setUrl("jdbc:postgresql://localhost" + database);
        simpleDriverDataSource.setUsername("postgres");
        simpleDriverDataSource.setPassword("postgres");
        return simpleDriverDataSource;
    }

    @Value("${hibernate.cache.use_second_level_cache:true}")
    private String useSecondLevelCache;


    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.jesperancinha.sftd.old.webapp.model");
        factory.setDataSource(dataSource);
        final Properties connectionProperties = new Properties();
        connectionProperties.setProperty("hibernate.connection.autocommit", "true");
        connectionProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        connectionProperties.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");
        connectionProperties.setProperty("hibernate.cache.use_structured_entries", "true");
        connectionProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        connectionProperties.setProperty("hibernate.cache.use_second_level_cache", this.useSecondLevelCache);
        factory.setJpaProperties(connectionProperties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory(dataSource));
        return txManager;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
