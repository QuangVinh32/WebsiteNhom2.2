package Website2.config;
import Website2.service.Class.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE)
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/api/v1/product/create-product",
                        "/api/v1/product/update-product/{id}",
                        "/api/v1/product/delete-product/{id}",
                        "/api/v1/order/**",
                        "/api/v1/cart/**",
                        "/api/v1/categories/**",
                        "/api/v1/type/**")
                .hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.PUT,
                        "/api/v1/product/create-product",
                        "/api/v1/product/update-product/{id}",
                        "/api/v1/product/delete-product/{id}",
                        "/api/v1/order/**",
                        "/api/v1/cart/**",
                        "/api/v1/categories/**",
                        "/api/v1/type/**")
                .hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.POST,
                        "/api/register",
                        "/api/login")
                .permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/v1/cart/add/{productId}",
                        "/api/v1/cart/remove/{productId}")
                .permitAll() //
                .antMatchers(HttpMethod.GET,
                        "/pages/**",
                        "/api/v1/cart/summary",
                        "/auth/**",
                        "/api/v1/product/find-all-product",
                        "/api/v1/product/find-by-id/{id}",
                            "/api/v1/product/find-by-id/v1/{id}")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and().cors().and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3002","http://localhost:3001","http://localhost:3000")); // Chỉ cho phép nguồn từ localhost:3001
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")); // Cho phép các phương thức HTTP này
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // Chỉ cho phép các headers này
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
