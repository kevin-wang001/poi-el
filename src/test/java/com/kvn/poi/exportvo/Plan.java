package com.kvn.poi.exportvo;

import java.math.BigDecimal;
import java.util.Date;

/**
* @author wzy
* @date 2017年7月7日 上午11:41:25
*/
public class Plan {
	// 项目名称
	private String projectName;
	// 项目简称
	private String projectShortName;
	// 合同编号
	private String contractCode;
	// 信托成立日期
	private Date startDate;
	// 预计结束日期
	private Date endDate;
	// 信托计划期限
	private int planTimes;
	// 信托计划期限单位
	private int planTimesUnit;
	// 期限说明
	private String timesDesc;
	// 信托初始成立规模
	private long trustPlanScale;
	// 信托初始成立份额
	private BigDecimal trustPlanShare;
	 private BigDecimal beneficiaryTrustShare;
	// 信托单位面值
	private Integer termValue;
	// 每份认购价格
	private double price;
	// 最低预期收益率
	private int lowestRate;
	// 最高预期收益率
	private int higtestRate;
	// 合同金额
	private BigDecimal contractAmount;
	// 合同签订日期
	private Date signContractDate;
	// 合同签订地:省
	private String contractProvince;
	// 合同签订地:市
	private String contractCity;
	// 信托功能 1:融资类 2:投资类 3:组合类 4:事务管理类
	private int trustFunction;
	// 计划筹集资金上限
	private long moneyLimit;
	// 是否分笔到账
	private boolean tickByTick;
	// 成立条件说明
	private String foundedConditionDesc;
	// 信托性质
	private int trustNature;
	// 管理方式
	private String manageType;
	// 结算币种
	private String settlementCurrency;
	// 资金运用地区：省
	private String fundsRegionProvince;
	// 资金运用地区：市
	private String fundsRegionCity;
	// 资金运用方式
	private int[] fundsUseType;
	// 资金运用行业
	private int[] fundsUseIndustry;
	// 资金运用说明
	private String fundsUsedDesc;
	// 风控措施
	private int[] riskManageMeasures;
	// 信托计划托管人名称
	private String custodianBankName;
	// 信托计划托管人金融许可证编号
	private String custodianBankOrgCode;
	// 投资顾问名称
	private String advisorName;
	// 投资顾问组织机构代码
	private String advisorOrgCode;
	// 信托财产专户名称
	private String propertyAccountName;
	// 信托财产专户账号
	private String propertyAccount;
	// 信托财产专户开户行名称
	private String propertyAccountBankName;
	private String remark1;
	private String remark2;
	// 信托经理姓名
	private String bailorName;
	//
	private String bailorCardCode;
	private String bailorFaxCode;
	private String bailorMobile;
	private String bailorEmail;
	private String bailorPostCode;
	private String bailorAddress;
	private String beneficiaryName;
	private String beneficiaryCardCode;
	private String beneficiaryMobile;
	private String beneficiaryFaxCode;
	private String beneficiaryEmail;
	private String beneficiaryPostCode;
	private String beneficiaryAddress;

	public BigDecimal getBeneficiaryTrustShare() {
		return beneficiaryTrustShare;
	}

