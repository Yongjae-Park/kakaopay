package com.task.kakaopay.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DistributionUtil {
	public static int[] divideSplashedMoney(int splashedMoney, int personnel){
		//splashedMoney = 100,000
		//personnel = 20
		int money = 0;
		int[] moneyArr = new int[personnel];
		for(int i=0;i<personnel;i++) {
			money = ThreadLocalRandom.current().nextInt(splashedMoney+1);// money = 0~100,000
			moneyArr[i] = money;
			splashedMoney-=money;
		}
		Arrays.sort(moneyArr);
		return moneyArr;
	}
}
