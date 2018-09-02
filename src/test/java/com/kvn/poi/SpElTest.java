package com.kvn.poi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kvn.poi.MockUtil;
import com.kvn.poi.exp.PoiExporter;
import com.kvn.poi.exp.context.PoiExporterContext;
import com.kvn.poi.exportvo.Order;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

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
public class SpElTest {
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> rootObjectMap = initRootObjectMap();
		PoiExporterContext peContext = new PoiExporterContext(new SpelExpressionParser(), rootObjectMap);
		List<?> list = (List<?>) peContext.getRootObjectMap().get("list");
		for (Object item : list) {
			Expression expression = peContext.getSpelExpParser().parseExpression("#{[\"name\"]}", new TemplateParserContext());
			String parsedContent = expression.getValue(PoiExporterContext.EVAL_CONTEXT, item, String.class);
			System.out.println("parsedContent = [" + parsedContent + "]");
		}


	}

	private static Map<String,Object> initRootObjectMap() {
		Map<String, Object> rootObjectMap = Maps.newHashMap();
		Map<String, Object> map = new HashMap<>();
		map.put("name","zkkk");
		List list = Lists.newArrayList(map);
		rootObjectMap.put("list", list);
		return rootObjectMap;
	}

}
