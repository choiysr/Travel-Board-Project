package org.kakarrot.config;

import lombok.extern.log4j.Log4j;
import org.kakarrot.security.RestAuthenticationEntryPoint;
import org.kakarrot.security.RestLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"org.kakarrot.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user0").password("{noop}1234").roles("MEMBER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().authorizeRequests()
                .antMatchers("/board/list", "/board/viewFile", "/security/**").permitAll()
                .antMatchers("/board/**").access("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')");
        http.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());
        http.formLogin().loginProcessingUrl("/login").loginPage("/security/login")
                .successHandler(new RestLoginSuccessHandler())
//                .defaultSuccessUrl("/security/loginSuccess", true)
                .usernameParameter("username").passwordParameter("password").permitAll();
        http.logout().logoutUrl("/security/logout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedOrigin("*");
//        configuration.setAllowedOrigins(Arrays.asList("https://localhost:8080","http://localhost:63343"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
