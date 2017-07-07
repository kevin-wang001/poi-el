package com.kvn.poi.processor;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class PropertyRowProcessor implements RowProcessor {
	public static class SINGLE {
		public static PropertyRowProcessor INSTANCE = new PropertyRowProcessor();
	}

	@Override
	public int support(XSSFRow row) {
//		for (int k = 0; k <= row.getLastCellNum(); k++) {
//			XSSFCell cell = row.getCell(k);
//			if (null != cell && cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
//				String content = cell.getStringCellValue().trim();
//				Pattern prePattern = Pattern.compile(Constants.POI_PROPERTY_START_REGEXP);
//				Matcher matcher = prePattern.matcher(content);
//				if (matcher.find()) {
//					return k;
//				}
//			}
//		}
		return -1;
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
