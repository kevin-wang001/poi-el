package com.kvn.poi.exp.function;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

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

    public static void main(String[] args) {
        Integer i1 = new Integer(3);
        Integer i2 = new Integer(2);

        System.out.println(((Comparable) i1).compareTo(i2));
    }

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
     * 匹配替换函数，处理target非数组的情况
     *
     * @param source             源数据
     * @param target        待匹配的目标数据
     * @param matchedPlaceholder 匹配成功后返回的占位符
     * @param missedPlaceholder  匹配失败后返回的占位符
     * @return
     */
    public static <T> String matchReplaceBy(T source, T target, String matchedPlaceholder, String missedPlaceholder) {
        if (source == null || target == null) {
            return missedPlaceholder;
        }

        Class<?> targetClass = target.getClass();
        if (targetClass.isArray()) {
            throw new IllegalArgumentException("请使用其他函数，泛型不支持数组类型：target class is " + targetClass);
        }

        if (targetClass.isPrimitive()) { // 基本数据类型
            return source == target ? matchedPlaceholder : missedPlaceholder;
        }
        if (Comparable.class.isAssignableFrom(targetClass)) { // 基本数据的包装类型，或者实现了 Comparable 的类型
            return ((Comparable) source).compareTo(target) == 0 ? matchedPlaceholder : missedPlaceholder;
        }

        if (targetClass == String.class) {
            return ((String) source).trim().equals(((String) target).trim()) ? matchedPlaceholder : missedPlaceholder;
        }
        throw new IllegalArgumentException("不支持的数据类型：" + targetClass);
    }


    /**
     * 匹配替换函数，处理target为List的情况
     *
     * @param source             源数据
     * @param targets        待匹配的目标数据数组
     * @param matchedPlaceholder 匹配成功后返回的占位符
     * @param missedPlaceholder  匹配失败后返回的占位符
     * @return
     */
    public static <T> String matchReplaceBy(T source, List<T> targets, String matchedPlaceholder, String missedPlaceholder) {
        if (source == null || targets == null) {
            return missedPlaceholder;
        }
        for (T target : targets) {
            if (target.getClass().isPrimitive() && source == target) { // 基本数据类型
                return matchedPlaceholder;
            }
            if (Comparable.class.isAssignableFrom(target.getClass()) && ((Comparable) source).compareTo(target) == 0) { // 基本数据的包装类型，或者实现了 Comparable 的类型
                return matchedPlaceholder;
            }
            if (target.getClass() == String.class && ((String) source).trim().equals(((String) target).trim())) {
                return matchedPlaceholder;
            }
        }
        return missedPlaceholder;
    }

    /**
     * 匹配替换函数
     *
     * @param source             源数据
     * @param targetArray        待匹配的目标数据数组
     * @param matchedPlaceholder 匹配成功后返回的占位符
     * @param missedPlaceholder  匹配失败后返回的占位符
     * @return
     */
    public static String matchArrReplaceBy(String source, String[] targetArray, String matchedPlaceholder, String missedPlaceholder) {
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
     *
     * @param source             源数据
     * @param targetArr          待匹配的目标数据
     * @param matchedPlaceholder 匹配成功后返回的占位符
     * @param missedPlaceholder  匹配失败后返回的占位符
     * @return
     */
    public static String matchArrReplaceBy(int source, int[] targetArr, String matchedPlaceholder, String missedPlaceholder) {
        for (int target : targetArr) {
            if (source == target) {
                return matchedPlaceholder;
            }
        }
        return missedPlaceholder;
    }

}
