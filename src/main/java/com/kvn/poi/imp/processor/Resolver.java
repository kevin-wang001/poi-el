package com.kvn.poi.imp.processor;

import java.lang.reflect.Field;
import java.util.List;

/**
* @author wzy
* @date 2017年7月12日 下午3:31:16
*/
public interface Resolver {
	
	boolean support(Field field);
	Object process();
	Resolver build(List<Object> input, List<Object> head);

}
