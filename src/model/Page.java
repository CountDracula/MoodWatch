package model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;





import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

//POJO for Hibernate
/// So this class creates table to the DB with java code
/// Use scheme with name "tester" (without the " " )



@Entity
@Table(name = "Pages")
public class Page {
	

	@ElementCollection (targetClass=String.class)
	@CollectionTable(name ="Sites", joinColumns=@JoinColumn(name="page_id"))
	private List sites;
		
	 public Page() {
		
			
		}
	 
	 public Page(String page, String siteToAdd)
	 {
		 this.page = page;
		 this.sites = Collections.synchronizedList(new ArrayList());
		
		 
		
	 }
	 
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "page_id")
	   private int page_id;


	
	@Column (name="timestamp")
	
	private String timestamp;

	@Column(name="pagename",nullable=false)
	private String page; ///e.g. www.google.com
	
	
	public int getPage_id() {
	return page_id;
}

public void setPage_id(int page_id) {
	this.page_id = page_id;
}

	@Column(name="mood",nullable=false)
	private int mood;
	
	

	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}





	public int compareTo(Page p) {
	    return this.page.compareTo(p.page);
	}

	
	
	public List getSites() {
		return this.sites;
	}


	public void setSites(List sites) {
		this.sites = sites;
	}


	public int getMood() {
		return mood;
	}


	public void setMood(int mood) {
		this.mood = mood;
	}




	
		
	}
	


