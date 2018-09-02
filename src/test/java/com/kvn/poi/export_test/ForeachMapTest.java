package com.kvn.poi.export_test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kvn.poi.MockUtil;
import com.kvn.poi.exp.PoiExporter;
import com.kvn.poi.exportvo.Order;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author wzy
* @date 2017年7月4日 下午5:52:23
*/
public class ForeachMapTest {

	/**
	 * 取map里面的值，应该使用[key]的格式去取，本例中使用 #{["name"]}
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Map<String, Object> rootObjectMap = Maps.newHashMap();
		Map<String, Object> map = new HashMap<>();
		map.put("name","zkkk");
		List list = Lists.newArrayList(map);
		rootObjectMap.put("list", list);
		OutputStream des = new FileOutputStream("E:\\rlt\\test.xlsx");
		InputStream is = ForeachMapTest.class.getClassLoader().getResourceAsStream("template/04_Template_foreach_map.xlsx");
		PoiExporter.export2Destination(is, rootObjectMap, des);
	}

}
