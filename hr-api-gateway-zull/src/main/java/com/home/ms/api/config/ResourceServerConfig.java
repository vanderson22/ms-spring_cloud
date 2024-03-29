package com.home.ms.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String OPERATOR_ROLE = "OPERATOR";

	private static final String ADMIN_ROLE = "ADMIN";

	@Autowired
	private JwtTokenStore tokenStore;

	/* hr-oauth/oauth/token */
	private static final String[] PUBLIC = { "/hr-oauth/**"  };
	private static final String[] OPERATOR = { "/hr-worker/**" };
	private static final String[] ADMIN = { "/hr-payroll/**" , "/hr-user/**" , "/actuator/**" , "/hr-worker/actuator/**"};

	
	/**
	 * 
	 * Permissão de acesso aos endpoints 
	 * */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
//		 http.authorizeRequests()
//	 	 .antMatchers("/**").permitAll();
 	 	 http.cors().configurationSource(corsConfigurationSource());
		 http.authorizeRequests()
		 	 .antMatchers(PUBLIC).permitAll()
		 	 .antMatchers(HttpMethod.GET , OPERATOR).hasAnyRole(OPERATOR_ROLE , ADMIN_ROLE)
		 	 .antMatchers(ADMIN).hasRole(ADMIN_ROLE)
		 	 .antMatchers(PUBLIC).hasAnyRole( OPERATOR_ROLE , ADMIN_ROLE)
		 	 .anyRequest()
		 	 .authenticated();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource()	{
	    CorsConfiguration corsConfiguration = new CorsConfiguration();
	    //qualquer origem
	    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
	    corsConfiguration.setAllowedMethods(Arrays.asList("*"));
	    corsConfiguration.setAllowCredentials(Boolean.TRUE);
	    corsConfiguration.setAllowedMethods(Arrays.asList("Authorization" , "Content-Type"));
	
	    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	    
	    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	
	  return urlBasedCorsConfigurationSource;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter()
	{
		   FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>( new CorsFilter(corsConfigurationSource()));
		   
		   bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		   
		   	return bean;
	}

}
