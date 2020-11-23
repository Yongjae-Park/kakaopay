package com.task.kakaopay.util;

import java.util.concurrent.ThreadLocalRandom;

public class TokenUtil {

	public static String generateToken() {
		
		final char[] digits = {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
		        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
		        'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
		        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 
		        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
		        'Y', 'Z', '#', '$'
		};//64진수 -> 3자리 = 2^18의 확률로 겹침 1/262144
		
//		int shift = 6;
//	    char[] buf = new char[64];
//	    int charPos = 64;
//	    int radix = 1 << shift;
//	    long mask = radix - 1;
//	    long number = v;
//	    do {
//	        buf[--charPos] = digits[(int) (number & mask)];//buf 끝에서부터 채워넣음 number가 가장 클때 buf에 들어갈 digit랜덤의 경우가 가장 큼
//	        number >>>= shift;//2^6만큼 나누기
//	    } while (number != 0);
	    
	    
	    
//	    return new String(buf, charPos, (64 - charPos));
		int randomIndex = 0;
		char[] token = new char[3];
		
		for(int j=0; j<100;j++) {
			for(int i=0;i<3;i++) {
				randomIndex = ThreadLocalRandom.current().nextInt(64);
				token[i]=digits[randomIndex];
			}
//			System.out.println(String.valueOf(token));
		}
	    return String.valueOf(token);
	}
}
