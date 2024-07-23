package Website2.config.security;

import Website2.config.exception.ErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http, UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http, ErrorHandler handler,
            JwtLoginFilter jwtLoginFilter, JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {
        return http
                .authorizeHttpRequests(registry -> registry
                        .antMatchers(HttpMethod.DELETE)
                        .hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/v1/product/create-product", "/api/v1/product/update-product/{id}","/api/v1/product/delete-product/{id}","/api/v1/order/**","/api/v1/cart/**","/api/v1/categorys/**","/api/v1/type/**","/api/v1/order/**")
                        .hasAnyAuthority("ADMIN", "MANAGER")
                        .antMatchers(HttpMethod.PUT, "/api/v1/product/create-product", "/api/v1/product/update-product/{id}","/api/v1/product/delete-product/{id}","/api/v1/order/**","/api/v1/cart/**","/api/v1/categorys/**","/api/v1/type/**","/api/v1/order/**")
                        .hasAnyAuthority("ADMIN", "MANAGER")
                        .antMatchers(HttpMethod.POST, "/api/v1/auth/register","/api/v1/product/find-all-product")
                        .permitAll()
                        .antMatchers(HttpMethod.GET, "/pages/**", "/auth/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(configurer -> configurer
                        .authenticationEntryPoint(handler)
                        .accessDeniedHandler(handler)
                )
                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://127.0.0.1:5500");
        configuration.setAllowedMethods(Arrays.asList("HEAD", "OPTIONS", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
