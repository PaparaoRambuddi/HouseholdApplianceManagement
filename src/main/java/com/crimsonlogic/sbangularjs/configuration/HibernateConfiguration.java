package com.crimsonlogic.sbangularjs.configuration;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.crimsonlogic.sbangularjs.util.PropertiesConfig;


@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.crimsonlogic.sbangularjs.configuration" })
public class HibernateConfiguration {
 
    @Autowired
    PropertiesConfig config;
    
    @Autowired
    private Environment environment;
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.crimsonlogic.sbangularjs.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	/*@Autowired
    @Resource(name="/jdbc/bctsDSLocal")
    DataSource dataSource;*/
    
	@Bean
	public DataSource dataSource() {

		if (config.isProdDeploy()) {
			DataSource ds = null;
			Context ctx;

			try {
				
				ctx = new InitialContext();
				ds = (DataSource) ctx.lookup(config.SPRING_DATASOURCE_JNDI_NAME());

			} catch (NamingException e) {
				
			}
			return ds;
		} else {
			
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
			dataSource.setUrl(environment.getProperty("spring.datasource.url"));
			dataSource.setUsername(environment.getProperty("spring.datasource.username"));
			dataSource.setPassword(environment.getProperty("spring.datasource.password"));
			return dataSource;
		}

	}
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(PropertiesConfig.HIBERNATE_DIALECT, config.HIBERNATE_DIALECT());
        properties.put(PropertiesConfig.HIBERNATE_SHOW_SQL, config.HIBERNATE_SHOW_SQL());
        properties.put(PropertiesConfig.HIBERNATE_FORMAT_SQL, config.HIBERNATE_FORMAT_SQL());
        properties.put(PropertiesConfig.HIBERNATE_ALLOW_UPDATE_OUTSITE_TRANSACTION, config.HIBERNATE_ALLOW_UPDATE_OUTSITE_TRANSACTION());
        properties.put(PropertiesConfig.HIBERNATE_SEARCH_DEFAULT_DIRECTORY_PROVIDER, config.HIBERNATE_SEARCH_DEFAULT_DIRECTORY_PROVIDER());
        properties.put(PropertiesConfig.HIBERNATE_SEARCH_DEFAULT_INDEX_BASE, config.HIBERNATE_SEARCH_DEFAULT_INDEX_BASE());
        
        return properties;        
    }

    @Bean    
    public PlatformTransactionManager transactionManager() {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(sessionFactory().getObject());
       return txManager;
    }
}

