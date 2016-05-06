package com.vincent.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.vincent")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	MySimpleUrlAuthenticationSuccessHandler customSuccessHandler;

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    
	@Autowired
	DataSource dataSource;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
     
	/*	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("vincent").password("Yukirin0715").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.antMatchers("/", "/home/**").access("isAuthenticated()")
		.antMatchers("/", "/home/**").access("isAuthenticated())")
		.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.and()
		.formLogin()
		.loginPage("/login")
		.successHandler(customSuccessHandler)
		.usernameParameter("username").passwordParameter("password")
		.and().logout().deleteCookies("JSESSIONID")
		.and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60)
		.and().csrf()
		.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();

		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}

}