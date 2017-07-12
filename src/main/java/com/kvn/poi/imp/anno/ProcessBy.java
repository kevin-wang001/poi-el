package com.kvn.poi.imp.anno;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
* @author wzy
* @date 2017年7月12日 下午2:39:13
*/
@Target({ ANNOTATION_TYPE })
@Retention(RUNTIME)
@Deprecated
public @interface ProcessBy {
	Class<?> value();
}
