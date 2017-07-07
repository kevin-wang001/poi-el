package com.kvn.poi;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.kvn.poi.processor.RowProcessorStrategy;

/**
 * @author wzy
 * @date 2017年7月5日 上午9:41:52
 */
public class PoiEl {
	public static void parse(XSSFWorkbook wb, Map<String, Object> rootObjectMap) {
		final SpelExpressionParser parser = new SpelExpressionParser();
		
		// 只处理一个sheet
		for (int i = 0; i < 1; i++) {
			XSSFSheet sheet = wb.getSheetAt(i);
			// 开始行结束行
			int j = sheet.getFirstRowNum();
			// 每行
			while (j <= sheet.getLastRowNum()) {
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					continue;
				}
				int dealRows = RowProcessorStrategy.getRowProcessor(row).dealRow(row, rootObjectMap, parser);
				j = j + dealRows;
			}
		}
	}
}
