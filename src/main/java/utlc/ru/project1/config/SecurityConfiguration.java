package utlc.ru.project1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import utlc.ru.project1.service.AdministratorService;

import static utlc.ru.project1.database.entity.Role.ADMIN;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AdministratorService administratorService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll()); // Permit all requests without authentication
        return http.build();

//        http
//                .csrf(AbstractHttpConfigurer::disable)  //todo remove the '.disable' option
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/images/**", "/css/**", "/js/**")
//                        .permitAll()  // Allow static resources
//
//                        .requestMatchers("/login",
//                                "/administrators/registration",
//                                "/v3/api-docs/**")
//                        .permitAll()
//
//                        .requestMatchers(HttpMethod.POST,
//                                "/administrators")
//                        .permitAll()
//
//                        .requestMatchers(
//                                RegexRequestMatcher.regexMatcher(
//                                        HttpMethod.POST,
//                                        "/administrators/\\d+/delete"))
//                        .hasAuthority(ADMIN.getAuthority())
//
//                        .requestMatchers(
//                                "/administrators/**",
//                                "/swagger-ui/**")
//                        .hasAuthority(ADMIN.getAuthority())
//
//                        .anyRequest().authenticated()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login")
//                        .deleteCookies("JSESSIONID"))
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/menu")
//                        .permitAll());
//        return http.build();
    }


}
