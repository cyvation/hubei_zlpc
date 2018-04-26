package com.start.boot.email.webService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the webService package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _AddMessageSendResponse_QNAME = new QName(
			"http://webService/", "addMessageSendResponse");
	private final static QName _AddMessageSend_QNAME = new QName(
			"http://webService/", "addMessageSend");
	private final static QName _UpdateWotalkMessageTypeResponse_QNAME = new QName(
			"http://webService/", "updateWotalkMessageTypeResponse");
	private final static QName _GetWotalkMessage_QNAME = new QName(
			"http://webService/", "getWotalkMessage");
	private final static QName _AddMessage_QNAME = new QName(
			"http://webService/", "addMessage");
	private final static QName _AddMessageResponse_QNAME = new QName(
			"http://webService/", "addMessageResponse");
	private final static QName _GetWotalkMessageResponse_QNAME = new QName(
			"http://webService/", "getWotalkMessageResponse");
	private final static QName _UpdateWotalkMessageType_QNAME = new QName(
			"http://webService/", "updateWotalkMessageType");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: webService
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link AddMessageSendResponse }
	 * 
	 */
	public AddMessageSendResponse createAddMessageSendResponse() {
		return new AddMessageSendResponse();
	}

	/**
	 * Create an instance of {@link AddMessage }
	 * 
	 */
	public AddMessage createAddMessage() {
		return new AddMessage();
	}

	/**
	 * Create an instance of {@link AddMessageSend }
	 * 
	 */
	public AddMessageSend createAddMessageSend() {
		return new AddMessageSend();
	}

	/**
	 * Create an instance of {@link UpdateWotalkMessageType }
	 * 
	 */
	public UpdateWotalkMessageType createUpdateWotalkMessageType() {
		return new UpdateWotalkMessageType();
	}

	/**
	 * Create an instance of {@link WotalkMessage }
	 * 
	 */
	public WotalkMessage createWotalkMessage() {
		return new WotalkMessage();
	}

	/**
	 * Create an instance of {@link UpdateWotalkMessageTypeResponse }
	 * 
	 */
	public UpdateWotalkMessageTypeResponse createUpdateWotalkMessageTypeResponse() {
		return new UpdateWotalkMessageTypeResponse();
	}

	/**
	 * Create an instance of {@link GetWotalkMessageResponse }
	 * 
	 */
	public GetWotalkMessageResponse createGetWotalkMessageResponse() {
		return new GetWotalkMessageResponse();
	}

	/**
	 * Create an instance of {@link AddMessageResponse }
	 * 
	 */
	public AddMessageResponse createAddMessageResponse() {
		return new AddMessageResponse();
	}

	/**
	 * Create an instance of {@link GetWotalkMessage }
	 * 
	 */
	public GetWotalkMessage createGetWotalkMessage() {
		return new GetWotalkMessage();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AddMessageSendResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService/", name = "addMessageSendResponse")
	public JAXBElement<AddMessageSendResponse> createAddMessageSendResponse(
			AddMessageSendResponse value) {
		return new JAXBElement<AddMessageSendResponse>(
				_AddMessageSendResponse_QNAME, AddMessageSendResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddMessageSend }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService/", name = "addMessageSend")
	public JAXBElement<AddMessageSend> createAddMessageSend(AddMessageSend value) {
		return new JAXBElement<AddMessageSend>(_AddMessageSend_QNAME,
				AddMessageSend.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UpdateWotalkMessageTypeResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService/", name = "updateWotalkMessageTypeResponse")
	public JAXBElement<UpdateWotalkMessageTypeResponse> createUpdateWotalkMessageTypeResponse(
			UpdateWotalkMessageTypeResponse value) {
		return new JAXBElement<UpdateWotalkMessageTypeResponse>(
				_UpdateWotalkMessageTypeResponse_QNAME,
				UpdateWotalkMessageTypeResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetWotalkMessage }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService/", name = "getWotalkMessage")
	public JAXBElement<GetWotalkMessage> createGetWotalkMessage(
			GetWotalkMessage value) {
		return new JAXBElement<GetWotalkMessage>(_GetWotalkMessage_QNAME,
				GetWotalkMessage.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddMessage }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService/", name = "addMessage")
	public JAXBElement<AddMessage> createAddMessage(AddMessage value) {
		return new JAXBElement<AddMessage>(_AddMessage_QNAME, AddMessage.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AddMessageResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService/", name = "addMessageResponse")
	public JAXBElement<AddMessageResponse> createAddMessageResponse(
			AddMessageResponse value) {
		return new JAXBElement<AddMessageResponse>(_AddMessageResponse_QNAME,
				AddMessageResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetWotalkMessageResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService/", name = "getWotalkMessageResponse")
	public JAXBElement<GetWotalkMessageResponse> createGetWotalkMessageResponse(
			GetWotalkMessageResponse value) {
		return new JAXBElement<GetWotalkMessageResponse>(
				_GetWotalkMessageResponse_QNAME,
				GetWotalkMessageResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UpdateWotalkMessageType }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService/", name = "updateWotalkMessageType")
	public JAXBElement<UpdateWotalkMessageType> createUpdateWotalkMessageType(
			UpdateWotalkMessageType value) {
		return new JAXBElement<UpdateWotalkMessageType>(
				_UpdateWotalkMessageType_QNAME, UpdateWotalkMessageType.class,
				null, value);
	}

}
