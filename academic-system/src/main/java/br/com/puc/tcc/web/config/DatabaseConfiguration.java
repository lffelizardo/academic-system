package br.com.puc.tcc.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@EnableJpaRepositories("br.com.puc.tcc.web.repository")
@EnableTransactionManagement
@EntityScan("br.com.puc.tcc.web.domain")
public class DatabaseConfiguration {

	@Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

}
