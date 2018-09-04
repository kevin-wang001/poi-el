package com.kvn.poi.export_test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kvn.poi.MockUtil;
import com.kvn.poi.exp.PoiExporter;
import com.kvn.poi.exportvo.Order;
import com.kvn.poi.exportvo.Plan;

/**
* @author wzy
* @date 2017年7月4日 下午5:52:23
*/
public class MixTemplateTest {

	public static void main(String[] args) throws Exception {
		Map<String, Object> rootObjectMap = Maps.newHashMap();
		Plan plan = initPlan();
		Order order1 = MockUtil.randomInstanceOfNonCollection(Order.class);
		Order order2 = MockUtil.randomInstanceOfNonCollection(Order.class);
		List<Order> list = Lists.newArrayList(order1, order2);
		
		
		rootObjectMap.put("mangerName", "张三");
		rootObjectMap.put("plan", plan);
		rootObjectMap.put("list", list);
		
		OutputStream out = new FileOutputStream("E:\\rlt\\test.xlsx");
		InputStream is = ForeachTest.class.getClassLoader().getResourceAsStream("template/03_Template_混合模板.xlsx");
		PoiExporter.export2Destination(is, rootObjectMap, out);
	}

	private static Plan initPlan() {
		Plan plan = new Plan();
		plan.setProjectName("迈高天空基金项目");
		plan.setProjectShortName("迈高");
		plan.setContractCode("10000201801240124");
		plan.setStartDate(new Date("2018/01/01"));
		plan.setEndDate(new Date());
		plan.setPlanTimes(3);
		plan.setPlanTimesUnit(2);
		plan.setTimesDesc("没有到期时间");
		plan.setTrustPlanScale(100000000L);
		plan.setTrustPlanShare(new BigDecimal(20000));
		plan.setTermValue(23);
		plan.setPrice(23.5);
		plan.setLowestRate(10);
		plan.setHigtestRate(30);
		plan.setSignContractDate(new Date("2018/05/07"));
		plan.setContractProvince("广东省");
		plan.setContractCity("深圳市");
		plan.setTrustFunction(3);
		plan.setMoneyLimit(1500000000);
		plan.setTickByTick(true);
		plan.setFoundedConditionDesc("帮帮忙，尽快");
		plan.setTrustNature(1);
		plan.setManageType("主动");
		plan.setSettlementCurrency("2");
		plan.setFundsRegionProvince("广东省");
		plan.setFundsRegionCity("广州市");
		plan.setFundsUseType(new int[]{1,2});
		plan.setFundsUseIndustry(new int[]{1,3});
		plan.setFundsUsedDesc("无特殊说明");
		plan.setRiskManageMeasures(new int[]{1, 3});
		plan.setCustodianBankName("ICBC");
		plan.setCustodianBankOrgCode("xx001");
		plan.setAdvisorName("张三");
		plan.setAdvisorOrgCode("xx机构");
		plan.setPropertyAccountName("dg账户");
		plan.setPropertyAccount("458557455687525");
		plan.setPropertyAccountBankName("ICBC");
		plan.setRemark1("111");
		plan.setRemark2("222");
		plan.setBailorName("李四");
		plan.setBailorCardCode("87855554545454");
		plan.setBailorFaxCode("7878-898-7789");
		plan.setBailorMobile("1365445887");
		plan.setBailorEmail("wanxxx@163.com");
		plan.setBailorPostCode("521201");
		plan.setBailorAddress("xxxx号11");
		plan.setBeneficiaryName("王五");
		plan.setBeneficiaryCardCode("65654452121444555");
		plan.setBeneficiaryMobile("1520012121222");
		plan.setBeneficiaryFaxCode("1211-4545-856");
		plan.setBeneficiaryEmail("wuxxx@qq.com");
		plan.setBeneficiaryPostCode("8540000");
		plan.setBeneficiaryAddress("xx路xx");
		return plan;
	}

}
