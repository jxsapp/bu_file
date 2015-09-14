package org.bu.core.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class HeaderParser
{
	public static String getToken(HttpServletRequest request){
		String token = null;
		Cookie[] cookies=request.getCookies(); 
		if(cookies != null) {
			for (Cookie c : cookies)     
		    {    
		       if(c.getName().equalsIgnoreCase("token"))    
		       {    
		          token = c.getValue();    
		       } 
		    }
		}
		return token;
	}
}
