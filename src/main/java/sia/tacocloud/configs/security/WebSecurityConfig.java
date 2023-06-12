package sia.tacocloud.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Profile("taco-dev")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .csrf()
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/authentication")
                .defaultSuccessUrl("/design").permitAll()
                .and()
                .oauth2Login()
                .loginPage("/authentication")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization")
                .and()
                .defaultSuccessUrl("/design")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedHeaders(List.of("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
