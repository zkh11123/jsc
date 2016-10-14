package com.mgc.user.service;

import java.util.List;
import java.util.Map;

import com.mgc.common.model.Result;
import com.mgc.common.model.SoftAdvert;

public interface SpiderService {
	
	public void spiderContents();

	public void spiderBaiduUrl();
	
	public String getRandomKeyword();
	
	public String getRandomArticle();
	
	public List<Map<String, String>> getSlink(String url,String agent);
	
	public String getRandomTime();
	
	public Result isRedirect(String url,String agent);

	public String getRandomImageUrl();
	
	public String getRandomInfoWord();

	public void spiderWechatContents();
	
	public List<SoftAdvert> getSoftAdverts();

	public Result deleteUrl(String[] urls);

}
