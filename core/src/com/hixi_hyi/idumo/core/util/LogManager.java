package com.hixi_hyi.idumo.core.util;

public class LogManager {
	public static boolean DEBUG;
	public static Logger LOGGER;

	public static void debug(Object o) {
		if(DEBUG&&LOGGER!=null){
			LOGGER.debug(o.toString());
		}
	}
	public static void info(Object o) {
		if(DEBUG&&LOGGER!=null){
			LOGGER.info(o.toString());
		}
	}
	public static void warning(Object o) {
		if(DEBUG&&LOGGER!=null){
			LOGGER.warning(o.toString());
		}
	}
	public static void log() {
		if(DEBUG&&LOGGER!=null){
			LOGGER.log();
		}
	}


}
