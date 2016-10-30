package util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean (name="logincontroller")
@SessionScoped
public class Login {
	
	private String password;
	private String user;
	private String message;
	
	public String validateLogin()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		
		boolean validLogin =  new LoginDAO().validate(user, password);
		
		if (validLogin)
		{
			 
		     HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		     session.setAttribute("username", user);
		     return "adminindex";
		}
		else
		{
			context.getCurrentInstance().addMessage("loginForm:error", new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect username and password", "Wrong username / password, please check your input."));
		
			return "adminLogin";
		}
	}
	
	public String logOut()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		     HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		 
		     session.invalidate();
		     user = null;
		     password = null;
		     return "index.xhtml";
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
