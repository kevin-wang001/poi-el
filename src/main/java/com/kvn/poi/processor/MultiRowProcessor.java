package com.kvn.poi.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.kvn.poi.common.Constants;
import com.kvn.poi.domain.MutiRowModel;
import com.kvn.poi.exception.PoiElErrorCode;

public class MultiRowProcessor implements RowProcessor {
	

	@Override
	public int dealRow(XSSFRow currentRow, Map<String, Object> rootObjectMap, final SpelExpressionParser parser) {
		XSSFCell beginCell = currentRow.getCell(support(currentRow));

		// 从beginCell中找出list key
		String beginCellContent = beginCell.getStringCellValue();
		Matcher ma = Pattern.compile(Constants.POI_FOREACH_START_REGEXP).matcher(beginCellContent);
		String key = null;
		if (ma.find()) {
			key = ma.group(1).trim();
		} else {
			throw PoiElErrorCode.TAG_NOT_FOUND.exp(beginCellContent, Constants.POI_FOREACH_START_REGEXP);
		}

		MutiRowModel tpRow = new MutiRowModel();
		tpRow.setListKey(key);
		tpRow.setBegin(beginCell.getRowIndex());
		Map<Integer, Map<Integer, Object>> cellMap = new TreeMap<>();
		int beginRowNum = beginCell.getRow().getRowNum();
		Pattern prePattern = Pattern.compile(Constants.POI_FOREACH_START_REGEXP);
		Pattern postPattern = Pattern.compile(Constants.POI_FOREACH_END_REGEXP);
		while (beginRowNum <= beginCell.getSheet().getLastRowNum() && null == tpRow.getEnd()) {
			XSSFRow row = beginCell.getSheet().getRow(beginRowNum);
			if (row == null) {
				// throw new Exception();
			}
			Map<Integer, Object> map = new HashMap<>();
			short end = row.getLastCellNum();
			for (int k = 0; k <= end; k++) {
				XSSFCell cell = row.getCell(k);
				if (null == cell) {
					continue;
				}
				String cellValue = cell.getRichStringCellValue().getString().trim();
				if (cellValue.equals("")) {
					continue;
				}
				String value = cellValue;
				Matcher preMatcher = prePattern.matcher(cellValue);
				if (preMatcher.find()) {
					value = preMatcher.replaceAll("");
				}
				Matcher postMatcher = postPattern.matcher(cellValue);
				if (postMatcher.find()) {
					value = postMatcher.replaceAll("");
					tpRow.setEnd(beginRowNum);
				}
				// 存放<单元格列号, 单元格内容>。单元格内容是除去tag之外的
				map.put(k, value);
			}
			cellMap.put(beginRowNum, map);
			beginRowNum++;
		}
		tpRow.setCellMap(cellMap);
		if (null == tpRow.getEnd()) {
			// throw new Exception();
		}

		Object rootObject = rootObjectMap.get(key);
		if(!(rootObject instanceof List)){
			throw PoiElErrorCode.ILLEGAL_PARAM.exp("<poi:foreach>中list：" + key + "对应的值应该为List");
		}
		
		List ls = (List) rootObject;
		
		// transfer
		setMutiData(beginCell, ls, tpRow, parser);

		return ls.size() * (tpRow.getEnd() - tpRow.getBegin() + 1);
	}

	private static void setMutiData(XSSFCell cell, List ls, MutiRowModel tpRow, final SpelExpressionParser parser) {
		XSSFSheet sheet = cell.getSheet();
		int mutiRow = tpRow.getEnd() - tpRow.getBegin() + 1; // 循环的行数
		// 行往下移
		sheet.shiftRows(tpRow.getEnd() + 1, sheet.getLastRowNum() + 3, (ls.size() - 1) * mutiRow, true, false);

		Map<Integer, Map<Integer, Object>> cellMap = tpRow.getCellMap();

		for (int i = 0; i < ls.size(); i++) {
			Object rootObjectItem = ls.get(i);
			for (Integer key1 : cellMap.keySet()) { // key1为row行号
				XSSFRow curRow = null;
				if (i == 0) {
					curRow = sheet.getRow(key1);
				} else {
					curRow = sheet.createRow(i * mutiRow + key1);
					// 拷贝样式
					copyCellStyle(sheet.getRow(key1), curRow);
					// 合并
					copyMergeRegion(sheet, key1, i * mutiRow + key1);
				}

				// 处理当前行里面的每个单元格：替换内容
				Map<Integer, Object> map = cellMap.get(key1);
				for (Integer key : map.keySet()) {
					String cellContent = map.get(key) == null ? "" : (String) map.get(key);
					XSSFCell c = curRow.getCell(key);
					c.setCellValue(parseValue(cellContent, parser, rootObjectItem));
//					c.setCellValue(CellProcessor.getCellReplaceValue(cellContent, data));
				}
			}
		}

	}

	private static String parseValue(String cellContent, final SpelExpressionParser parser, Object rootObjectItem) {
		Expression expression = parser.parseExpression(cellContent, new TemplateParserContext());
		return expression.getValue(rootObjectItem, String.class);
	}

	/**
	 * 拷贝样式
	 * 
	 * @param src
	 * @param des
	 */
	private static void copyCellStyle(XSSFRow src, XSSFRow des) {
		for (int i = src.getFirstCellNum(); i < src.getLastCellNum(); i++) {
			des.createCell(i).setCellStyle(src.getCell(i).getCellStyle());
		}
	}

	/**
	 * 拷贝合并单元格
	 * 
	 * @param sheet
	 * @param srcRow
	 * @param desRow
	 */
	private static void copyMergeRegion(XSSFSheet sheet, int srcRow, int desRow) {
		for (int j = 0; j < sheet.getNumMergedRegions(); j++) {
			CellRangeAddress oldRegion = sheet.getMergedRegion(j);
			if ((oldRegion.getFirstRow() == srcRow) && (oldRegion.getLastRow() == srcRow)) {
				int oldFirstCol = oldRegion.getFirstColumn();
				int oldLastCol = oldRegion.getLastColumn();
				CellRangeAddress newRegion = new CellRangeAddress(desRow, desRow, oldFirstCol, oldLastCol);
				// System.out.println(desRow+","+desRow+","+oldFirstCol+","+oldLastCol);
				sheet.addMergedRegion(newRegion);
			}
		}
	}

	@Override
	public int support(XSSFRow row) {
		for (int k = 0; k <= row.getLastCellNum(); k++) {
			XSSFCell cell = row.getCell(k);
			if (null != cell && cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String content = cell.getStringCellValue().trim();
				Pattern prePattern = Pattern.compile(Constants.POI_FOREACH_START_REGEXP);
				Matcher matcher = prePattern.matcher(content);
				if (matcher.find()) {
					return k;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String content = "<poi:foreach list=\"list\"> #{index}";
//		String content = "<poi:foreach list=\"list\">";
		boolean flag = content.matches("<poi:foreach\\s+list=\"(\\w+)\">");
		System.out.println(flag);
		
		Pattern prePattern = Pattern.compile(Constants.POI_FOREACH_START_REGEXP);
		Matcher matcher = prePattern.matcher(content);
		boolean f = matcher.find();
		System.out.println(f);
		System.out.println(matcher.replaceAll(""));
	}

}
