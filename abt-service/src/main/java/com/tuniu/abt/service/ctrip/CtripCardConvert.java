package com.tuniu.abt.service.ctrip;

/**
 * 证件类型，途牛转换程携程对应的证件类型
 * @author baodawei
 *
 */
public enum CtripCardConvert {
	//枚举常量必须要写在类开始的位置,不然会报错
	NA(0),//未知
	ID(1),//二代身份证
	PASSPORT(2),//护照
	MTC(3),//军官证
	HMP(4),//港澳通行证
	OTHER(6),//其它
	MTP(7),//台胞证
	RP(8),//回乡证
	RBT(9),//户口簿
	BRC(10),//出生证明
	TP(11);	//台湾通行证
	private String strValue;
	private int intValue;
	private CtripCardConvert(String strValue)
	{
		this.strValue=strValue;
	}
	private CtripCardConvert(int intValue)
	{
		this.intValue=intValue;
	}
	public String getStrValue()
	{
		return strValue;
	}
	public int getIntValue()
	{
		return intValue;
	}
	

}
