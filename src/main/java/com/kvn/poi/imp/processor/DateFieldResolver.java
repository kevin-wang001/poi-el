package com.kvn.poi.imp.processor;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.kvn.poi.imp.anno.ExcelDateColum;

/**
* @author wzy
* @date 2017年7月12日 下午2:40:20
*/
public class DateFieldResolver implements Resolver {
	private Field field;
	private ExcelDateColum excelDateColum;
	private List<Object> input;
	private List<Object> head;

	public DateFieldResolver(Field field, ExcelDateColum edc) {
		super();
		this.field = field;
		this.excelDateColum = edc;
	}

	@Override
	public boolean support(Field field) {
		ExcelDateColum edc = field.getAnnotation(ExcelDateColum.class);
		return edc != null;
	}

	@Override
	public Object process() {
		String columnName = excelDateColum.value();
		int indexOfColumn = head.indexOf(columnName);
		Object columnRawValue = input.get(indexOfColumn);
		DateTimeFormatter format = DateTimeFormat.forPattern(excelDateColum.pattern());
//		// 时间解析
		return DateTime.parse(columnRawValue.toString(), format).toDate();
	}

	@Override
	public Resolver build(List<Object> input, List<Object> head) {
		this.input = input;
		this.head = head;
		return this;
	}

}
