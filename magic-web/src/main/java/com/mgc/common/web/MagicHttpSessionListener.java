package com.mgc.common.web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.mgc.common.model.UserLoginVo;
import com.mgc.common.utils.Constants;

public class MagicHttpSessionListener implements HttpSessionListener{

	private static final Logger logger = Logger.getLogger(MagicHttpSessionListener.class);
	
	
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		logger.info("session创建"+session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		logger.info("session被销毁"+session.getId());
		Long userId = (Long) session.getAttribute("magicUserId");
		if(Constants.sessionMap.containsKey(session.getId())){
			logger.debug("删除当前session保存的上次文件信息");
			Constants.sessionMap.remove(session.getId());
		}
		
		if(Constants.userLoginVersionMap.containsKey(userId)){
			UserLoginVo vo = Constants.userLoginVersionMap.get(userId);
			int cnt = vo.getLoginCout();
			if(cnt == 1){
				logger.debug("删除当前session保存的用户登录信息");
				Constants.userLoginVersionMap.remove(userId);
			}else {
				logger.debug("更新当前session保存的用户登录信息");
				vo.setLoginCout(cnt-1);
			}
		}
		
	}
	
}
