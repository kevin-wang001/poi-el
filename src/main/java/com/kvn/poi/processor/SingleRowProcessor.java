package com.kvn.poi.processor;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SingleRowProcessor implements RowProcessor {
	public static class SINGLE {
		public static SingleRowProcessor INSTANCE = new SingleRowProcessor();
	}

	@Override
	public int support(XSSFRow row) {
		return 0;
	}

	@Override
	public int dealRow(XSSFRow currentRow, Map<String, Object> rootObjectMap, SpelExpressionParser parser) {
//		for(int i=0; i<row.getLastCellNum(); i++){
//		XSSFCell cell = row.getCell(i);
//		if(null!= cell && cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
//			String cellContent = cell.getStringCellValue();
//			cell.setCellValue(CellProcessor.getCellReplaceValue(cellContent, data));
//		}
//		
//	}
	return 1;
	}

}
