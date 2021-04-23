package com.turo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Configuration
@EnableTransactionManagement
public class HibernateConf {

  @Bean("serversEntityManager")
  public LocalSessionFactoryBean sessionFactory() {
    final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("com.turo.model");
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  @Bean
  public DataSource dataSource() {
    return new LocalRoutingDataSource();
  }

  @Bean(name = "serversTransactionManager")
  public PlatformTransactionManager hibernateTransactionManager() {
    final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }

  private final Properties hibernateProperties() {
    final Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
    hibernateProperties.setProperty("hibernate.show-sql", "true");
    hibernateProperties.setProperty("hibernate.format_sql", "true");
    hibernateProperties.setProperty("hibernate.use_sql_comments", "true");
    hibernateProperties.setProperty(
        "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
    hibernateProperties.setProperty("hibernate.connection.autocommit","true");
    // hibernateProperties.setProperty("hibernate.default_batch_fetch_size", "3");
    hibernateProperties.setProperty("hibernate.max_fetch_depth", "3");

    return hibernateProperties;
  }

  @Configuration
  class LocalRoutingDataSource extends AbstractRoutingDataSource {

    public DataSource dataSourceRead() {
      final BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/localRead?createDatabaseIfNotExist=true");
      dataSource.setUsername("root");
      dataSource.setPassword("relayrides");
      return dataSource;
    }

    public DataSource dataSourceWrite() {
      final BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/local1?createDatabaseIfNotExist=true");
      dataSource.setUsername("root");
      dataSource.setPassword("relayrides");
      dataSource.setDefaultAutoCommit(true);
      return dataSource;
    }

    public LocalRoutingDataSource() {
      super();
      final DataSource read = dataSourceRead();
      final DataSource write = dataSourceWrite();

      final Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
      dataSourceMap.put("READ_WRITE", write);
      dataSourceMap.put("READ_ONLY", read);

      this.setDefaultTargetDataSource(write);
      this.setTargetDataSources(dataSourceMap);
      
    }

    @Override
    protected Object determineCurrentLookupKey() {
      System.out.println("--->" + TransactionSynchronizationManager.isCurrentTransactionReadOnly()); 
      
      return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
          ? "READ_ONLY"
          : "READ_WRITE";
    }
  }
}
