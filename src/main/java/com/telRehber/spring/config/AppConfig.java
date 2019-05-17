package com.telRehber.spring.config;

import com.telRehber.model.tblUnvan;
import com.telRehber.model.tblBirim;
import com.telRehber.model.tblLogin;
import com.telRehber.model.tblPersonel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;
 import static org.hibernate.cfg.AvailableSettings.C3P0_CONFIG_PREFIX;

@PropertySource(value= "classpath:hibernate.properties", encoding = "UTF-8")
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = {"com.telRehber"})


public class AppConfig {
    @Autowired
    private Environment environment;
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Properties props = new Properties();

        props.put(DRIVER, environment.getProperty("mssql.driver"));
        props.put(URL, environment.getProperty("mssql.url"));
        props.put(USER, environment.getProperty("mssql.user"));
        props.put(PASS, environment.getProperty("mssql.password"));
        props.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
        props.put(DIALECT, environment.getProperty("hibernate.dialect"));
        props.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
        props.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
        props.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.acquire_increment"));
        props.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
        props.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));
        props.put(C3P0_CONFIG_PREFIX + ".initialPoolSize", environment.getProperty("hibernate.c3p0.initialPoolSize"));
        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(tblUnvan.class, tblBirim.class, tblPersonel.class, tblLogin.class);
        return factoryBean;
    }
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}