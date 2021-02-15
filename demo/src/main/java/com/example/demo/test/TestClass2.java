package com.example.demo.test;

public class TestClass2 {
	void Test() {
		TestClass tc = TestClass.builder().test("test").build();
		tc.getTest();
		
		User user = new User.builder()
				.name("hgbong")
				.age(29)
				.build();
		
		
	}
}
