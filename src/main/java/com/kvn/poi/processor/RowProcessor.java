package com.kvn.poi.processor;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public interface RowProcessor {
	int dealRow(XSSFRow currentRow, Map<String, Object> rootObjectMap, final SpelExpressionParser parser);
	int support(XSSFRow row);
}
