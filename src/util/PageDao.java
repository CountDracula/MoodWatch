package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.Page;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import controller.Server;

public class PageDao {

	
	public PageDao()
	{
		
	}


public void addPage(String pageToAdd, String siteToAdd)
{
	Session session = DBManager.getSessionFactory().openSession();

session.beginTransaction();
// persists two new Contact objects
Date timeNow = new Date();


try{
Page page = new Page(pageToAdd, siteToAdd);
page.setPage(pageToAdd);
page.getSites().add(siteToAdd);

page.setTimestamp(timeNow);


session.persist(page);
System.out.println("Added page: " + page.getPage());
        session.getTransaction().commit();
}
catch (HibernateException e)
{
//ADD something
}
finally{
session.close();



}
}
	
/// Retrieve everything (i.e website and it's values + list of it's sites)
/// Currently allows duplicate entries for pages because they are stored as objects.. Might need to re-implement this (like with threadsToClient) but how to access them in PrimeFAces datatable then?


// Maybe HashMap(String, List<page,string>) .... OR edit HQL... 
public Map<Page, List<String>> getAll()
{
	Map<Page, List<String>> allSites = new HashMap();
	Session session = DBManager.getSessionFactory().openSession();
	
	// use name of java class i.e. Page, not name of table i.e. Pages...
String hql = "SELECT * from pages JOIN sites ON pages.page_id = sites.Page_page_id";

List<Object[]> rows = (List<Object[]>) session.createSQLQuery(hql).list();
for (Object[] o : rows)
{
	
	Page page = new Page();
	
	if (allSites.get(page.getPage()) == null)
	{

	allSites.put(new Page(), new ArrayList<String>());
	page.setMood(((Integer) o[1]));
	page.setPage(((String) o[2]));
	Date time = (Date) o[3];	
	page.setTimestamp(time);
	allSites.get(page).add((String) o[5]);
	}

	else
	{
	allSites.get(page.getPage()).add((String) o[5]);
	}
	
	page.setSites(allSites.get(page));

//o[0] = page_id
//o[1] = mood
//o[2] = pagename
//o[3] = timestamp
//o[4] = page.id (in sites table)
//o[5] = sites (list of sites)
	
}

		
	
	return allSites;
}

//// Converting HashMap to ArrayList so PrimeFaces can display it in datatable
public ArrayList<Entry<Page, List<String>>> getPSF()
{
	 Set<Entry<Page, List<String>>> resultSet = getAll().entrySet();
	
return new ArrayList<Map.Entry<Page, List<String>>>(resultSet);
}

/// Use this to add all the websites to clients on startup
public Collection<String> pagesToClient()
{
	Collection<String> pageNames = new ArrayList<String>();
	List<Page> pages = getPages();
	
	for (Page page : pages)
	{
		pageNames.add(page.getPage());
	}
	return pageNames;
}

/// Used to present all pages on the xhtml sites (admin add thread site)
public List<Page> getPages()

{

	List<Page> pages = null;
	Session session = DBManager.getSessionFactory().openSession();
	
	// use name of java class i.e. Page, not name of table i.e. Pages...
	String hql = "from Page";
	Query q = session.createQuery(hql);
	
	
	pages =  q.list();
	
	for (Page page : pages)
	{
		
		System.out.println("Page: " + page.getPage() + " " +" timestamp: " + page.getTimestamp());
	}
	
		
	
	return pages;
	
}
	
public Map<String, List<String>> threadsToClient()
{
	Map<String, List<String>> allThreads = new HashMap();
	Session session = DBManager.getSessionFactory().openSession();
	
	// use name of java class i.e. Page, not name of table i.e. Pages...
String hql = "SELECT pagename, sites from pages JOIN sites ON pages.page_id = sites.Page_page_id";

List<Object[]> rows = (List<Object[]>) session.createSQLQuery(hql).list();
for (Object[] o : rows)
{

	String page = (String) o[0];
	String siteToAdd = (String) o[1];
	
	if (allThreads.get(page) == null)
	{
	allThreads.put(page, new ArrayList<String>());
	
	allThreads.get(page).add(siteToAdd);
	}
	
	else
	{
		allThreads.get(page).add(siteToAdd);
	}
	


//o[0] = pagename
//o[1] = sites

		
}
	
	return allThreads;
	
}

}
