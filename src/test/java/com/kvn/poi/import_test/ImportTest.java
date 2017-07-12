package com.kvn.poi.import_test;

import java.io.InputStream;

import com.alibaba.fastjson.JSON;
import com.kvn.poi.imp.PoiImporter;
import com.kvn.poi.imp.vo.PoiGenericSheetVo;
import com.kvn.poi.imp.vo.PoiSheetVo;
import com.kvn.poi.importvo.OrderImportVo;

/**
* @author wzy
* @date 2017年7月12日 下午2:00:30
*/
public class ImportTest {

	public static void main(String[] args) {
		importGeneric();
	}
	
	public static void importRaw(){
		InputStream is = ImportTest.class.getClassLoader().getResourceAsStream("excel/order.xlsx");
		PoiSheetVo sheetVo = PoiImporter.importFirstSheetFrom(is);
		System.out.println("===>" + JSON.toJSONString(sheetVo.getContent()));
	}
	
	public static void importGeneric(){
		InputStream is = ImportTest.class.getClassLoader().getResourceAsStream("excel/order.xlsx");
		PoiGenericSheetVo<OrderImportVo> genericSheetVo = PoiImporter.importFirstSheetFrom(is, OrderImportVo.class);
		System.out.println("===>" + JSON.toJSONString(genericSheetVo.getHead()));
		System.out.println("===>" + JSON.toJSONString(genericSheetVo.getBody()));
	}

}
