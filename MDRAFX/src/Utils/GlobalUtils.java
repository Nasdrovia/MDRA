package Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;

public class GlobalUtils {

	/**
	 * Method used to get the current date, in SQL format
	 * 
	 * @return an SQL Date
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		Date date = new Date(currentDate.getTime());
		return date;
	}

	/**
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = is.read();

		while (reads != -1) {
			baos.write(reads);
			reads = is.read();
		}

		return baos.toByteArray();
	}

}
