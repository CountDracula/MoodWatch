package util;

import java.util.Date;
import java.util.List;

import model.Page;
import model.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class LoginDAO {

	
	public void addUser(String username, String password)
	{
	
	Session session = DBManager.getSessionFactory().openSession();

	session.beginTransaction();
	
	try{
	User user = new User();
	user.setUsername(username);
	user.setPassword(password);


	session.persist(user);
	System.out.println("Added user: " + user.getUsername());
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
	
	
	public boolean validate(String user, String password)
	{
	Session session = DBManager.getSessionFactory().openSession();

	session.beginTransaction();
	
	String hql = "from User u where u.username=:user and u.password=:password";
	Query q = session.createQuery(hql);
	q.setString("user", user);
	q.setString("password", password);
	List<User> list = q.list();
	

	if(list.size() > 0)
	{
		session.close();
		return true;
	}
	else
	{
		return false;
	}
	
	

	
	
}
	}
