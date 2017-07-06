package com.kvn.poi.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kvn.poi.MockUtil;
import com.kvn.poi.PoiEl;
import com.kvn.poi.exportvo.Order;

/**
 * 测试有多个<poi:foreach>的情况
* @author wzy
* @date 2017年7月4日 下午5:52:23
*/
public class MultiPoiForeachTest {
	
	public static XSSFWorkbook getPoiWbByTpl(String path,Map<String, Object> rootObjectMap){
		Long beginTime = System.currentTimeMillis();
		System.out.println("开始处理时间:"+beginTime);
		XSSFWorkbook wb = null;
		try{
			OutputStream out = new FileOutputStream("E:\\rlt\\test.xlsx");
			InputStream in = new FileInputStream(path);
			wb = new XSSFWorkbook(in);
			PoiEl.parse(wb, rootObjectMap);
			wb.write(out);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		final Long endTime = System.currentTimeMillis();
		System.out.println("结束处理时间:"+endTime+",耗时："+(endTime-beginTime)/1000+"秒");
		return wb;
		
	}
	

	public static void main(String[] args) throws Exception {
		String path = "E:\\gitWorkspace\\poi-el\\src\\main\\resources\\template\\02_Template_多个foreach.xlsx";
		Map<String, Object> rootObjectMap = Maps.newHashMap();
		Order order1 = MockUtil.randomInstanceOfNonCollection(Order.class);
		Order order2 = MockUtil.randomInstanceOfNonCollection(Order.class);
		Order order3 = MockUtil.randomInstanceOfNonCollection(Order.class);
		
		List list = Lists.newArrayList(order1, order2, order3);
		rootObjectMap.put("list", list);
		
		
		Order order4 = MockUtil.randomInstanceOfNonCollection(Order.class);
		Order order5 = MockUtil.randomInstanceOfNonCollection(Order.class);
		List list2 = Lists.newArrayList(order4, order5);
		rootObjectMap.put("list2", list2);
		getPoiWbByTpl(path, rootObjectMap);
	}

}
