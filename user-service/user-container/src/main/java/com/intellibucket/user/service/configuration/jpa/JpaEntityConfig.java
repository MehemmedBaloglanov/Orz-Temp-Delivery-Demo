package com.intellibucket.user.service.configuration.jpa;

import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import com.intellibucket.user.service.repository.model.CustomerRegistrationEntity;
import com.intellibucket.user.service.repository.model.PhoneNumberEntity;
import com.intellibucket.user.service.repository.model.UserAddressEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class JpaEntityConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);

        em.setPackagesToScan(CompanyRegistrationEntity.class.getPackage().getName(),
                CustomerRegistrationEntity.class.getPackage().getName(),
                PhoneNumberEntity.class.getPackage().getName(),
                UserAddressEntity.class.getPackage().getName());

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        em.setJpaProperties(hibernateProperties());

        em.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);

        return em;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        properties.setProperty("hibernate.jdbc.batch_size", "50");

        properties.setProperty("hibernate.show_sql", "true");

        properties.setProperty("hibernate.format_sql", "true");

        properties.setProperty("hibernate.dialect.storage_engine", "innodb");

        return properties;
    }
}