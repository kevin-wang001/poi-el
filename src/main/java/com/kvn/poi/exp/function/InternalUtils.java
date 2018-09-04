package com.kvn.poi.exp.function;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * <pre>
 * 内部函数库，使用EL表达式在excel模板中调用。
 * 示例：
 *  #{ T(com.kvn.poi.function.InternalUtils).fmtDate(beginTime,'yyyy-MM-dd') }
 * </pre>
 * 
 * @author wzy
 * @date 2017年7月6日 下午5:47:03
 */
public class InternalUtils {

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String fmtDate(Date date, String pattern) {
		DateTime dt = new DateTime(date.getTime());
		return dt.toString(pattern);
	}

	/**
	 * 匹配替换函数
	 * @param source 源数据
	 * @param target 待匹配的目标数据
	 * @param matchedPlaceholder 匹配成功后返回的占位符
	 * @param missedPlaceholder 匹配失败后返回的占位符
	 * @return
	 */
	public static String matchBooleanReplaceBy(boolean source, boolean target, String matchedPlaceholder, String missedPlaceholder){
		return source == target ? matchedPlaceholder : missedPlaceholder;
	}

	/**
	 * 匹配替换函数
	 * @param source 源数据
	 * @param target 待匹配的目标数据
	 * @param matchedPlaceholder 匹配成功后返回的占位符
	 * @param missedPlaceholder 匹配失败后返回的占位符
	 * @return
	 */
	public static String matchCharReplaceBy(String source, String target, String matchedPlaceholder, String missedPlaceholder){
		if (source == null || target == null) {
			return missedPlaceholder;
		}
		return source.trim().equals(target.trim()) ? matchedPlaceholder : missedPlaceholder;
	}

	/**
	 * 匹配替换函数
	 * @param source 源数据
	 * @param targetArray 待匹配的目标数据数组
	 * @param matchedPlaceholder 匹配成功后返回的占位符
	 * @param missedPlaceholder 匹配失败后返回的占位符
	 * @return
	 */
	public static String matchCharReplaceBy(String source, String[] targetArray, String matchedPlaceholder, String missedPlaceholder){
		if (source == null || targetArray == null) {
			return missedPlaceholder;
		}

		for (String target : targetArray) {
			if (source.trim().equals(target.trim())) {
				return matchedPlaceholder;
			}
		}

		return missedPlaceholder;
	}

	/**
	 * 匹配替换函数
	 * @param source 源数据
	 * @param target 待匹配的目标数据
	 * @param matchedPlaceholder 匹配成功后返回的占位符
	 * @param missedPlaceholder 匹配失败后返回的占位符
	 * @return
	 */
	public static String matchIntReplaceBy(int source, int target, String matchedPlaceholder, String missedPlaceholder){
		return source == target ? matchedPlaceholder : missedPlaceholder;
	}

	/**
	 * 匹配替换函数
	 * @param source 源数据
	 * @param targetArr 待匹配的目标数据
	 * @param matchedPlaceholder 匹配成功后返回的占位符
	 * @param missedPlaceholder 匹配失败后返回的占位符
	 * @return
	 */
	public static String matchIntReplaceBy(int source, int[] targetArr, String matchedPlaceholder, String missedPlaceholder){
		for (int target : targetArr) {
			if (source == target) {
				return matchedPlaceholder;
			}
		}
		return missedPlaceholder;
	}

}
