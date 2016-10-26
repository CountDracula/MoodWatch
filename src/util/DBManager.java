package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import model.Page;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.fasterxml.classmate.AnnotationConfiguration;

 
/**
 * A sample program that demonstrates how to perform simple CRUD operations
 * with Hibernate framework.
 * @author www.codejava.net
 *
 */
public class DBManager {
   
private Session session;	
private Configuration configuration;
private SessionFactory factory;
	
/// USED TO UPDATE DATABASE BY ADMINISTRATOR!
//TODO check if session / factory is threadsafe
	public DBManager() {
		
		configuration = new Configuration();
    	configuration.configure("hibernate.cfg.xml");
   		factory = configuration.buildSessionFactory();
    	
	}


    	
    public void addPage(String pageToAdd, String siteToAdd)
    
    
    {
    	session = factory.openSession();
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