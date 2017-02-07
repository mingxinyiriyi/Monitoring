package com.tc.www.monitoring.model;

/**
 * Created by Administrator on 2016/11/8.
 */

public class Ups {
    private String id;
    //Ups参数
    private String tem;
    private String type;//ups类型
    private String inputHz;//输入频率
    private String batteryVol;//电池电压
    private String inputVol;//输入电压
    private String outputVol;//输出电压
    private String faileVol;//故障电压值
    private String outputVolLoad;//输出电流负载

    //Ups图例
    private int normal; //正常
    private int banormal; //告警

    //Ups状态
    private int buzzerOpen;//蜂鸣器打开
    private int batteryLow;//电池低
    private int isTest;//正在测试
    private int upsFaile;//ups故障
    private int bypassVol;//旁路电压正在升压或降压
    private int isclose;//正在关机活关机状态
    private int cityFaile;//市电失败（即时）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInputHz() {
        return inputHz;
    }

    public void setInputHz(String inputHz) {
        this.inputHz = inputHz;
    }

    public String getBatteryVol() {
        return batteryVol;
    }

    public void setBatteryVol(String batteryVol) {
        this.batteryVol = batteryVol;
    }

    public String getInputVol() {
        return inputVol;
    }

    public void setInputVol(String inputVol) {
        this.inputVol = inputVol;
    }

    public String getOutputVol() {
        return outputVol;
    }

    public void setOutputVol(String outputVol) {
        this.outputVol = outputVol;
    }

    public String getFaileVol() {
        return faileVol;
    }

    public void setFaileVol(String faileVol) {
        this.faileVol = faileVol;
    }

    public String getOutputVolLoad() {
        return outputVolLoad;
    }

    public void setOutputVolLoad(String outputVolLoad) {
        this.outputVolLoad = outputVolLoad;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getBanormal() {
        return banormal;
    }

    public void setBanormal(int banormal) {
        this.banormal = banormal;
    }

    public int getBuzzerOpen() {
        return buzzerOpen;
    }

    public void setBuzzerOpen(int buzzerOpen) {
        this.buzzerOpen = buzzerOpen;
    }

    public int getBatteryLow() {
        return batteryLow;
    }

    public void setBatteryLow(int batteryLow) {
        this.batteryLow = batteryLow;
    }

    public int getIsTest() {
        return isTest;
    }

    public void setIsTest(int isTest) {
        this.isTest = isTest;
    }

    public int getUpsFaile() {
        return upsFaile;
    }

    public void setUpsFaile(int upsFaile) {
        this.upsFaile = upsFaile;
    }

    public int getBypassVol() {
        return bypassVol;
    }

    public void setBypassVol(int bypassVol) {
        this.bypassVol = bypassVol;
    }

    public int getIsclose() {
        return isclose;
    }

    public void setIsclose(int isclose) {
        this.isclose = isclose;
    }

    public int getCityFaile() {
        return cityFaile;
    }

    public void setCityFaile(int cityFaile) {
        this.cityFaile = cityFaile;
    }
}
