package com.kvn.poi.complexSpel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kvn.poi.exp.context.PoiExporterContext;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author wzy
* @date 2017年7月4日 下午5:52:23
*/
public class SpElTest2 {
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> rootObjectMap = initRootObjectMap();
		PoiExporterContext peContext = new PoiExporterContext(new SpelExpressionParser(), rootObjectMap);
		List<?> list = (List<?>) peContext.getRootObjectMap().get("list");
		for (Object item : list) {
			Expression nameExp = peContext.getSpelExpParser().parseExpression("#{name}", new TemplateParserContext());
			Expression remarkExp = peContext.getSpelExpParser().parseExpression("#{remark}", new TemplateParserContext());
			Expression mathExp = peContext.getSpelExpParser().parseExpression("#{scoreMap['math']}", new TemplateParserContext());
			String nameContent = nameExp.getValue(PoiExporterContext.EVAL_CONTEXT, item, String.class);
			String remarkContent = remarkExp.getValue(PoiExporterContext.EVAL_CONTEXT, item, String.class);
			String mathContent = mathExp.getValue(PoiExporterContext.EVAL_CONTEXT, item, String.class);
			System.out.println("name = [" + nameContent + "]");
			System.out.println("remark = [" + remarkContent + "]");
			System.out.println("math = [" + mathContent + "]");
		}


	}

	private static Map<String,Object> initRootObjectMap() {
		Map<String, Object> rootObjectMap = Maps.newHashMap();
		List<Stu> stuList = Lists.newArrayList();
		for (int i = 0; i < 2; i++) {
			Stu stu = new Stu();
			stu.setName("张三" + i);
			stu.setRemark("备注" + i);
			Map<String, Integer> scoreMap = Maps.newHashMap();
			scoreMap.put("math", 95 + i);
			scoreMap.put("chinese", 80 + i);
			scoreMap.put("english", 77 + i);
			stu.setScoreMap(scoreMap);
			stuList.add(stu);
		}

		rootObjectMap.put("list", stuList);
		return rootObjectMap;
	}


	public static class Stu {
		private String name;
		private String remark;
		private Map<String, Integer> scoreMap;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Map<String, Integer> getScoreMap() {
			return scoreMap;
		}

		public void setScoreMap(Map<String, Integer> scoreMap) {
			this.scoreMap = scoreMap;
		}
	}

}
