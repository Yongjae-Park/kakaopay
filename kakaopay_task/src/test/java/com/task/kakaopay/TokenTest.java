package com.task.kakaopay;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

import com.task.kakaopay.util.TokenUtil;

public class TokenTest {

	@Test
	public void tokentest() throws NoSuchAlgorithmException {
		final char[] digits = {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
		        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
		        'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
		        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 
		        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
		        'Y', 'Z', '#', '$'
		};
		int randomIndex = 0;
		char[] token = new char[3];
		
		for(int j=0; j<100;j++) {
			for(int i=0;i<3;i++) {
				randomIndex = ThreadLocalRandom.current().nextInt(64);
				token[i]=digits[randomIndex];
			}
			System.out.println(String.valueOf(token));
		}
	}
	
}
