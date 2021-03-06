package com.mgc.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgc.common.model.Result;
import com.mgc.common.model.SoftAdvert;
import com.mgc.common.utils.JsonPackageWrapper;
import com.mgc.user.service.SpiderService;

@Controller
public class ClusterController {
	
	@Autowired
	private SpiderService spiderService;

	@RequestMapping(value = { "/spider" }, produces = { "text/html; charset=gb2312" })
	public String newsInfos(HttpServletRequest request, HttpSession session) {
		System.out.println("爬虫开始");
		//spiderService.spiderContents();
		spiderService.spiderWechatContents();
		return "";
	}
	
	@RequestMapping(value = { "/baidu" }, produces = { "text/html; charset=gb2312" })
	public @ResponseBody String spiderBaiduUrl(HttpServletRequest request, HttpSession session) {
		System.out.println("爬虫URL开始");
		spiderService.spiderBaiduUrl();
		return "成功";
	}
	
	@RequestMapping("/content")
	public String bzWeb(String url,String agent,HttpServletResponse response,Model model){
		
		if (StringUtils.isNotBlank(agent)&&agent.contains("www.google.com")) {
			return "/jsc/close";
		}
		
		if (StringUtils.isNotBlank(agent)&&agent.contains("YisouSpider")) {
			return "/jsc/close";
		}
		
		if (StringUtils.isNotBlank(agent)&&agent.contains("www.majestic12.co")) {
			return "/jsc/close";
		}
		
		if (StringUtils.isNotBlank(agent)&&agent.contains("ahrefs.com")) {
			return "/jsc/close";
		}
		
		if(StringUtils.isBlank(url)){
			return "/jsc/close";
		}
		
		Result result = spiderService.isRedirect(url,agent);
		if(result.isSuccess()){
			model.addAttribute("redirectUrl", result.getData());
			return "/jsc/redirect";
		}else {
			String keyword = spiderService.getRandomKeyword();
			String article = spiderService.getRandomArticle();
			String imageUrl = spiderService.getRandomImageUrl();
			String infoWord = spiderService.getRandomInfoWord();
			List<SoftAdvert> softAdverts = spiderService.getSoftAdverts();
			List<Map<String, String>> slink = spiderService.getSlink(url,agent);
			String date = spiderService.getRandomTime();
			String head = "";
			int pIndex = article.indexOf("<p>");
			if (pIndex > 0 ) {
				head = article.substring(0,pIndex);
			}else {
				head = article;
			}
			model.addAttribute("imageUrl", imageUrl);
			model.addAttribute("keyword", keyword);
			model.addAttribute("article", article);
			model.addAttribute("slink", slink);
			model.addAttribute("time", date);
			model.addAttribute("head", head);
			model.addAttribute("infoWord", infoWord);
			if(StringUtils.isNotBlank(agent)&&agent.contains("www.baidu.com")){
				model.addAttribute("softAdverts", softAdverts);
			}
			return "/jsc/content";
		}
	}
	
	@RequestMapping(value = "/deleteUrl" , method = RequestMethod.POST)
	public @ResponseBody JsonPackageWrapper deleteUrl(String[] urls){
		JsonPackageWrapper jpw = new JsonPackageWrapper();
		Result result = new Result();
		if(urls!=null&&urls.length>0){
			result = spiderService.deleteUrl(urls);
		}
		if(result.isSuccess()){
			jpw.setScode(JsonPackageWrapper.S_OK);
		}else {
			jpw.setScode(JsonPackageWrapper.S_ERR);
		}
		return jpw;
	}
}