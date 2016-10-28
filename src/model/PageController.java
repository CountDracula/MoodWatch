package model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;





import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;



import testers.PoolTester;
import util.DBManager;
import util.PageDao;
import controller.Server;





@ManagedBean (name ="pagecontroller")
public class PageController {
	
private String page;
private String site;



//private Date timestamp;
		
	 public PageController() {
		
			
		}
	 
	 @PostConstruct
	 public void initChoices()
	 {
		
	 }

	
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}


	//public Date getTimestamp() {
	//	return timestamp;
	//}

	//public void setTimestamp(Date timestamp) {
	//	this.timestamp = timestamp;
	//}

	public void submit()
	{
		 HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        String pageToAdd = request.getParameter("testForm:page");
	        //note the difference when getting the parameter
	        String siteToAdd = request.getParameter("testForm:site");
	        
	      // For debugging only  
	      System.out.println(pageToAdd);
	      System.out.println(siteToAdd);
	     
	    
	      PageDao dao = new PageDao();
	      dao.addPage(pageToAdd, siteToAdd);
	      
	      
	      
	        

	        
	}
	

}
	 
	 
	
	


