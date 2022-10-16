package com.syed.osama.hassan.springsecurity.security;

import com.syed.osama.hassan.springsecurity.auth.service.ApplicationUserService;
import com.syed.osama.hassan.springsecurity.jwt.JwtAuthenticationFilter;
import com.syed.osama.hassan.springsecurity.jwt.JwtConfig;
import com.syed.osama.hassan.springsecurity.jwt.JwtTokenVerifier;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.crypto.SecretKey;
import java.util.concurrent.TimeUnit;

import static com.syed.osama.hassan.springsecurity.security.model.Role.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(null), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .anyRequest()
                .authenticated();
//                .and()
//                .formLogin()
//                    .loginPage("/login").permitAll()
//                    .defaultSuccessUrl("/courses", true)
//                    .usernameParameter("username")
//                .and()
//                .rememberMe() // Default to 2 weeks
//                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                    .key("securedKey")
//                    .rememberMeParameter("remember-me")
//                .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                    .clearAuthentication(true)
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID", "remember-me")
//                    .logoutSuccessUrl("/login");


        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
