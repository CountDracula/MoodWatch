package util;

import java.util.Date;

import model.Page;

import org.hibernate.HibernateException;
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
	

	
}
