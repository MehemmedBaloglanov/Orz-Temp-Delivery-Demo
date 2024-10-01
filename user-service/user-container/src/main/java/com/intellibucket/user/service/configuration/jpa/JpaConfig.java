package com.intellibucket.user.service.configuration.jpa;

import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import com.intellibucket.user.service.repository.model.CustomerRegistrationEntity;
import com.intellibucket.user.service.repository.model.PhoneNumberEntity;
import com.intellibucket.user.service.repository.model.UserAddressEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class JpaConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);

        // Use Product class to determine the package to scan
        em.setPackagesToScan(CompanyRegistrationEntity.class.getPackage().getName(),
                CustomerRegistrationEntity.class.getPackage().getName(),
                PhoneNumberEntity.class.getPackage().getName(),
                UserAddressEntity.class.getPackage().getName());

        // Set the JPA vendor (Hibernate)
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Set Hibernate properties for MySQL
        em.setJpaProperties(hibernateProperties());

        // Set the correct EntityManagerFactory interface
        em.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);

        return em;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // Hibernate dialect for MySQL
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        // Hibernate DDL policy: update the schema automatically
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        // Hibernate JDBC batch size for performance
        properties.setProperty("hibernate.jdbc.batch_size", "50");

        // Show SQL in the console (optional)
        properties.setProperty("hibernate.show_sql", "true");

        // Format SQL in the console (optional)
        properties.setProperty("hibernate.format_sql", "true");

        // Use MySQL InnoDB engine (optional, ensures foreign keys, etc.)
        properties.setProperty("hibernate.dialect.storage_engine", "innodb");

        return properties;
    }
}