package com.ecommerce;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hasher {
	public static void main(String[] args) {
		String[] passwords = {
			    "password123",
			    "password456",
			    "password789",
			    "password101",
			    "password202",
			    "password303",
			    "password404",
			    "password505",
			    "password606",
			    "password707",
			    "password808",
			    "password909",
			    "password010",
			    "password111",
			    "password121"
			};

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		int i = 1;
		for (String password : passwords) {
			System.out.print(i++ + ": ");
			System.out.println(encoder.encode(password));
		}
	}
}
