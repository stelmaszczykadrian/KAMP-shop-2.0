package com.codecool.shop.config;
import com.codecool.shop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Controller
@EnableWebSecurity
public class SecurityConfiguration{
    @Autowired
    DataSource dataSource;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled"
                        +" from users"
                        +" where email = ?")
                .authoritiesByUsernameQuery("select email, authority from users where email = ?")
                .passwordEncoder(new SCryptPasswordEncoder(16384, 8, 4, 32, 64));



    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManagerBuilder auth) throws Exception {

        http
                .authorizeRequests()
                    .requestMatchers("/home").permitAll()
                    .requestMatchers("/api/carts").hasRole("ADMIN")
                    .requestMatchers("/register").permitAll()
                    .anyRequest().hasRole("USER")
                    .and()
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//
//
//
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("a")
//                        .password("a")
//                        .roles("USER")
//                        .build();
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("password")
//                        .roles("ADMIN")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}

