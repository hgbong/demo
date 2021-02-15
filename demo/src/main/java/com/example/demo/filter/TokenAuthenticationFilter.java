package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.UserService;
import com.example.demo.token.AuthenticationTokenProvider;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationTokenProvider authenticationTokenProvider;

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String token = authenticationTokenProvider.parseTokenString(request);
		
		if (authenticationTokenProvider.validateToken(token)) {
			Long userNo = authenticationTokenProvider.getTokenOwnerNo(token);
		
			try {
				
				User member = (User) userService.loadUserByUsername(userNo.toString());
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member, member.getPassword(), member.getAuthorities());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member, member.getPassword(), null);
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (UsernameNotFoundException e) {
//				throw new Exception("Not Found");
			}
			
		}
		
		filterChain.doFilter(request, response);

	}

}
