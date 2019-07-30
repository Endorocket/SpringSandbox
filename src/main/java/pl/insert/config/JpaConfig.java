package pl.insert.config;

import org.hibernate.cfg.beanvalidation.BeanValidationIntegrator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.insert.services.UserService;

import javax.persistence.ValidationMode;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = {"pl.insert.dao", "pl.insert.services"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserService.class)})
public class JpaConfig {

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setPersistenceUnitName("unitName");
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.getJpaPropertyMap().put(BeanValidationIntegrator.MODE_PROPERTY, ValidationMode.NONE);
    return entityManagerFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
    transactionManager.setRollbackOnCommitFailure(true);
    return transactionManager;
  }
}
