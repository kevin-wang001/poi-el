package com.kvn.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.kvn.poi.context.PoiElContext;
import com.kvn.poi.exception.PoiElErrorCode;
import com.kvn.poi.function.FunctionRegister;
import com.kvn.poi.log.Log;
import com.kvn.poi.processor.RowProcessorStrategy;

/**
 * excel导出类
 * @author wzy
 * @date 2017年7月5日 上午9:41:52
 */
public class PoiExporter {
	private static final Logger logger = LoggerFactory.getLogger(PoiExporter.class);
	
	/**
	 * 向StandardEvaluationContext中注册内部函数
	 */
	static {
		FunctionRegister.registerInternalFunction();
	}
	
	public static void parse(XSSFWorkbook wb, Map<String, Object> rootObjectMap) {
		Long start = System.currentTimeMillis();
		
		PoiElContext peContext = new PoiElContext(new SpelExpressionParser(), rootObjectMap);
		
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
				int dealRows = RowProcessorStrategy.getRowProcessor(row).dealRow(row, peContext);
				j = j + dealRows;
			}
		}
		
		long end = System.currentTimeMillis();
		
		logger.info(Log.op("PoiEl#parse").msg("PoiEl解析模板耗时[{0}]ms", (end - start)).toString());
	}
	
	
	public static XSSFWorkbook parse2Destination(String templatePath, Map<String, Object> rootObjectMap, OutputStream des){
		InputStream in = null;
		try {
			in = new FileInputStream(templatePath);
		} catch (FileNotFoundException e) {
			throw PoiElErrorCode.TEMPLATE_FILE_NOT_FOUND.exp(e, templatePath);
		}
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(in);
		} catch (IOException e) {
			throw PoiElErrorCode.SYSTEM_ERROR.exp(e);
		}
		PoiExporter.parse(wb, rootObjectMap);
		
		// 关闭资源
		try {
			wb.write(des);
			des.flush();
			des.close();
		} catch (IOException e) {
			throw PoiElErrorCode.SYSTEM_ERROR.exp(e);
		}
		
		return wb;
	}
}