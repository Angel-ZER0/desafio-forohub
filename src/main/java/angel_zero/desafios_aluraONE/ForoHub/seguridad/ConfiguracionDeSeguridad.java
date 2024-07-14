package angel_zero.desafios_aluraONE.ForoHub.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfiguracionDeSeguridad {

	@Autowired
	 private FiltroDeSeguridad filtro;
	 
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    	
	        return httpSecurity
	                .csrf(csrf -> csrf.disable())
	                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
	                        .anyRequest().authenticated())
	                		.addFilterBefore(filtro, UsernamePasswordAuthenticationFilter.class)
	                .build();
	        
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
	            throws Exception {
	    	
	        return authenticationConfiguration.getAuthenticationManager();
	        
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	
	        return new BCryptPasswordEncoder();
	        
	    }
	
}
