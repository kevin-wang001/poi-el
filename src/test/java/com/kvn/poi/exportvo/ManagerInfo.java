package com.kvn.poi.exportvo;

/**
 * Created by wangzhiyuan on 2018/9/5
 */
public class ManagerInfo {
    // 信托经理姓名
    private String name;
    // 证件类型
    private String certificateType;
    // 证件号码
    private String certificateNo;

    public ManagerInfo(String name, String certificateType, String certificateNo) {
        this.name = name;
        this.certificateType = certificateType;
        this.certificateNo = certificateNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }
}
