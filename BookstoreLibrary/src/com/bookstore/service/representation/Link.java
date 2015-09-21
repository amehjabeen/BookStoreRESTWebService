package com.bookstore.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Link")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class Link {
	private String use;
	private String url;
	private String mediaType;
	private String method;

	public Link() {}

	public Link(String use, String url, String method, String mediaType) {
		this.use = use;
		this.url = url;
		this.setMediaType(mediaType);
		this.setMethod(method);
	}

	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}