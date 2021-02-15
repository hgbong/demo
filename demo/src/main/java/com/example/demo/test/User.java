package com.example.demo.test;

import lombok.Builder;

public class User {
	String name;
	int age;
	
	public static class builder {
		private String name;
		private int age;
		
		public builder() {
		
		}
		
		public builder name(String name) {
			this.name = name;
			return this;
		}
		public builder age(int age) {
			this.age = age;
			return this;
		}
		
		public User build() {
			User user = new User();
			user.name = this.name;
			user.age = this.age;
			
			return user;
		}
	}
}
