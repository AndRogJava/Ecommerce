package ecommerce.registration.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

public class DateValidator {
	public static boolean validator(String date) {
		try {
			LocalDate ld = LocalDate.parse(date);
			LocalDate after = LocalDate.of(1900, 01, 01);
			//data di oggi (zona CET)
			LocalDate before = LocalDate.now(ZoneId.of("CET"));
			if(ld.isBefore(before) && ld.isAfter(after))
				return true;
			else
				return false;
		}catch(DateTimeParseException e) {
			return false;
		}
	}
}
