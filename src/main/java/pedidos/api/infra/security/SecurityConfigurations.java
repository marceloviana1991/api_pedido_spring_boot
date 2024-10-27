package pedidos.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST,"/login", "/usuarios").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/usuarios", "/usuarios/{id}").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.PUT, "/usuarios").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/produtos", "/produtos/estoque").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.PUT, "/produtos").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.GET, "/produtos", "/produtos/{id}").hasRole("USER");
                    req.requestMatchers(HttpMethod.POST, "/pedidos").hasRole("USER");
                    req.requestMatchers(HttpMethod.GET, "/pedidos").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.GET, "/pedidos/{id}").hasRole("USER");
                    req.requestMatchers(HttpMethod.DELETE, "/pedidos").hasRole("ADMIN");
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
