package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter{

	@Override
	public void destroy() {
	
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        
        if (session.getAttribute("username")!=null)
        {
        	chain.doFilter(request, response);
        }
        else
        {
        	response.sendRedirect(request.getContextPath() + "/adminLogin.xhtml");
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
