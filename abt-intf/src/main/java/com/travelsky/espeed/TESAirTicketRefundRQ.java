//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.15 时间 04:21:17 PM CST 
//


package com.travelsky.espeed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://espeed.travelsky.com}POS"/>
 *         &lt;element ref="{http://espeed.travelsky.com}TicketRefundInfoDetails"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CancelSeatInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="OperationNo" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="ConfirmRefundInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ReturnRefundFormInfoInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CheckSegStatusInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pos",
    "ticketRefundInfoDetails"
})
@XmlRootElement(name = "TES_AirTicketRefundRQ")
public class TESAirTicketRefundRQ {

    @XmlElement(name = "POS", required = true)
    protected POS pos;
    @XmlElement(name = "TicketRefundInfoDetails", required = true)
    protected TicketRefundInfoDetails ticketRefundInfoDetails;
    @XmlAttribute(name = "EchoToken")
    protected String echoToken;
    @XmlAttribute(name = "TimeStamp")
    protected String timeStamp;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "Target")
    protected String target;
    @XmlAttribute(name = "CancelSeatInd")
    protected Boolean cancelSeatInd;
    @XmlAttribute(name = "OperationNo")
    @XmlSchemaType(name = "anySimpleType")
    protected String operationNo;
    @XmlAttribute(name = "ConfirmRefundInd")
    protected Boolean confirmRefundInd;
    @XmlAttribute(name = "ReturnRefundFormInfoInd")
    protected Boolean returnRefundFormInfoInd;
    @XmlAttribute(name = "CheckSegStatusInd")
    protected Boolean checkSegStatusInd;

    /**
     * 获取pos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POS }
     *     
     */
    public POS getPOS() {
        return pos;
    }

    /**
     * 设置pos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POS }
     *     
     */
    public void setPOS(POS value) {
        this.pos = value;
    }

    /**
     * 获取ticketRefundInfoDetails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TicketRefundInfoDetails }
     *     
     */
    public TicketRefundInfoDetails getTicketRefundInfoDetails() {
        return ticketRefundInfoDetails;
    }

    /**
     * 设置ticketRefundInfoDetails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TicketRefundInfoDetails }
     *     
     */
    public void setTicketRefundInfoDetails(TicketRefundInfoDetails value) {
        this.ticketRefundInfoDetails = value;
    }

    /**
     * 获取echoToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEchoToken() {
        return echoToken;
    }

    /**
     * 设置echoToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEchoToken(String value) {
        this.echoToken = value;
    }

    /**
     * 获取timeStamp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设置timeStamp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStamp(String value) {
        this.timeStamp = value;
    }

    /**
     * 获取version属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * 获取target属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置target属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * 获取cancelSeatInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancelSeatInd() {
        return cancelSeatInd;
    }

    /**
     * 设置cancelSeatInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancelSeatInd(Boolean value) {
        this.cancelSeatInd = value;
    }

    /**
     * 获取operationNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationNo() {
        return operationNo;
    }

    /**
     * 设置operationNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationNo(String value) {
        this.operationNo = value;
    }

    /**
     * 获取confirmRefundInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConfirmRefundInd() {
        return confirmRefundInd;
    }

    /**
     * 设置confirmRefundInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConfirmRefundInd(Boolean value) {
        this.confirmRefundInd = value;
    }

    /**
     * 获取returnRefundFormInfoInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnRefundFormInfoInd() {
        return returnRefundFormInfoInd;
    }

    /**
     * 设置returnRefundFormInfoInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnRefundFormInfoInd(Boolean value) {
        this.returnRefundFormInfoInd = value;
    }

    /**
     * 获取checkSegStatusInd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCheckSegStatusInd() {
        return checkSegStatusInd;
    }

    /**
     * 设置checkSegStatusInd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCheckSegStatusInd(Boolean value) {
        this.checkSegStatusInd = value;
    }

}
