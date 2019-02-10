package com.rest.automateapi.steps;

import java.util.Date;

public class ApiIteratorClass {

	public Date unixEpochToStandardTime (String dueDateFromApi) {
		Date date = new Date(Long.parseLong(dueDateFromApi.trim())*1000L);
//		SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd");
//		jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//		return jdf.format(date);
		return date;
	}
	
}
