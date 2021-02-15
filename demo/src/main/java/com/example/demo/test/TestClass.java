package com.example.demo.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestClass {
	@NonNull
	private String test;
	@NonNull
	private int age;

}
