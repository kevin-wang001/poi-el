package com.kvn.poi.processor;

import org.apache.poi.xssf.usermodel.XSSFRow;

import com.kvn.poi.context.PoiElContext;

public interface RowProcessor {
	int dealRow(XSSFRow currentRow, PoiElContext peContext);
	int support(XSSFRow row);
}
