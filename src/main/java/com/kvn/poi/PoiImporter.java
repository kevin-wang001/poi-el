package com.kvn.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.kvn.poi.domain.PoiSheetVo;
import com.kvn.poi.exception.PoiElErrorCode;
import com.kvn.poi.log.Log;

/**
 * excel 导入类
 * 
 * @author wzy
 * @date 2017年7月12日 上午9:43:53
 */
public class PoiImporter {
	private static final Logger logger = LoggerFactory.getLogger(PoiImporter.class);

	public static PoiSheetVo importFirstSheetFrom(File excelFile) {
		FileInputStream is = null;

		try {
			is = new FileInputStream(excelFile);
		} catch (FileNotFoundException e) {
			throw PoiElErrorCode.TEMPLATE_FILE_NOT_FOUND.exp(e, excelFile.getName());
		}

		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(is);
		} catch (IOException e) {
			try {
				is.close();
			} catch (IOException e1) {
				logger.error(Log.op("PoiImporter#importFirstSheetFrom").msg("关闭资源失败").toString(), e);
			}
			throw PoiElErrorCode.SYSTEM_ERROR.exp(e);
		}

		long start = System.currentTimeMillis();
		PoiSheetVo sheetVo = processSheet(wb.getSheetAt(0));
		long end = System.currentTimeMillis();

		logger.info(Log.op("PoiImporter#importFirstSheetFrom").msg("处理[{0}]共耗时[{1}ms]", excelFile.getName(), (end - start)).toString());

		// 关闭资源
		try {
			is.close();
		} catch (IOException e) {
			logger.error(Log.op("PoiImporter#importFirstSheetFrom").msg("关闭资源失败").toString(), e);
		}

		return sheetVo;
	}

	/**
	 * 解析sheet
	 * 
	 * @param sheet
	 * @return
	 */
	private static PoiSheetVo processSheet(XSSFSheet sheet) {
		PoiSheetVo sheetVo = new PoiSheetVo();

		// 处理每行
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row == null) { // 跳过空行
				continue;
			}

			List<Object> oneRow = Lists.newArrayList(); // 行内容
			// 单元格起始位置可能不是第0个
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				Object value; // 单元格内容
				if (cell == null) {
					value = "";
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {
					value = cell.getDateCellValue();
				} else {
					value = getCellValueText(cell, row, sheet, sheet.getWorkbook());
				}
				oneRow.add(value);
			}

			sheetVo.getContent().add(oneRow);
		}

		return sheetVo;
	}

	/**
	 * 取cell值，统一转为String
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellValueText(XSSFCell cell, XSSFRow row, XSSFSheet sheet, XSSFWorkbook wb) {
		String cv = "";
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC:
			DecimalFormat df = new DecimalFormat();
			df.setParseIntegerOnly(true);
			double value = cell.getNumericCellValue();
			if (value % 1 == 0) {
				cv = df.format(value);
				cv = cv.replaceAll(",", "");
			} else {
				if ((value + "").length() >= 7) {
					// 四舍五入小数点后四位
					value = Math.round(value * 10000);
					value = value / 10000;
					df = new DecimalFormat("0.0000");
					cv = df.format(value);
				} else {
					cv = value + "";
				}
			}
			break;
		case XSSFCell.CELL_TYPE_STRING:
			cv = cell.getRichStringCellValue().getString().trim();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
//			logger.info("case CELL_TYPE_BLANK");
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			try {
				XSSFFormulaEvaluator evaluator = new XSSFFormulaEvaluator(wb);
				cv = evaluator.evaluate(cell).getStringValue();

			} catch (Exception e) {
				logger.error(Log.op("PoiImporter#getCellValueText").msg("解析出错！").toString(), e);
			}
			break;
		default:
			logger.warn(Log.op("PoiImporter#getCellValueText").msg("未知的单元格类型[{0}]", cell.getCellType()).toString());
			break;
		}
		return cv;
	}
}
