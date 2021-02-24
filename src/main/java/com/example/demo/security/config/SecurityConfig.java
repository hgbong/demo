package com.example.demo.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/**기본내용,,
		 * 만약 이 SecurityConfig 클래스 자체를 생성하지 않으면 => 스프링 시큐리티 기본 login 페이지 적용
		 * (WebSecurityConfigurerAdapter 에 보면, http.formLogin() 이랑 모든 request 인증하라는 메서드가 있음
		 * 즉, 기본 스프링 시큐리티 동작 == 모든 요청을 인증으로 보냄 => formLogin 때문에 /login 로 이동함
		 * 
		 * 근데 만약 이 클래스 메서드를 override 하고 구현 안하면 (즉 super(http) 도 호출안하면)
		 * 위의 Adapter 에 적용된 내용 - http.formLogin() , 모든 url 인증 처리   이 두개가 다 적용 안되는거임
		 * 그러니까 "override 만 하면 인증없이 다 들어가지는 거임!!!"
		 * 
		 * 마지막으로, 아래처럼 직접 구현 (특히 formLogin() 사용하고 최소한 특정 url 들에 authenticated() 적용하는 것) 해야 스프링 시큐리티를 설정할 수 있는 거임
		 * 
		 * ----참고내용-----
		 * http.formLogin() == 아예 안쓰는거랑 효과는 완전 똑같음. 즉 안썼는데 (또는 disable) authorizeRequests 로 특정 url 들에 인증을 걸어두면
		 * ===> 403 Access Denied  에러만 던져주고 끝임
		 * 	(반복얘기지만, 이걸 formLogin() 활성화해서 login form 으로 리다이렉트 시켜줘야함^^;) 
		 */
		
		http
			.csrf()
				.disable();
				// formLogin() 없으면 아예 form 안쓰겠다는 말. /admin 이동하면 => 403 access denied 에러 뱉고 끝;; 로그인 시켜주는 게 예의니까 로그인 페이지로 이동해야
				// 근데 그러려면 formLogin() 필요함.
				// **진짜중요한거 : formLogin().loginPage("/login") vs formLogin() 이거는 다름;; 
				// 아마 내부적으로 formLogin() 만 쓰면 바로 /login 으로 이동하는데, 명시적으로 loginPage("/login") 쓰면.. 저 아래 설정에서 permitAll() 이 적용돼있어야 함!!!!
					// 즉,, loginPage("/login") 쓰면 명시적으로 저 아래 필터에 거쳐지는?
				
				// 그리고 loginProcessingUrl 헷갈림.. 아마 form 을 통해 인증할 때 client 에서 요청하는 url 같은데..
					// ex. 서버에서 "/loginProc" 라고 설정하면, 클라도 form (name,pw 를 담은) action 을 /loginProc 로 맞춰 주어야 여기서 인증이 되는듯
						// 아마 이거랑.. UPAF 까지 관계가 있을 것도??
//			.formLogin().disable()
//				.loginProcessingUrl("/home")
//				.loginPage("/login")
//			.and()
//			.authorizeRequests()
//				.antMatchers("/logintest").permitAll()
//				.anyRequest().authenticated();
			
//				.loginPage("/logintest")
//			.and()
//			.authorizeRequests()
//				.antMatchers("/", "/home", "/logintest").permitAll()
//				.antMatchers("/admin").hasRole("ADMIN")
//				.anyRequest().authenticated();
		
		
	}
}
