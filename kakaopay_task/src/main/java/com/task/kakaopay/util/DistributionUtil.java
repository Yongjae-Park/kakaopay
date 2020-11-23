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
		int sum = 0;
		int initialMoney = splashedMoney;
		for(int i=0;i<personnel;i++) {
			money = ThreadLocalRandom.current().nextInt(splashedMoney+1);// money = 0~100,000
			moneyArr[i] = money;
			splashedMoney-=money;
			sum+=money;
			if(i==personnel-1 && sum<initialMoney) {
				//마지막 번째이면서 지금까지 할당된 money의 총합이 최초 금액보다 작은경우
				moneyArr[i]+=initialMoney-sum;
				break;
			}
		}
		return moneyArr;
	}
}
