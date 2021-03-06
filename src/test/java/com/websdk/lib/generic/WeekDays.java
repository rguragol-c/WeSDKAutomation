package com.websdk.lib.generic;

public enum WeekDays {
	// TODO Auto-generated constructor stub

		Sunday(1), Monday(2), Tuesday(3), Wednesday(4), Thrusday(5), Friday(6), Saturday(7);

		private int value;

		private WeekDays(int value) {
			this.value = value;
		}


		public static int getWeekDaysIndex(String month) {
			int weekIndex = 1;

			switch (month.toUpperCase())
			 {

			case "Sunday":
				weekIndex = 1;
				return 1;

			case "Monday":
				weekIndex = 2;
				return 2;

			case "Tuesday":
				weekIndex = 3;
				return 3;

			case "Wednesday":
				weekIndex = 4;
				return 4;

			case "Thrusday":
				weekIndex = 5;
				return 5;

			case "Friday":
				weekIndex = 6;
				return 6;

			case "Saturday":
				weekIndex = 7;
				return 7;

			
			}
			return weekIndex;

		}
		public static String getWeekDayValue(String index) {
			

			switch (index)
			 {

			case "Sun":
				return "Sunday";

			case "Mon":
				return "Monday";

			case "Tue":
				return "Tuesday";

			case "Wed":
				return "Wednesday";

			case "Thu":
				return "Thrusday";

			case "Fri":
				return "Friday";

			case "Sat":
				return "Saturday";

			}
			return null;

		}
}
