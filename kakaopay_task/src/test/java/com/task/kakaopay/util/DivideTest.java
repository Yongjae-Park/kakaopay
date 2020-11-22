package com.task.kakaopay.util;

import org.junit.jupiter.api.Test;

public class DivideTest {

	@Test
	public void devideUtilTest() {
		int splashedMoney = Integer.MAX_VALUE-1;
		int personnel = 1000;
		int[] intList = DistributionUtil.divideSplashedMoney(splashedMoney, personnel);
		
		for(int i:intList) {
			System.out.println("["+i+"]");
		}
	}
}
