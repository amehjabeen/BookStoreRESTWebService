package com.bookstore.service.representation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public abstract class AbstractRepresentation {
	
	@XmlElementWrapper(name="links")
	@XmlElement(name="link")
	protected List<Link> links = new ArrayList<Link>();
	
	public List<Link> getLinks() {
		return links;
	}
	
	public void addLink(Link link){
		this.links.add(link);
	}
}
