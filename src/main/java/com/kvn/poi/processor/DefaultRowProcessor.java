package com.kvn.poi.processor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.google.common.collect.Maps;
import com.kvn.poi.common.Constants;

/**
 * 默认的行处理器。单元格内容中含有${key}的进行替换，否则不处理
 * 
 * @author wzy
 */
public class DefaultRowProcessor implements RowProcessor {
	public static class SINGLE {
		public static DefaultRowProcessor INSTANCE = new DefaultRowProcessor();
	}

	@Override
	public int support(XSSFRow row) {
		return 0;
	}

	@Override
	public int dealRow(XSSFRow currentRow, Map<String, Object> rootObjectMap, SpelExpressionParser parser) {
		for (int i = 0; i < currentRow.getLastCellNum(); i++) {
			XSSFCell cell = currentRow.getCell(i);
			if (null != cell && cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String cellContent = cell.getStringCellValue();
				String resolvedContent = resolve(cellContent, rootObjectMap, parser);
				cell.setCellValue(resolvedContent);
			}

		}
		return 1;
	}

	/**
	 * 将带有${key}和${vo.key}的内容进行替换
	 * @param cellContent
	 * @param rootObjectMap
	 * @param parser 
	 * @return
	 */
	public static String resolve(String cellContent, Map<String, Object> rootObjectMap, SpelExpressionParser parser) {
		String resolvedContent = cellContent;
		Pattern pattern = Pattern.compile(Constants.POI_KEY_REGEXP);
		// 处理${key}
		for(Matcher matcher = pattern.matcher(resolvedContent); matcher.find(); matcher = pattern.matcher(resolvedContent)){
			String key = matcher.group(1);
			String value = (String) rootObjectMap.get(key);
			resolvedContent = matcher.replaceFirst(value);
		}
		
		// 处理${vo.key}
		pattern = Pattern.compile(Constants.POI_VO_DOT_KEY_REGEXP);
		for(Matcher matcher = pattern.matcher(resolvedContent); matcher.find(); matcher = pattern.matcher(resolvedContent)){
			String vokey = matcher.group(1);
			String keys[] = vokey.split("\\.");
			String vo = keys[0];
			String key = keys[1];
			Object rootObjectItem = rootObjectMap.get(vo);
			String resolvedKey = parser.parseExpression(key).getValue(rootObjectItem, String.class);
			resolvedContent = matcher.replaceFirst(resolvedKey);
		}
		return resolvedContent;
	}
	
	public static void main(String[] args) {
		Foo foo = new Foo(13, "kvn");
		Map<String, Object> rootObjectMap = Maps.newHashMap();
		rootObjectMap.put("key1", "111");
		rootObjectMap.put("key2", "222");
		rootObjectMap.put("foo", foo);
		
		String cellContent = "this is ${key1} and that is ${key2}, foo.name is ${foo.name}";
		SpelExpressionParser parser = new SpelExpressionParser();
		System.out.println(resolve(cellContent, rootObjectMap, parser));
	}
	
	public static class Foo{
		int id;
		String name;
		public Foo(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

}
