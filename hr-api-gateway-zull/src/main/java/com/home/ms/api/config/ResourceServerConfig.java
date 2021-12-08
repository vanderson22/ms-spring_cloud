package com.home.ms.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;

	/* hr-oauth/oauth/token */
	private static final String[] PUBLIC = { "/hr-oauth/oauth/token" , "/actuator/**" };
	private static final String[] OPERATOR = { "/hr-worker/**" };
	private static final String[] ADMIN = { "/hr-payroll/**" , "/hr-user/**" };

	
	/**
	 * 
	 * Permissão de acesso aos endpoints 
	 * */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		 http.authorizeRequests()
	 	 .antMatchers(PUBLIC).permitAll();
	 	 
		 http.authorizeRequests()
//		 	 .antMatchers(PUBLIC).permitAll()
		 	 .antMatchers(HttpMethod.GET , OPERATOR).hasAnyRole("OPERATOR" , "ADMIN")
		 	 .antMatchers(ADMIN).hasRole("ADMIN")
		 	 .anyRequest()
		 	 .authenticated();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

}
