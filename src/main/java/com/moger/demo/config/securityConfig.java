package com.moger.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {
	private final PasswordEncoder passEncoder;
	@Autowired
	public securityConfig(PasswordEncoder enc) {
		this.passEncoder=enc;
	}
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/","index","/css/*","/js/*").permitAll()
       .antMatchers("/**").hasAnyRole("USER", "ADMIN")
     //   .antMatchers("/admin/**").hasAnyRole("ADMIN")
     
	    .anyRequest()
	    .authenticated()
	    .and()
	    .httpBasic();
         
    
     
    }
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
    	//return super.userDetailsService();
    	UserDetails user0=User.builder().username("user0@semanticsquare.com").password(passEncoder.encode("test")).roles("USER")
    			.build();
    	return new InMemoryUserDetailsManager(user0);
    }

 
 
}