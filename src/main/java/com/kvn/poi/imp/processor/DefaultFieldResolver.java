package com.kvn.poi.imp.processor;

import java.lang.reflect.Field;
import java.util.List;

import com.kvn.poi.common.PoiUtils;
import com.kvn.poi.imp.anno.ExcelColum;

/**
* @author wzy
* @date 2017年7月12日 下午2:40:20
*/
public class DefaultFieldResolver implements Resolver {
	private Field field;
	private ExcelColum excelColum;
	private List<Object> input;
	private List<Object> head;
	
	
	
	public DefaultFieldResolver(Field field, ExcelColum excelColum) {
		super();
		this.field = field;
		this.excelColum = excelColum;
	}

	public Object process() {
		String columnName = excelColum.value();
		int indexOfColumn = head.indexOf(columnName);
		Object columnRawValue = input.get(indexOfColumn);
		return PoiUtils.parse2Type(columnRawValue, field.getType());
	}
	
	public boolean support(Field field){
		ExcelColum ec = field.getAnnotation(ExcelColum.class);
		return ec != null;
	}

	@Override
	public Resolver build(List<Object> input, List<Object> head) {
		this.input = input;
		this.head = head;
		return this;
	}

}
