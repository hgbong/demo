package com.example.demo.token;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class JwtAuthenticationToken implements AuthenticationToken{

	private String token;
}
