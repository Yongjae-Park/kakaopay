package com.task.kakaopay.util;

import java.util.ArrayList;
import java.util.List;

public class DistributionUtil {
	public static List<Integer> divideSplashedMoney(int splashedMoney, int personnel){
		
		List<Integer> returnList = new ArrayList<>();
		
		for(int i=0;i<personnel;i++) {
			returnList.add(i, splashedMoney/personnel);
		}
		return returnList;
	}
}
