package com.kvn.poi.common;
/**
* @author wzy
* @date 2017年7月6日 下午2:22:30
*/
public class Constants {
	public static final String POI_FOREACH_START_REGEXP = "<poi:foreach\\s+list=\"(\\w+)\">";
	public static final String POI_FOREACH_END_REGEXP = "</poi:foreach>";
	
	public static final String POI_PROPERTY_START_REGEXP = "<poi:property\\s+item=\"(\\w+)\">";
	public static final String POI_PROPERTY_END_REGEXP = "</poi:property>";
}
