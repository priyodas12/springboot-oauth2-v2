package io.oauth2.learn.auth;


import io.oauth2.learn.service.ProductManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ProductManagerService productManagerService;
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().build();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //configuring role based authorization,
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("Configuring authorization...");

        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/api/**")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated().and().httpBasic();

    }

    //configuring authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        log.info("Configuring authentication...");

        auth.authenticationProvider(authProvider());

    }


    //Password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }


    //authentication details provider through UserDetailsService
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
