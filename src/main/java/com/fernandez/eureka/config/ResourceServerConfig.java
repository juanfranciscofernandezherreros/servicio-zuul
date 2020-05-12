package com.fernandez.eureka.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
				.antMatchers(HttpMethod.GET , "/api/languages/{id}*", "/api/languages/v1/**").permitAll()
				.antMatchers(HttpMethod.GET , "/api/comentarios/{id}*", "/api/comentarios/v1/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/comentarios/v1/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/blogs/v1/translate/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/comentarios/v1/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/blogs/v1").permitAll()
				.antMatchers(HttpMethod.POST, "/api/newsletter/v1").permitAll()
				.antMatchers(HttpMethod.POST, "/api/newsletter/v1/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/v1/{id}/articles").permitAll()
				.antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/newsletter/v1").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/newsletter/v1/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/blogs/v1/name").permitAll()
				.antMatchers(HttpMethod.POST, "/api/images/v1").permitAll()
				.antMatchers(HttpMethod.DELETE,"/api/blogs/v1/**").permitAll()
				.antMatchers(HttpMethod.PUT,"/api/blogs/v1/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categorias/v1/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categorias/v1/{id}").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/v1/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/usuarios/**").permitAll()
				.antMatchers(HttpMethod.POST,"/api/usuarios/v1/users").permitAll()
				.antMatchers(HttpMethod.POST,"/api/usuarios/v1/usuarios").permitAll()
				.antMatchers(HttpMethod.GET, "/api/internationalization/v1/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/blogs/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/distritos","/api/barrios","/api/usuarios/usuarios").permitAll()
				.antMatchers(HttpMethod.GET, "/api/distritos/{id}", "/api/barrios/{id}","/api/usuarios/usuarios/{id}").permitAll()
				.antMatchers(HttpMethod.PUT,"/api/distritos/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST,"/api/v1/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and().cors().configurationSource(corsConfigurationSource());
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("algun_codigo_secreto");
		return tokenConverter;
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
