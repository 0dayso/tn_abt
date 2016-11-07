//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 02:06:47 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{http://espeed.travelsky.com}DocHolderFormattedName" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="DocType" type="{http://espeed.travelsky.com}DocumentType" />
 *       &lt;attribute name="DocTypeDetail" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DocID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DocIssueCountry" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DocHolderNationality" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DocHolderInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Gender" type="{http://espeed.travelsky.com}GenderType" />
 *       &lt;attribute name="ExpireDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="RPH" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "docHolderFormattedName"
})
@XmlRootElement(name = "Document")
public class Document {

    @XmlElement(name = "DocHolderFormattedName")
    protected DocHolderFormattedName docHolderFormattedName;
    @XmlAttribute(name = "DocType")
    protected DocumentType docType;
    @XmlAttribute(name = "DocTypeDetail")
    protected String docTypeDetail;
    @XmlAttribute(name = "DocID", required = true)
    protected String docID;
    @XmlAttribute(name = "DocIssueCountry")
    protected String docIssueCountry;
    @XmlAttribute(name = "DocHolderNationality")
    protected String docHolderNationality;
    @XmlAttribute(name = "DocHolderInd")
    protected Boolean docHolderInd;
    @XmlAttribute(name = "BirthDate")
    @XmlSchemaType(name = "date")
    protected String birthDate;
    @XmlAttribute(name = "Gender")
    protected GenderType gender;
    @XmlAttribute(name = "ExpireDate")
    @XmlSchemaType(name = "date")
    protected String expireDate;
    @XmlAttribute(name = "RPH")
    protected String rph;

    /**
     * 获取docHolderFormattedName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DocHolderFormattedName }
     *     
     */
    public DocHolderFormattedName getDocHolderFormattedName() {
        return docHolderFormattedName;
    }

    /**
     * 设置docHolderFormattedName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DocHolderFormattedName }
     *     
     */
    public void setDocHolderFormattedName(DocHolderFormattedName value) {
        this.docHolderFormattedName = value;
    }

    /**
     * 获取docType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DocumentType }
     *     
     */
    public DocumentType getDocType() {
        return docType;
    }

    /**
     * 设置docType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentType }
     *     
     */
    public void setDocType(DocumentType value) {
        this.docType = value;
    }

    /**
     * 获取docTypeDetail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocTypeDetail() {
        return docTypeDetail;
    }

    /**
     * 设置docTypeDetail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocTypeDetail(String value) {
        this.docTypeDetail = value;
    }

    /**
     * 获取docID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocID() {
        return docID;
    }

    /**
     * 设置docID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocID(String value) {
        this.docID = value;
    }

    /**
     * 获取docIssueCountry属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocIssueCountry() {
        return docIssueCountry;
    }

    /**
     * 设置docIssueCountry属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocIssueCountry(String value) {
        this.docIssueCountry = value;
    }

    /**
     * 获取docHolderNationality属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocHolderNationality() {
        return docHolderNationality;
    }

    /**
     * 设置docHolderNationality属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocHolderNationality(String value) {
        this.docHolderNationality = value;
    }

    /**
     * 获取docHolderInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDocHolderInd() {
        return docHolderInd;
    }

    /**
     * 设置docHolderInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDocHolderInd(Boolean value) {
        this.docHolderInd = value;
    }

    /**
     * 获取birthDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * 设置birthDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * 获取gender属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GenderType }
     *     
     */
    public GenderType getGender() {
        return gender;
    }

    /**
     * 设置gender属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GenderType }
     *     
     */
    public void setGender(GenderType value) {
        this.gender = value;
    }

    /**
     * 获取expireDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * 设置expireDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    /**
     * 获取rph属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRPH() {
        return rph;
    }

    /**
     * 设置rph属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRPH(String value) {
        this.rph = value;
    }

}
