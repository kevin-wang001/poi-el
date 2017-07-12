package com.kvn.poi.processor;

import org.apache.poi.xssf.usermodel.XSSFRow;

import com.kvn.poi.context.PoiExporterContext;

public interface RowProcessor {
	int dealRow(XSSFRow currentRow, PoiExporterContext peContext);
	int support(XSSFRow row);
}
