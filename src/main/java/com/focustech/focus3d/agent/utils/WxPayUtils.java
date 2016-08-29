package com.focustech.focus3d.agent.utils;

import java.math.BigDecimal;

import com.focustech.common.utils.MathUtils;
import com.focustech.common.utils.TCUtil;

/**
 *
 * *
 * @author lihaijun
 *
 */
public class WxPayUtils {
	/**
	 *
	 * *
	 * @param value
	 */
	public static String valueOfPrice(BigDecimal value){
		double round = MathUtils.round(value.doubleValue(), 2);
		String price = TCUtil.sv(round * 100);
		if(price.indexOf(".") != -1){
			price = price.substring(0, price.lastIndexOf("."));
		}
		return price;
	}

	public static void main(String[] arg){
		String valueOfPrice = WxPayUtils.valueOfPrice(new BigDecimal("3333.3333"));
		System.out.println(valueOfPrice);
	}
}
