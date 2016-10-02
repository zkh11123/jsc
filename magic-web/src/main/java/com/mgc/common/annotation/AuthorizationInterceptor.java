package com.mgc.common.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mgc.common.model.UserLoginVo;
import com.mgc.common.utils.Constants;

public class AuthorizationInterceptor implements HandlerInterceptor {
	
	private static Logger logger = Logger.getLogger(AuthorizationInterceptor.class);
	
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if ((handler == null)
				|| (!HandlerMethod.class.isAssignableFrom(handler.getClass()))) {
			return true;
		}
		HandlerMethod method = (HandlerMethod) handler;
		AuthLevel level = getAuthLevelFromHandler(method);
		StringBuffer url = request.getRequestURL();
		if (level == AuthLevel.NONE) {
			logger.debug("不需要验证权限的请求："+url);
			return true;
		}
		HttpSession session = request.getSession();
		if (level == AuthLevel.STRICT) {
			Object userNameObject = session.getAttribute("MagicUser");
			Object versionObject = session.getAttribute("magicUserVersion");
			Object userIdObject = session.getAttribute("magicUserId");
			int version = 0;
			Long userId = 0l;
			if (userNameObject != null && versionObject !=null && userIdObject!=null) {
				version = (Integer) versionObject;
				userId = (Long) userIdObject;
				UserLoginVo vo = Constants.userLoginVersionMap.get(userId);
				if(vo != null){
					if(version == vo.getVersion()){
						logger.debug("验证通过的请求："+url);
						return true;
					}
				}
			}
		}
		logger.debug("验证失败并重定向的请求："+url);
		session.invalidate();
		response.sendRedirect("/user/reload");
		response.flushBuffer();
		return false;
	}

	private AuthLevel getAuthLevelFromHandler(HandlerMethod method) {
		AuthRequired ar = (AuthRequired) method
				.getMethodAnnotation(AuthRequired.class);
		if (ar == null) {
			ar = (AuthRequired) method.getBeanType().getAnnotation(
					AuthRequired.class);
		}
		return ar == null ? AuthLevel.NONE : ar.value();
	}

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
}