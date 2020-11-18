package com.app.dbloader.util;

import java.text.SimpleDateFormat;

public class DateUtil{
	public static final String DATE_FORMAT = "dd.MM.yy";

	public static final java.sql.Date getSqlDate(String date) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		java.util.Date parsed = format.parse(date);
		return new java.sql.Date(parsed.getTime());
	}
}
