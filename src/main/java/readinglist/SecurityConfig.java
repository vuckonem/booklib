package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ReaderRepository readerRepository;
	
	// specifies that requests for “/” (which ReadingListController’s methods are
	// mapped to) require an authenticated user with the READER role
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/")
		.access("hasRole('READER')")
		.antMatchers("/**").permitAll()
		.and()
		.formLogin()
		//It also designates /login as the path for the login page as well as the login failure page
		.loginPage("/login")
		.failureUrl("/login?error=true");
	}

	// We’re going to authenticate users against the database via JPA by setting a custom user details service
	// This service can be any class that implements UsersDetailsService and is used to look up user details given a username
	@Override
	protected void configure( AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(new UserDetailsService() {
		
		@Override
		public UserDetails loadUserByUsername(String username)
		throws UsernameNotFoundException {
			
			return (UserDetails) readerRepository.findOne(username);
		}
		});
	}
}