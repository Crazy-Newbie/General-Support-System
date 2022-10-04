package com.badaklng.app.utilities;

import java.sql.Timestamp;

import javax.persistence.AttributeConverter;

public class TimestampConverter implements AttributeConverter<Timestamp, String> {

	@Override
	public String convertToDatabaseColumn(Timestamp arg0) {
		return Utility.timestampToString(arg0, "yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public Timestamp convertToEntityAttribute(String arg0) {
		return Utility.stringToTimestamp(arg0, "yyyy-MM-dd HH:mm:ss");
	}

}
