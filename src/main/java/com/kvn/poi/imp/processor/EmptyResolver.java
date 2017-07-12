package com.kvn.poi.imp.processor;

import java.lang.reflect.Field;
import java.util.List;

/**
* @author wzy
* @date 2017年7月12日 下午2:40:20
*/
public class EmptyResolver implements Resolver {
	
	private EmptyResolver() {
		super();
	}

	public static class SINGLE {
		public static final EmptyResolver INSTANCE = new EmptyResolver();
	}

	@Override
	public boolean support(Field field) {
		return true;
	}

	@Override
	public Object process() {
		return null;
	}

	@Override
	public Resolver build(List<Object> input, List<Object> head) {
		return this;
	}

	

}
