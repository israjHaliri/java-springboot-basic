package com.mywork.springboot1;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by israj on 9/30/2016.
 */

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

    private  static final String SQL_LOGIN = "select username,password,active as enabled from s_users where username = ?";
    private  static final String SQL_PERMISSION = "select u.username, r.nama as authority from s_users u\n" +
            "join s_user_role ur on u.id = ur.id_user\n" +
            "join s_roles r on ur.id_role = r.id where u.username = ?";

    @Autowired private DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(ds).usersByUsernameQuery(SQL_LOGIN).authoritiesByUsernameQuery(SQL_PERMISSION);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/backend/**").hasAnyRole("ADMIN","STAFF")
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                .and().authorizeRequests()
                .antMatchers("/**").permitAll().anyRequest().permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/halo").and().logout().and()
                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }

}