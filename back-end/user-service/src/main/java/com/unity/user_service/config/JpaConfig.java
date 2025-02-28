package com.unity.user_service.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "com.unity.user_service.repository") // Adjust package
public class JpaConfig {

    // @Bean
    // public JpaVendorAdapter jpaVendorAdapter() {
    //     HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    //     adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect"); // MySQL dialect
    //     adapter.setShowSql(true);          // Log SQL statements
    //     adapter.setGenerateDdl(true);      // Auto-create/update tables
    //     adapter.setPrepareConnection(true); // Optimize DB connection
    //     return adapter;
    // }

    // @Bean
    // public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    //         DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
    //     LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    //     em.setDataSource(dataSource);
    //     em.setPackagesToScan("com.unity.user_service.entity"); // Where your entities are (e.g., User)
    //     em.setJpaVendorAdapter(jpaVendorAdapter);
    //     return em;
    // }
}