	public void setBeneficiaryTrustShare(BigDecimal beneficiaryTrustShare) {
		this.beneficiaryTrustShare = beneficiaryTrustShare;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectShortName() {
		return projectShortName;
	}

	public void setProjectShortName(String projectShortName) {
		this.projectShortName = projectShortName;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPlanTimes() {
		return planTimes;
	}

	public void setPlanTimes(int planTimes) {
		this.planTimes = planTimes;
	}

	public int getPlanTimesUnit() {
		return planTimesUnit;
	}

	public void setPlanTimesUnit(int planTimesUnit) {
		this.planTimesUnit = planTimesUnit;
	}

	public String getTimesDesc() {
		return timesDesc;
	}

	public void setTimesDesc(String timesDesc) {
		this.timesDesc = timesDesc;
	}

	public long getTrustPlanScale() {
		return trustPlanScale;
	}

	public void setTrustPlanScale(long trustPlanScale) {
		this.trustPlanScale = trustPlanScale;
	}

	public BigDecimal getTrustPlanShare() {
		return trustPlanShare;
	}

	public void setTrustPlanShare(BigDecimal trustPlanShare) {
		this.trustPlanShare = trustPlanShare;
	}

	public Integer getTermValue() {
		return termValue;
	}

	public void setTermValue(Integer termValue) {
		this.termValue = termValue;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getLowestRate() {
		return lowestRate;
	}

	public void setLowestRate(int lowestRate) {
		this.lowestRate = lowestRate;
	}

	public int getHigtestRate() {
		return higtestRate;
	}

	public void setHigtestRate(int higtestRate) {
		this.higtestRate = higtestRate;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Date getSignContractDate() {
		return signContractDate;
	}

	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}

	public String getContractProvince() {
		return contractProvince;
	}

	public void setContractProvince(String contractProvince) {
		this.contractProvince = contractProvince;
	}

	public String getContractCity() {
		return contractCity;
	}

	public void setContractCity(String contractCity) {
		this.contractCity = contractCity;
	}

	public int getTrustFunction() {
		return trustFunction;
	}

	public void setTrustFunction(int trustFunction) {
		this.trustFunction = trustFunction;
	}

	public long getMoneyLimit() {
		return moneyLimit;
	}

	public void setMoneyLimit(long moneyLimit) {
		this.moneyLimit = moneyLimit;
	}

	public boolean isTickByTick() {
		return tickByTick;
	}

	public void setTickByTick(boolean tickByTick) {
		this.tickByTick = tickByTick;
	}

	public String getFoundedConditionDesc() {
		return foundedConditionDesc;
	}

	public void setFoundedConditionDesc(String foundedConditionDesc) {
		this.foundedConditionDesc = foundedConditionDesc;
	}

	public int getTrustNature() {
		return trustNature;
	}

	public void setTrustNature(int trustNature) {
		this.trustNature = trustNature;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public String getSettlementCurrency() {
		return settlementCurrency;
	}

	public void setSettlementCurrency(String settlementCurrency) {
		this.settlementCurrency = settlementCurrency;
	}

	public String getFundsRegionProvince() {
		return fundsRegionProvince;
	}

	public void setFundsRegionProvince(String fundsRegionProvince) {
		this.fundsRegionProvince = fundsRegionProvince;
	}

	public String getFundsRegionCity() {
		return fundsRegionCity;
	}

	public void setFundsRegionCity(String fundsRegionCity) {
		this.fundsRegionCity = fundsRegionCity;
	}

	public int[] getFundsUseType() {
		return fundsUseType;
	}

	public void setFundsUseType(int[] fundsUseType) {
		this.fundsUseType = fundsUseType;
	}

	public int[] getFundsUseIndustry() {
		return fundsUseIndustry;
	}

	public void setFundsUseIndustry(int[] fundsUseIndustry) {
		this.fundsUseIndustry = fundsUseIndustry;
	}

	public String getFundsUsedDesc() {
		return fundsUsedDesc;
	}

	public void setFundsUsedDesc(String fundsUsedDesc) {
		this.fundsUsedDesc = fundsUsedDesc;
	}

	public int[] getRiskManageMeasures() {
		return riskManageMeasures;
	}

	public void setRiskManageMeasures(int[] riskManageMeasures) {
		this.riskManageMeasures = riskManageMeasures;
	}

	public String getCustodianBankName() {
		return custodianBankName;
	}

	public void setCustodianBankName(String custodianBankName) {
		this.custodianBankName = custodianBankName;
	}

	public String getCustodianBankOrgCode() {
		return custodianBankOrgCode;
	}

	public void setCustodianBankOrgCode(String custodianBankOrgCode) {
		this.custodianBankOrgCode = custodianBankOrgCode;
	}

	public String getAdvisorName() {
		return advisorName;
	}

	public void setAdvisorName(String advisorName) {
		this.advisorName = advisorName;
	}

	public String getAdvisorOrgCode() {
		return advisorOrgCode;
	}

	public void setAdvisorOrgCode(String advisorOrgCode) {
		this.advisorOrgCode = advisorOrgCode;
	}

	public String getPropertyAccountName() {
		return propertyAccountName;
	}

	public void setPropertyAccountName(String propertyAccountName) {
		this.propertyAccountName = propertyAccountName;
	}

	public String getPropertyAccount() {
		return propertyAccount;
	}

	public void setPropertyAccount(String propertyAccount) {
		this.propertyAccount = propertyAccount;
	}

	public String getPropertyAccountBankName() {
		return propertyAccountBankName;
	}

	public void setPropertyAccountBankName(String propertyAccountBankName) {
		this.propertyAccountBankName = propertyAccountBankName;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getBailorName() {
		return bailorName;
	}

	public void setBailorName(String bailorName) {
		this.bailorName = bailorName;
	}

	public String getBailorCardCode() {
		return bailorCardCode;
	}

	public void setBailorCardCode(String bailorCardCode) {
		this.bailorCardCode = bailorCardCode;
	}

	public String getBailorFaxCode() {
		return bailorFaxCode;
	}

	public void setBailorFaxCode(String bailorFaxCode) {
		this.bailorFaxCode = bailorFaxCode;
	}

	public String getBailorMobile() {
		return bailorMobile;
	}

	public void setBailorMobile(String bailorMobile) {
		this.bailorMobile = bailorMobile;
	}

	public String getBailorEmail() {
		return bailorEmail;
	}

	public void setBailorEmail(String bailorEmail) {
		this.bailorEmail = bailorEmail;
	}

	public String getBailorPostCode() {
		return bailorPostCode;
	}

	public void setBailorPostCode(String bailorPostCode) {
		this.bailorPostCode = bailorPostCode;
	}

	public String getBailorAddress() {
		return bailorAddress;
	}

	public void setBailorAddress(String bailorAddress) {
		this.bailorAddress = bailorAddress;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryCardCode() {
		return beneficiaryCardCode;
	}

	public void setBeneficiaryCardCode(String beneficiaryCardCode) {
		this.beneficiaryCardCode = beneficiaryCardCode;
	}

	public String getBeneficiaryMobile() {
		return beneficiaryMobile;
	}

	public void setBeneficiaryMobile(String beneficiaryMobile) {
		this.beneficiaryMobile = beneficiaryMobile;
	}

	public String getBeneficiaryFaxCode() {
		return beneficiaryFaxCode;
	}

	public void setBeneficiaryFaxCode(String beneficiaryFaxCode) {
		this.beneficiaryFaxCode = beneficiaryFaxCode;
	}

	public String getBeneficiaryEmail() {
		return beneficiaryEmail;
	}

	public void setBeneficiaryEmail(String beneficiaryEmail) {
		this.beneficiaryEmail = beneficiaryEmail;
	}

	public String getBeneficiaryPostCode() {
		return beneficiaryPostCode;
	}

	public void setBeneficiaryPostCode(String beneficiaryPostCode) {
		this.beneficiaryPostCode = beneficiaryPostCode;
	}

	public String getBeneficiaryAddress() {
		return beneficiaryAddress;
	}

	public void setBeneficiaryAddress(String beneficiaryAddress) {
		this.beneficiaryAddress = beneficiaryAddress;
	}
}
