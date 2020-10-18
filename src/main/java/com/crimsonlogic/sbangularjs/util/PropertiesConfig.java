package com.crimsonlogic.sbangularjs.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(
          value={"classpath:application.properties", 
        		  "classpath:hibernate.properties"},
          ignoreResourceNotFound = true)
//@Scope(value=WebApplicationContext.SCOPE_APPLICATION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PropertiesConfig{
	static final Logger LOG = LoggerFactory.getLogger(PropertiesConfig.class);
	@Autowired
	Environment environment;
	
	public static String SPRING_DATASOURCE_JNDI_NAME = "spring.datasource.jndi-name";
	public static String SPRING_BATCH_INITIALIZE_SCHEMA = "spring.batch.initialize-schema";
	public static String SCHEDULER_SCAN_INTERVAL = "scheduler.scan.interval";
	public static String SCHEDULER_INITIAL_DELAY = "scheduler.initial.delay";
	public static String PROD_DEPLOY = "prod.deploy";
	// classpath:hibernate.properties
	public static String HIBERNATE_DIALECT = "hibernate.dialect";
	public static String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	public static String HIBERNATE_ALLOW_UPDATE_OUTSITE_TRANSACTION = "hibernate.allow_update_outside_transaction";
	public static String HIBERNATE_SEARCH_DEFAULT_DIRECTORY_PROVIDER = "hibernate.search.default.directory_provider";
	public static String HIBERNATE_SEARCH_DEFAULT_INDEX_BASE = "hibernate.search.default.indexBase";

	public String SPRING_DATASOURCE_JNDI_NAME() {
		return environment.getRequiredProperty(SPRING_DATASOURCE_JNDI_NAME);
	}	
	public String SPRING_BATCH_INITIALIZE_SCHEMA() {
		return environment.getRequiredProperty(SPRING_BATCH_INITIALIZE_SCHEMA);
	}
	public String SCHEDULER_SCAN_INTERVAL() {
		return environment.getProperty(SCHEDULER_SCAN_INTERVAL);
	}
	public String SCHEDULER_INITIAL_DELAY() {
		return environment.getProperty(SCHEDULER_INITIAL_DELAY);
	}
	
	public String HIBERNATE_DIALECT() {
		return environment.getRequiredProperty(HIBERNATE_DIALECT);
	}
	public String HIBERNATE_SHOW_SQL() {
		return environment.getRequiredProperty(HIBERNATE_SHOW_SQL);
	}
	public String HIBERNATE_FORMAT_SQL() {
		return environment.getRequiredProperty(HIBERNATE_FORMAT_SQL);
	}
	public String HIBERNATE_ALLOW_UPDATE_OUTSITE_TRANSACTION() {
		return environment.getRequiredProperty(HIBERNATE_ALLOW_UPDATE_OUTSITE_TRANSACTION);
	}
	public String HIBERNATE_SEARCH_DEFAULT_DIRECTORY_PROVIDER() {
		return environment.getRequiredProperty(HIBERNATE_SEARCH_DEFAULT_DIRECTORY_PROVIDER);
	}
	public String HIBERNATE_SEARCH_DEFAULT_INDEX_BASE() {
		return environment.getRequiredProperty(HIBERNATE_SEARCH_DEFAULT_INDEX_BASE);
	}
	
	public boolean isProdDeploy() {
		return Boolean.parseBoolean(environment.getProperty(PROD_DEPLOY));
	}
}
