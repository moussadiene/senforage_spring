package sn.senforage.sec;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration		// pour configurer
@EnableWebSecurity  //pour activer la securité web

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	// creer des utlisateurs
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("djine")
//			.password("$2a$10$.7OUQLOezTng2GoEC/QT/OjnA00xbTFXhWxcYpjWJr9wzFcSSN6dm")
//			.roles("USER");
//		
//		auth.inMemoryAuthentication().withUser("admin").password("passer").roles("ADMIN");
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select email as principal ,password as credentials, etat from users where email=?")
		.authoritiesByUsernameQuery("select user as principal, role as role from users_roles where user=?")
		.rolePrefix("ROLE_");
		
		
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
// 		regle et strategie de securite
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		//http.formLogin();
		http.authorizeRequests().antMatchers("/dashboad","/","/villages/index","/clients/index").hasAnyRole("USER","ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers("/villages/*","/clients/*","/roles/*","/users/*").hasRole("ADMIN");
//		
		http.exceptionHandling().accessDeniedPage("/403");
		//super.configure(http);
	}
}
