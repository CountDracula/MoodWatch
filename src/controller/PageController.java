package controller;

import java.text.ParseException;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;





import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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

import model.Page;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;












import testers.MainController;
import util.DBManager;
import util.PageDao;





@ManagedBean (name ="pagecontroller")
public class PageController {
	
private String page;
private String site;
private Date timestamp;

		
	 public PageController() {
		
			
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


	public Date getTimestamp() {
		return getTimestamp();
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void submit() throws ParseException
	{
		 HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        String pageToAdd = request.getParameter("testForm:page");
	        String siteToAdd = request.getParameter("testForm:site");
	        
	      // For debugging only  
	      System.out.println(pageToAdd);
	      System.out.println(siteToAdd);
	     
	    
	      PageDao dao = new PageDao();
	      dao.addPage(pageToAdd, siteToAdd);
  
	}
	
	//TODO: add clear() method and add to submit so input fields get reset after submitting data
	
	public List<Page> getPages()
	{
		PageDao dao = new PageDao();
		return dao.getPages();
	}
	
	public ArrayList<Entry<Page, List<String>>> getAll()
	{
		PageDao dao = new PageDao();
		return dao.getPSF();
	}
	
	


}
	 
	 
	
	


