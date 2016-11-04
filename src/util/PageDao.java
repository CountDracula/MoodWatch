package util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.annotations.Synchronize;

import controller.Server;


/// Contains various methods to retrieve records from DB 
public class PageDao {

	
	public PageDao()
	{
		
	}

public ArrayList<Page> pagesFromDB()
{
	Session session = DBManager.getSessionFactory().openSession();
	session.beginTransaction();
	SQLQuery query = session.createSQLQuery("SELECT * from pages JOIN sites ON pages.page_id = sites.page_id");
	query.addEntity(Page.class);
	
ArrayList <Page> pages = (ArrayList<Page>) query.list();
session.close();
return pages;
}




public void updatePage(String pageToAdd, int mood, String timeStamp) throws ParseException
{
Session session = DBManager.getSessionFactory().openSession();
	
	SQLQuery query = session.createSQLQuery("SELECT * from pages JOIN sites ON pages.page_id = sites.page_id");
	query.addEntity(Page.class);
	ArrayList<Page>pages = (ArrayList<Page>) query.list();
	
	Map<String, Page> results = new HashMap();
	

	
	if (pages.size() !=0)
	{
	for (Page p : pages)
	{
		results.put(p.getPage(), p);
			}
	}

	if (results.containsKey(pageToAdd))
	{
		session.beginTransaction();
		results.get(pageToAdd).setMood(mood);
		results.get(pageToAdd).setTimestamp(timeStamp);
		session.saveOrUpdate(results.get(pageToAdd));
		session.getTransaction().commit();

	}

	session.close();
	
}

public void addPage(String pageToAdd, String siteToAdd)
{
	
	Session session = DBManager.getSessionFactory().openSession();
	
	SQLQuery query = session.createSQLQuery("SELECT * from pages JOIN sites ON pages.page_id = sites.page_id");
	query.addEntity(Page.class);
	
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM");
	
	Date date = new Date();
	String timeNow = format.format(date);

ArrayList<Page>pages = (ArrayList<Page>) query.list();

if (pages.size()==0)
{
	session.beginTransaction();
	Page page = new Page();
	page.setPage(pageToAdd);
	page.setMood(0);
	page.setTimestamp(timeNow);
	ArrayList<String> sites = new ArrayList();
	page.setSites(sites);
	page.getSites().add(siteToAdd);
	session.persist(page);
	session.getTransaction().commit();

	
}


Map<String, Page> results = new HashMap();
if (pages.size() !=0)
{
for (Page p : pages)
{
	results.put(p.getPage(), p);
}
}

if (results.containsKey(pageToAdd))
{
	session.beginTransaction();
	results.get(pageToAdd).getSites().add(siteToAdd);
	session.saveOrUpdate(results.get(pageToAdd));
	session.getTransaction().commit();

}
else if (pages.size()>0)
{
	session.beginTransaction();
	Page page = new Page();
	page.setPage(pageToAdd);
	page.setMood(0);
	page.setTimestamp(timeNow);
	ArrayList<String> sites = new ArrayList();
	page.setSites(sites);
	page.getSites().add(siteToAdd);
	session.persist(page);
	session.getTransaction().commit();

}
	


try{
        
}
catch (HibernateException e)
{
//ADD something
}
finally{
session.close();



}
}



public Map<Page, List<String>> getAll()
{
	Map<Page, List<String>> allSites = new HashMap();
	Session session = DBManager.getSessionFactory().openSession();
	session.beginTransaction();
	SQLQuery query = session.createSQLQuery("SELECT * from pages JOIN sites ON pages.page_id = sites.page_id");
	query.addEntity(Page.class);
	
ArrayList <Page> pages = (ArrayList<Page>) query.list();
for (Page p : pages)
{
	allSites.put(p, p.getSites());
	session.saveOrUpdate(p);

}

session.close();

return allSites;
}



//// Converting HashMap to ArrayList so PrimeFaces can display it in datatable
public ArrayList<Entry<Page, List<String>>> getPSF()
{
	 Set<Entry<Page, List<String>>> resultSet = getAll().entrySet();
	
return new ArrayList<Map.Entry<Page, List<String>>>(resultSet);
}

/// Use this to add all the websites to clients on startup
public Collection<String> sitesToClient()
{
	
ArrayList <Page> pages = pagesFromDB();
ArrayList<String> allSites = new ArrayList();
	
	for (Page page : pages)
	{
		allSites.add(page.getPage());
	}
	return allSites;
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

	
ArrayList <Page> pages =  pagesFromDB();
for (Page p : pages)
{
	allThreads.put(p.getPage(), p.getSites());

}
	
	return allThreads;
	
}

}