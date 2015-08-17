package com.websdk.lib.generic;

public enum Months {

	// TODO Auto-generated constructor stub

	JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6), JULY(7), AUGUST(
			8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12);

	private int value;

	private Months(int value) {
		this.value = value;
	}

	public int getMonth() {
		return value;
	}

	public static int getMonthIndex(String month) {
		int monthIndex = 1;

		switch (month.toUpperCase())
		 {

		case "JANUARY":
			monthIndex = 1;
			return 1;

		case "FEBRUARY":
			monthIndex = 2;
			return 2;

		case "MARCH":
			monthIndex = 3;
			return 3;

		case "APRIL":
			monthIndex = 4;
			return 4;

		case "MAY":
			monthIndex = 5;
			return 5;

		case "JUNE":
			monthIndex = 6;
			return 6;

		case "JULY":
			monthIndex = 7;
			return 7;

		case "AUGUST":
			monthIndex = 8;
			return 8;

		case "SEPTEMBER":
			monthIndex = 9;
			return 9;

		case "OCTOBER":
			monthIndex = 10;
			return 10;

		case "NOVEMBER":
			monthIndex = 11;
			return 11;

		case "DECEMBER":
			monthIndex = 12;
			return 12;
		}
		return monthIndex;

	}
}
