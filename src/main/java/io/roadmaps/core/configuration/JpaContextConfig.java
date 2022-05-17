package io.roadmaps.core.configuration;

import io.roadmaps.core.configuration.properties.JpaNativeProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.validation.Valid;

@Configuration
public class JpaContextConfig {

    // Если не переопределить этот бин, ничего не работает ¯\_(ツ)_/¯
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory(@Valid JpaNativeProperties jpaNativeProperties){
        LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localEntityManagerFactoryBean.setJpaProperties(jpaNativeProperties.getProperties());
        return localEntityManagerFactoryBean;
    }

}
