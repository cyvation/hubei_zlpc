package com.start.boot.email.webService;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for wotalkMessage complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="wotalkMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="appName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="expireTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linkUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="removed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tyywXxxh" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wotalkMessage", propOrder = { "appId", "appName", "content",
		"createdTime", "expireTime", "id", "linkUrl", "receiverId",
		"receiverName", "removed", "sendId", "sendType", "smsContent", "title",
		"tyywXxxh" })
public class WotalkMessage {

	protected String appId;
	protected String appName;
	protected String content;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createdTime;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar expireTime;
	protected String id;
	protected String linkUrl;
	protected String receiverId;
	protected String receiverName;
	protected String removed;
	protected String sendId;
	protected String sendType;
	protected String smsContent;
	protected String title;
	protected BigDecimal tyywXxxh;

	/**
	 * Gets the value of the appId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * Sets the value of the appId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAppId(String value) {
		this.appId = value;
	}

	/**
	 * Gets the value of the appName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * Sets the value of the appName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAppName(String value) {
		this.appName = value;
	}

	/**
	 * Gets the value of the content property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the value of the content property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * Gets the value of the createdTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreatedTime() {
		return createdTime;
	}

	/**
	 * Sets the value of the createdTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreatedTime(XMLGregorianCalendar value) {
		this.createdTime = value;
	}

	/**
	 * Gets the value of the expireTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getExpireTime() {
		return expireTime;
	}

	/**
	 * Sets the value of the expireTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setExpireTime(XMLGregorianCalendar value) {
		this.expireTime = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the linkUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * Sets the value of the linkUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinkUrl(String value) {
		this.linkUrl = value;
	}

	/**
	 * Gets the value of the receiverId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiverId() {
		return receiverId;
	}

	/**
	 * Sets the value of the receiverId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiverId(String value) {
		this.receiverId = value;
	}

	/**
	 * Gets the value of the receiverName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * Sets the value of the receiverName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiverName(String value) {
		this.receiverName = value;
	}

	/**
	 * Gets the value of the removed property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRemoved() {
		return removed;
	}

	/**
	 * Sets the value of the removed property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRemoved(String value) {
		this.removed = value;
	}

	/**
	 * Gets the value of the sendId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSendId() {
		return sendId;
	}

	/**
	 * Sets the value of the sendId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSendId(String value) {
		this.sendId = value;
	}

	/**
	 * Gets the value of the sendType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSendType() {
		return sendType;
	}

	/**
	 * Sets the value of the sendType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSendType(String value) {
		this.sendType = value;
	}

	/**
	 * Gets the value of the smsContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSmsContent() {
		return smsContent;
	}

	/**
	 * Sets the value of the smsContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSmsContent(String value) {
		this.smsContent = value;
	}

	/**
	 * Gets the value of the title property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the value of the title property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTitle(String value) {
		this.title = value;
	}

	/**
	 * Gets the value of the tyywXxxh property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getTyywXxxh() {
		return tyywXxxh;
	}

	/**
	 * Sets the value of the tyywXxxh property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setTyywXxxh(BigDecimal value) {
		this.tyywXxxh = value;
	}

}
