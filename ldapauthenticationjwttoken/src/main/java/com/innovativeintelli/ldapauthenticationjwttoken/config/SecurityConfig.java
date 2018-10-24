package com.innovativeintelli.ldapauthenticationjwttoken.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import com.innovativeintelli.ldapauthenticationjwttoken.security.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        		.csrf()
	        			.disable()
	        		.cors()
	        			.disable()
	        		.exceptionHandling()
	        			.authenticationEntryPoint(unauthorizedHandler)
	        		.and()
	                .authorizeRequests()
	                    .antMatchers("/api/auth/**").permitAll()
	                    .anyRequest().authenticated();
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	//Good to use BcryptEncoder for spring 5.0
	        auth
	                .ldapAuthentication()
	                    .userDnPatterns("uid={0},ou=people")
	                    .groupSearchBase("ou=groups")
	                .contextSource(contextSource())
	                .passwordCompare()
	                    .passwordEncoder(new LdapShaPasswordEncoder())
	                    .passwordAttribute("userPassword");
	    }
	    
	    

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return  new DefaultSpringSecurityContextSource(
                Collections.singletonList("ldap://localhost:12345"), "dc=innovativeintelli,dc=com");
    }
    
    
}