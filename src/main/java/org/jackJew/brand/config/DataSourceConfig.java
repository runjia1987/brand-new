package org.jackJew.brand.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jack on 2017/3/18.
 */
@Configuration
@EnableJpaRepositories(value = {"org.jackJew.brand.repository"})
@EnableTransactionManagement
@Slf4j
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(@Value("${driverClassName}") String driverClassName,
                                 @Value("${url}") String url,
                                 @Value("${username}") String username,
                                 @Value("${password}") String password,
                                 @Value("${maxTotal}") int maxTotal) throws Exception {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setMaxTotal(maxTotal);
        return basicDataSource;
    }

    @Bean("entityManagerFactory")
    @Autowired
    public AbstractEntityManagerFactoryBean entityManagerFactory(DataSource ds,
                                                                 @Value("${entity.basePackages}") String[] basePackages,
                                                                 @Value("${hibernate.ejb.naming_strategy}") String strategy,
                                                                 @Value("${hibernate.jdbc.batch_size}") String jdbcBatchSize) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(ds);
        entityManagerFactory.setPackagesToScan(basePackages);
        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());

        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.ejb.naming_strategy", strategy);
        jpaProperties.put("hibernate.jdbc.batch_size", jdbcBatchSize);
        entityManagerFactory.setJpaPropertyMap(jpaProperties);

        return entityManagerFactory;
    }

    @Bean(name = "transactionManager")
    @Autowired
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public FilterRegistrationBean openEntityManagerInViewFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        openEntityManagerInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
        filterRegistrationBean.setFilter(openEntityManagerInViewFilter);
        return filterRegistrationBean;
    }
}
