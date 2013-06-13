package com.qsoft.longdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public static int add(String input) {
		if (input.isEmpty()) {
			return 0;
		} else if (input.length() == 1) {
			return toInt(input);
		} else if (!input.startsWith("//")
				&& (input.contains("\n") || input.contains(","))) {
			return sum(input);
		} else {
			return sumWithDelim(input);
		}
	}

	private static int toInt(String input) {
		return Integer.parseInt(input);
	}

	private static int sumWithDelim(String input) {
		input = input.replace("//", "");
		while (input.contains("[")) {
			String delimContent = input.substring(input.indexOf("[") + 1,
					input.indexOf("]"));
			String delimDefine = input.substring(input.indexOf("["),
					input.indexOf("]") + 1);
			input = input.replaceAll(Pattern.quote(delimDefine), "");
			input = input.replaceAll(Pattern.quote(delimContent), ",");
		}

		return sum(input);
	}

	private static int sum(String input) {
		ArrayList<Integer> listInt = new ArrayList<Integer>();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(input);
		int sum = 0;
		while (m.find()) {
			int number = toInt(m.group());
			if (number < 0) {
				throw new NumberFormatException("negatives not allowed " + number);
			}
			if (number <= 1000)
				listInt.add(number);
		}
		for (Integer integer : listInt) {
			sum += integer;
		}
		return sum;
	}
}
