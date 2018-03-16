package us.hyalen.trippmember.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import us.hyalen.trippmember.core.security.JwtAuthenticationFilter;
import us.hyalen.trippmember.core.security.JwtLoginFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Accounts
		auth.inMemoryAuthentication()
			.withUser("default").password("password").authorities("DEFAULT_USER")
			.and().withUser("super").password("password").authorities("SUPER_USER")
			.and().withUser("admin").password("password").authorities("ADMIN_USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/api/home").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(
				new JwtLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(
				new JwtAuthenticationFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}
}