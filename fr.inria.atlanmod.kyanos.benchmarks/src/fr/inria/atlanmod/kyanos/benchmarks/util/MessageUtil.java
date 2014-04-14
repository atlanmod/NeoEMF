package fr.inria.atlanmod.kyanos.benchmarks.util;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

public class MessageUtil {
	
	public static void showError(String message) {
		System.err.println(message);
	}
	
	public static String formatMillis(long millis) {
	return String.format("%02d:%02d:%02d", 
		    TimeUnit.MILLISECONDS.toHours(millis),
		    TimeUnit.MILLISECONDS.toMinutes(millis) - 
		    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
		    TimeUnit.MILLISECONDS.toSeconds(millis) - 
		    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
	}
	
	public static String byteCountToDisplaySize(long size) {
		int MB = 1024 * 1024;
		return MessageFormat.format("{0} MB", size / MB);
	}
}
