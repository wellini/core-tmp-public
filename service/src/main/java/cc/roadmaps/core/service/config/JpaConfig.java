package cc.roadmaps.core.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    // TODO: For an unknown reason it doesn't work without manual configuration of the vendor adapter,
    //  so remove this config if you find a workaround ¯\_(ツ)_/¯
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setDataSource(dataSource);
        return emf;
    }
}
