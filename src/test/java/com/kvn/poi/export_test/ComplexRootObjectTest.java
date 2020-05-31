package com.kvn.poi.export_test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kvn.poi.complexSpel.SpElTest2;
import com.kvn.poi.exp.PoiExporter;
import com.kvn.poi.exp.context.PoiExporterContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhiyuan on 2018/12/11
 */
public class ComplexRootObjectTest {

    public static String toggleDisplay(int min, int max, int value){
        if (value >= min && value <= max) {
            return String.valueOf(value);
        }
        return "";
    }


    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Object> rootObjectMap = initRootObjectMap();
        PoiExporterContext peContext = new PoiExporterContext(new SpelExpressionParser(), rootObjectMap);
        OutputStream des = new FileOutputStream("E:\\rlt\\test.xlsx");
        InputStream is = ForeachMapTest.class.getClassLoader().getResourceAsStream("template/03_Template_复杂模板2.xlsx");
        PoiExporter.export2Destination(is, rootObjectMap, des);
    }



    private static Map<String,Object> initRootObjectMap() {
        Map<String, Object> rootObjectMap = Maps.newHashMap();
        List<SpElTest2.Stu> stuList = Lists.newArrayList();
        for (int i = 0; i < 2; i++) {
            SpElTest2.Stu stu = new SpElTest2.Stu();
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
