package com.mgc.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public final class RequestUtil
{
  public static boolean isJsonRequest(HttpServletRequest request)
  {
    return (StringUtils.equals("json", request.getParameter("format"))) || 
      (StringUtils.equals("jsonp", request.getParameter("format")));
  }

  public static String outputMsg(String code, String msg)
  {
    StringBuffer buffer = new StringBuffer("{code:'")
      .append(code)
      .append("',msg:'")
      .append(msg)
      .append("'}");
    return buffer.toString();
  }
  
  public static Long getUserId(HttpSession session){
	  Long userId = null;
	  Object o = (Long) session.getAttribute(Constants.USER_ID);
	  if(o != null){
		  userId = (Long) o;
	  }
	  return userId;
  }
}