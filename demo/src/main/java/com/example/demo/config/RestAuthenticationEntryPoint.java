package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
		private ObjectMapper objectMappger = new ObjectMapper();
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException, ServletException {
			
			System.out.println("authentication entry point 'commence' method run! ");
			
			
//		ErrorResponse er = null;
//		if(InsufficientAuthenticationException.class == authException.getClass()) {
//		er = new ErrorResponse(ErrorCode.UNAUTHORIZED, authException);
//		response.setStatus(HttpStatus.UNAUTHORIZED.value());
//		}
//		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//		response.getOutputStream().println(objectMappger.writeValueAsString(er));
		}
}
