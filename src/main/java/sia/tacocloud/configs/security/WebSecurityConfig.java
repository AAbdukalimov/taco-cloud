//package sia.tacocloud.configs.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests()
//                .antMatchers("/design", "/orders").hasRole("USER")
//                .antMatchers("/", "/**").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/design")
//                .and()
//                .oauth2Login()
//                .loginPage("/oauth2/authorization/facebook")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .csrf()
//                .and()
//                .build();
//    }
//
//}
