package com.crimsonlogic.sbangularjs.configuration;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DataSourceConfig {
	static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
	HibernateConfiguration configuration;

    @Bean
    public DataSourceInitializer dataSourceInitializer() throws MalformedURLException {
        
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        logger.info("dataSourceInitializer() start");
        databasePopulator.setIgnoreFailedDrops(true);
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(configuration.dataSource());
        initializer.setDatabasePopulator(databasePopulator);
        logger.info("dataSourceInitializer() end: {}",initializer);
        return initializer;
        
    }
  
	/*
	 * @Bean(name = "springBatchDataSource") public DataSource
	 * springBatchDataSource() {
	 * 
	 * EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new
	 * EmbeddedDatabaseBuilder(); return embeddedDatabaseBuilder.addScript(
	 * "classpath:org/springframework/batch/core/schema-drop-h2.sql")
	 * .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
	 * .setType(EmbeddedDatabaseType.H2) .build(); }
	 */
}
