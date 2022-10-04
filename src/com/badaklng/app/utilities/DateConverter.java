package com.badaklng.app.utilities;

import java.sql.Timestamp;

import javax.persistence.AttributeConverter;

public class DateConverter implements AttributeConverter<Timestamp, String> {

	private static final String FORMAT = "dd-MM-yyyy";

	@Override
	public String convertToDatabaseColumn(Timestamp arg0) {
		return Utility.timestampToString(arg0, FORMAT);
	}

	@Override
	public Timestamp convertToEntityAttribute(String arg0) {
		return Utility.stringToTimestamp(arg0, FORMAT);
	}

}
