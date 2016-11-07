package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Passenger {
	/**
	 * 姓名
	 */
	@NotNull(message = "姓名不能为空！")
	private String name;
	
	/**
	 * 名
	 */
//	@NotNull(message = "名不能为空！")
	private String firstName;
	
	/**
	 * 姓
	 */
//	@NotNull(message = "姓不能为空！")
	private String lastName;
	
	/**
	 * 证件类型：1-身份证, 2-因私护照，3-军官证，4-港澳通行证，11-台湾通行证，7-台胞证，6-其他
	 */
	@NotNull(message = "证件类型不能为空！")
	private int idType;
	
	/**
	 * 证件有效期
	 */
//	@NotNull(message = "证件有效期不能为空！")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "证件有效期格式错误！")
	private String idExpiryDate;
	
	/**
	 * 证件号码
	 */
	@NotNull(message = "证件号码不能为空！")
	private String idNumber;
	
	/**
	 * 出生日期
	 */
//	@NotNull(message = "出生日期不能为空！")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "出生日期格式错误！")
	private String birthday;
	
	/**
	 * 国籍
	 */
//	@NotNull(message = "国籍不能为空！")
	private String country;
	
	/**
	 * 性别 1男 0女 9未知
	 */
	private int sex;
	
	/**
	 * 联系电话
	 */
//	@NotNull(message = "联系电话不能为空！")
	@Pattern(regexp = "\\d+", message = "联系电话格式错误！")
	private String tel;
	
	
	/** 签证始发国 **/
	private String countryOfIssue;
	
	/**
	 * 乘客类型code ADT成人  CHD儿童  INF婴儿
	 */
	@NotNull(message = "乘客类型不能为空！")
	private String passangerTypeCode;
	
	private String extraInfo;
	
	public boolean isAdt() {
		return "ADT".equals(getPassangerTypeCode());
	}
	public boolean isChd() {
		return "CHD".equals(getPassangerTypeCode());
	}
	public boolean isInf() {
		return "INF".equals(getPassangerTypeCode());
	}	
	public String getPassangerTypeCode() {
		return passangerTypeCode;
	}
	public void setPassangerTypeCode(String passangerTypeCode) {
		this.passangerTypeCode = passangerTypeCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getIdExpiryDate() {
		return idExpiryDate;
	}
	public void setIdExpiryDate(String idExpiryDate) {
		this.idExpiryDate = idExpiryDate;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCountryOfIssue() {
		return countryOfIssue;
	}
	public void setCountryOfIssue(String countryOfIssue) {
		this.countryOfIssue = countryOfIssue;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	
}
