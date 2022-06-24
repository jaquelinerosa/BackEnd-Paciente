package br.com.treinamentospring.aula.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import br.com.treinamentospring.security.JwtSecurity;

@Configuration
@EnableWebSecurity
public class JwtConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.addFilterAfter(new JwtSecurity(), 
				UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/account").permitAll()
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				.antMatchers(HttpMethod.POST, "/api/paciente").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/paciente").permitAll()
				.antMatchers(HttpMethod.GET, "/api/paciente").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
				.anyRequest()
				.authenticated();
	}

	// configuração para liberar a documentação do SWAGGER
	private static final String[] SWAGGER = {
			"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui.html", "/webjars/**",
			"/v3/api-docs/**", "/swagger-ui/**"
	};

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(SWAGGER);
	}
}
