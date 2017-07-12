package com.kvn.poi.imp.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
* @author wzy
* @date 2017年7月12日 下午4:03:50
*/
public abstract class AbstractResolver<T extends Annotation> implements Resolver {
	protected Field field;
	protected List<Object> input;
	protected List<Object> head;
	
	public Resolver build(List<Object> input, List<Object> head) {
		this.input = input;
		this.head = head;
		return this;
	}
	
	public boolean support(Field field) {
		return field.getAnnotation(getAnnotationClass()) != null;
	}

	protected abstract Class<T> getAnnotationClass();
}
