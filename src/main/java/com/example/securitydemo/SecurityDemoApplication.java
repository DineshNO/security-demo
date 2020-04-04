package com.example.securitydemo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@SpringBootApplication
public class SecurityDemoApplication {

    //	@Bean
//	UserDetailsManager userDetailsManager(){
//		return new InMemoryUserDetailsManager();
//	}

    @Bean
    UserDetailsManager jdbcUserDetailsManager(DataSource ds) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(ds);
        return jdbcUserDetailsManager;
    }

    @Bean
    InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
        return () -> {
            UserDetails dinesh = User.withDefaultPasswordEncoder().username("dinesh").password("dinesh").roles("USER", "ADMIN").build();
            userDetailsManager.createUser(dinesh);
            UserDetails suresh = User.withUserDetails(dinesh).username("Suresh").build();
            userDetailsManager.createUser(suresh);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }

}
