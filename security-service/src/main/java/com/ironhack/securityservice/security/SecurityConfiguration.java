package com.ironhack.securityservice.security;

import com.ironhack.securityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security configuration
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * Password encoder
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * User service
     */
    @Autowired
    private UserService userService;

    /**
     * Password encoder
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure
     * @param auth AuthenticationManagerBuilder auth
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * Configure
     * @param httpSecurity HttpSecurity httpSecurity
     * @throws Exception Exception
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests()
                // --- USER SERVICE ---
                .mvcMatchers(HttpMethod.GET, "/users/basics").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/users/basics/{id}").hasAnyAuthority("ADMIN", "BASIC")
                .mvcMatchers(HttpMethod.PATCH,"/users/basics/update-room/{id}/{roomId}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/users/basics/update-balance/{id}/{balance}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/users/basics/update-stays/{id}/{numberOfStays}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/users/basics/{id}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/users/premiums").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/users/premiums/{id}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/users/premiums/update-room/{id}/{roomId}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/users/premiums/update-balance/{id}/{balance}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/users/premiums/update-stays/{id}/{numberOfStays}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/users/premiums/{id}").hasAnyAuthority("ADMIN")

                // --- ROOM SERVICE ---
                .mvcMatchers(HttpMethod.GET, "/regular-rooms").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.GET, "/regular-rooms/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.PATCH, "/update-regular-rooms/{id}/{availability}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.POST, "/regular-rooms").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/suites").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.GET, "/suites/{id}").hasAnyAuthority("ADMIN","BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.POST, "/suites").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/update-suites/{id}/{availability}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")

                // --- RESERVATION SERVICE ---
                .mvcMatchers(HttpMethod.GET, "/reservations/rooms").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/reservations/rooms/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.POST, "/reservations/rooms").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.DELETE, "/reservations/rooms").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.GET, "/reservations/user-matches-room/{userId}/{roomId}").hasAnyAuthority("ADMIN")

                // --- INVOICE SERVICE ---
                .mvcMatchers(HttpMethod.GET, "/invoices").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/invoices-final/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.POST, "/invoices-activity").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")

                // --- ACTIVITY SERVICE ---
                .mvcMatchers(HttpMethod.GET, "/activities/massages").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/activities/massages/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.POST, "/activities/massages").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.PATCH, "/activities/massages/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.DELETE, "/activities/massages/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")

                .mvcMatchers(HttpMethod.GET, "/activities/room-food-services").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/activities/room-food-services/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.POST, "/activities/room-food-services").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.PATCH, "/activities/room-food-services/deliver/{id}").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/activities/room-food-services/update-food").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.PATCH, "/activities/room-food-services/update-drink").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.DELETE, "/activities/room-food-services/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")

                .mvcMatchers(HttpMethod.GET, "/activities/pool-rents").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/activities/pool-rents/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.POST, "/activities/pool-rents").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.PATCH, "/activities/pool-rents/update-floaties").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.PATCH, "/activities/pool-rents/update-towels").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .mvcMatchers(HttpMethod.DELETE, "/activities/pool-rents/{id}").hasAnyAuthority("ADMIN", "BASIC", "PREMIUM")
                .anyRequest().permitAll();

        //"/users/basics" ->post
        //"/users/premiums" -> post
    }

}
