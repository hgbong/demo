package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filter.TokenAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	private static final String[] PUBLIC_URI = {
			"/some-public-apis"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        // ���� ���Ǽ��� ���� CSRF �����ؼ��� ��Ȱ��ȭ
        .csrf()
            .disable()
        // HTTP �⺻ ���� ��Ȱ��ȭ
        .httpBasic()
            .disable()
        // �� ��� ���� ��Ȱ��ȭ  
//        .formLogin()
//            .disable()
        // stateless�� ���� ��å ����  
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        // ���ҽ� �� ��� ���� ����  
        .authorizeRequests()      
            .antMatchers(PUBLIC_URI)
                .permitAll()
            .anyRequest()
                .authenticated()
            .and()
        // ���� ���� �߻� �� ó���� ���� �ڵ鷯 �߰�  
        .exceptionHandling()
            .authenticationEntryPoint(new RestAuthenticationEntryPoint());
		
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		TokenAuthenticationFilter TokenAuthenticationFilter = new TokenAuthenticationFilter();
		return TokenAuthenticationFilter;
	}
	
	
	
	
	
}
