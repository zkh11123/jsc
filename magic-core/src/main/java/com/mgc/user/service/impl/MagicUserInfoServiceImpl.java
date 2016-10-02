package com.mgc.user.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mgc.common.model.JscInfos;
import com.mgc.common.utils.ConvertUtil;
import com.mgc.common.utils.DateFormatUtil;
import com.mgc.common.utils.HttpClientFactory; 
import com.mgc.user.service.MagicUserInfoService;

@Service("magicUserInfoService")
public class MagicUserInfoServiceImpl implements MagicUserInfoService {
	private static Logger logger = Logger
			.getLogger(MagicUserInfoServiceImpl.class);
	
	@Value("${url.bind}")
	private String bindUrl;
	
	@Value("${url.target}")
	private String targetUrl;
	
	private List<String> infoList = new ArrayList<String>();
	
	private Map<String, String> sysnMap = new HashMap<String, String>();
	
	@Override
	public String getContent(String targetUrl, HttpServletRequest request) {
		StringBuffer content = new StringBuffer();
		String contentStr = "";
		try {
			Header[] headers = {
					new BasicHeader("Accept", request.getHeader("Accept")),
					new BasicHeader("Accept-Encoding",
							request.getHeader("Accept-Encoding")),
					new BasicHeader("Accept-Language",
							request.getHeader("Accept-Language")),
					new BasicHeader("Connection",
							request.getHeader("Connection")),
					new BasicHeader("User-Agent",
							request.getHeader("User-Agent")),
					new BasicHeader("Host", "www.qq.com"),
					new BasicHeader("Referer", targetUrl) };

			content = HttpClientFactory.getContent(targetUrl, headers);
			HttpClientFactory.injectCode(content);
			contentStr = content.toString();
			contentStr = contentStr.replaceAll("onclick=\"userLogin\\(\\);\"",
					"");
		} catch (Exception e) {
			logger.error("获取请求失败：" + targetUrl + " :", e);
		}
		return contentStr;
	}
	
	@Override
	public String getContentNews(String targetUrl) {
		StringBuffer content = new StringBuffer();
		String contentStr = "";
		try {
			Header[] headers = {
					new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"),
					new BasicHeader("Accept-Encoding","gzip, deflate, sdch"),
					new BasicHeader("Accept-Language","zh-CN,zh;q=0.8,und;q=0.6"),
					new BasicHeader("Connection","keep-alive"),
					new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36"),
					};

			content = HttpClientFactory.getContent(targetUrl, headers);
			contentStr = content.toString();
		} catch (Exception e) {
			logger.error("获取请求失败：" + targetUrl + " :", e);
		}
		return contentStr;
	}
	
	@Override
	public String getContentByDocument(Document document,String url,JscInfos jscInfos){
		Document doc = document.clone();
		String keyWord = this.getRandonInfos();
		String keyWords = keyWord+":";
		Element element = null;
		String link = null;
		String script = null;
		String saveUrl = jscInfos.getSaveUrl();
		if(!url.endsWith("/")){
			saveUrl+="/";
		}
		if(url.startsWith("http://news.qq.com")){
			tencentNewsDeal(jscInfos, keyWord, keyWords, doc);
			element = doc.getElementById("Cnt-Main-Article-QQ");
			link = "<link rel=\"stylesheet\" type=\"text/css\" href=\""+saveUrl+"qq/my.css\">";
			script = "<script type=\"text/javascript\" src=\""+saveUrl+"qq/my.js\"></script>";
			
			//改变时间
			List<Element> tiElements = doc.getElementsByClass("article-time");
			if(CollectionUtils.isNotEmpty(tiElements)){
				Element timeElement = tiElements.get(0);
				timeElement.html(DateFormatUtil.getRandomDateString());
			}
			
		}else if(url.startsWith("http://news.163.com")){
			wangyiNewsDeal(jscInfos, keyWord, keyWords, doc);
			List<Element> elements = doc.getElementsByClass("post_text");
			if(CollectionUtils.isNotEmpty(elements)){
				element = elements.get(0); 
			}
			//---------------------------------------------------------
			link = "<link rel=\"stylesheet\" type=\"text/css\" href=\""+saveUrl+"qq/my.css\">";
			script = "<script type=\"text/javascript\" src=\""+saveUrl+"qq/my.js\"></script>";
			//改变时间
			List<Element> tiElements = doc.select("div.post_time_source");
			if(CollectionUtils.isNotEmpty(tiElements)){
				Element timeElement = tiElements.get(0);
				timeElement.html(DateFormatUtil.getRandomDateString()+"　来源: ");
			}
		}else if (url.startsWith("http://www.radiotj.com")) {
			
		}
		if(element!=null){
			List<Element> textElements = element.getElementsByTag("p");
			Element textElement = textElements.get(0);
			String text = textElement.html();
			text = "要想在"+keyWord+"怎么办？请记住："+"【扣Q扣Q—W微X信】"
					+jscInfos.getTencentNumber()+"★【电话】"
					+jscInfos.getPhoneNumber()+"████████████货到付款★快速取证★不付定金★见货付款★"
					+ text;
			textElement.html(text);
		}
		
		List<Element> headElements = doc.getElementsByTag("head");
		Element headElement = headElements.get(0);
		headElement.append(link);
		String baiduSendjs = "<script>(function(){var bp = document.createElement('script');var curProtocol = window.location.protocol.split(':')[0];if (curProtocol === 'https') {bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';        else {bp.src = 'http://push.zhanzhang.baidu.com/push.js';}var s = document.getElementsByTagName(\"script\")[0];s.parentNode.insertBefore(bp, s);})();</script>";
		headElement.append(baiduSendjs);
		headElement.append(script);
		return doc.html().replaceAll("<!--.*?-->", "");
	}
	
	@Override
	public Document getContentNewsDetail(String targetUrl, HttpServletRequest request,JscInfos jscInfos) {
		
		try {
			Header[] headers = {
					new BasicHeader("Accept", request.getHeader("Accept")),
					new BasicHeader("Accept-Encoding",
							request.getHeader("Accept-Encoding")),
					new BasicHeader("Accept-Language",
							request.getHeader("Accept-Language")),
					new BasicHeader("Connection",
							request.getHeader("Connection")),
					new BasicHeader("User-Agent",
							request.getHeader("User-Agent")),
					new BasicHeader("Referer", targetUrl) };
			Document doc = null;
			if (targetUrl.startsWith("http://news.qq.com")) {
				//Cnt-Main-Article-QQ 这个id下面的都是内容
				doc = HttpClientFactory.getContentDocument(targetUrl, headers,"gbk");
				writeCssAndJavaScript(doc);
				
				Element element = doc.getElementById("Cnt-Main-Article-QQ");
				if(element!=null){
					getWeiyuanchuang(element);
					return doc;
				}
				
			} else if(targetUrl.startsWith("http://news.163.com")){
				//post_text class
				doc = HttpClientFactory.getContentDocument(targetUrl, headers,"gbk");
				writeCssAndJavaScript(doc);
				
				List<Element> elements = doc.getElementsByClass("post_text");
				if(CollectionUtils.isNotEmpty(elements)){
					Element element = elements.get(0); 
					getWeiyuanchuang(element);
					return doc;
				}
			}
		} catch (Exception e) {
			logger.error("获取请求失败：" + targetUrl + " :", e);
		}
		return null;
	}

	private void writeCssAndJavaScript(Document doc)
			throws FileNotFoundException, IOException {
		List<Element> scriptElements = doc.select("script");
		
		File jsfile = new File("f://target/qq/my.js");
		FileOutputStream jsfos = new FileOutputStream(jsfile);
		for (Element element : scriptElements) {
			String script = element.html();
			if(StringUtils.isNotBlank(script)){
				jsfos.write(script.getBytes());
				element.remove();
			}
		}
		jsfos.flush();
		jsfos.close();
		
		List<Element> styleElements = doc.select("style");
		File cssfile = new File("f://target/qq/my.css");
		FileOutputStream cssfos = new FileOutputStream(cssfile);
		for (Element element : styleElements) {
			String style = element.html();
			if(StringUtils.isNotBlank(style)){
				cssfos.write(style.getBytes());
			}
			element.remove();
		}
		cssfos.flush();
		cssfos.close();
		
	}

	private void tencentNewsDeal(JscInfos jscInfos, String keyWord,String keyWords,Document doc) {
		//标题
		List<Element> titleElements = doc.getElementsByTag("title");
		if(CollectionUtils.isNotEmpty(titleElements)){
			Element titleElement = titleElements.get(0);
			String title = titleElement.text();
			int i = title.indexOf("新闻_腾讯网");
			title = title.substring(0,i)+"_"+keyWord+"_新闻_腾讯网";
			titleElement.text(keyWord+"_"+title);
		}
		//文章标题
		List<Element> headElements =  doc.getElementsByTag("h1");
		if(CollectionUtils.isNotEmpty(headElements)){
			Element headElement = headElements.get(0);
			setHeadText(keyWords, headElement);
		}
		//description
		setDescText(jscInfos, doc ,keyWord);
		//kewords
		setKeywordsText(keyWord, doc);
		
		//处理列表
		Element allistElement = doc.getElementById("sideBars");
		Element listElement = doc.getElementById("jinghuatuijian");
		if(listElement != null && listElement !=null){
			List<Element> hrefElements = listElement.getElementsByTag("a");
			for (Element hrefElement : hrefElements) {
				hrefElement.attr("href", this.getRadomUrl(jscInfos));
			}
			allistElement.children().remove();
			allistElement.appendChild(listElement);
		}
		
		Element article = doc.getElementById("hotReadList");
		List<Element> alList = article.getElementsByTag("a");
		for (Element element : alList) {
			element.attr("href", this.getRadomUrl(jscInfos));
		}
		
	}

	private void wangyiNewsDeal(JscInfos jscInfos, String keyWord,String keyWords, Document doc) {
		
		List<Element> titleElements = doc.getElementsByTag("title");
		if(CollectionUtils.isNotEmpty(titleElements)){
			Element titleElement = titleElements.get(0);
			String title = titleElement.text();
			int i = title.indexOf("网易新闻");
			title = title.substring(0,i)+"_"+keyWord+"_网易新闻";
			titleElement.text(keyWords+title);
		}
		
		List<Element> headElements = doc.getElementsByTag("h1");
		if(CollectionUtils.isNotEmpty(headElements)){
			Element headElement = headElements.get(0);
			setHeadText(keyWords, headElement);
		}
		//description
		setDescText(jscInfos, doc ,keyWord);
		//keywords
		setKeywordsText(keyWord, doc);
		
		//处理列表
		Element allistElement = doc.getElementById("epContentRight");
		Element headElement = doc.getElementById("side_xwtj3_head");
		Element listElement = doc.getElementById("side_xwtj3_body");
		Element listElement1 = listElement.clone();
		Element listElement2 = listElement.clone();
		Element listElement3 = listElement.clone();
		Element listElement4 = listElement.clone();
		Element listElement5 = listElement.clone();
		
		if(listElement != null && listElement !=null && headElement !=null){
			List<Element> hrefElements = listElement.getElementsByTag("a");
			for (Element hrefElement : hrefElements) {
				hrefElement.attr("href", this.getRadomUrl(jscInfos));
			}
			
			List<Element> hrefElements1 = listElement1.getElementsByTag("a");
			for (Element hrefElement : hrefElements1) {
				hrefElement.attr("href", this.getRadomUrl(jscInfos));
			}
			
			List<Element> hrefElements2 = listElement2.getElementsByTag("a");
			for (Element hrefElement : hrefElements2) {
				hrefElement.attr("href", this.getRadomUrl(jscInfos));
			}
			
			List<Element> hrefElements3 = listElement3.getElementsByTag("a");
			for (Element hrefElement : hrefElements3) {
				hrefElement.attr("href", this.getRadomUrl(jscInfos));
			}
			
			List<Element> hrefElements4 = listElement4.getElementsByTag("a");
			for (Element hrefElement : hrefElements4) {
				hrefElement.attr("href", this.getRadomUrl(jscInfos));
			}
			
			List<Element> hrefElements5 = listElement5.getElementsByTag("a");
			for (Element hrefElement : hrefElements5) {
				hrefElement.attr("href", this.getRadomUrl(jscInfos));
			}
			
			allistElement.children().remove();
			allistElement.appendChild(headElement);
			allistElement.appendChild(listElement);
			allistElement.appendChild(listElement1);
			allistElement.appendChild(listElement2);
			allistElement.appendChild(listElement3);
			allistElement.appendChild(listElement4);
			allistElement.appendChild(listElement5);
		}
		
		List<Element> alList = doc.select("a[href*='.163.com']");
		for (Element element : alList) {
			element.attr("href", this.getRadomUrl(jscInfos));
		}
	}

	@Override
	public void getWeiyuanchuang(Element element) {
		String html = getNewsDetaiOriginal(element.html());
		element.html(html);
	}
	
	//修改标题信息
	private void setHeadText(String keyWords, Element headElement) {
		String head = headElement.text();
		headElement.text(keyWords+head);
	}
	
	private void setKeywordsText(String keyWord, Document doc) {
		Element kewordsElement = doc.getElementsByAttributeValue("name", "keywords").get(0);
		String oldkeyword = kewordsElement.attr("content");
		kewordsElement.attr("content",keyWord+","+oldkeyword);
	}

	private void setDescText(JscInfos jscInfos, Document doc,String keyWord) {
		Element descElement = doc.getElementsByAttributeValue("name", "Description").get(0);
		descElement.attr("content",keyWord+"【扣Q—W微X信】"
					+jscInfos.getTencentNumber()+"★【电话】"
					+jscInfos.getPhoneNumber()+"████████████货到付款★快速取证★不付定金★见货付款★");
	}
	
	@Override
	public String getNewsDetaiOriginal(String source){
		source = ConvertUtil.replaceString(source, sysnMap);
		return source;
	}

	@Override
	public String changeUrl(HttpServletRequest request) {
		StringBuffer content = new StringBuffer();
		StringBuffer url = request.getRequestURL();
		String realUrl = "";
		if (url.toString().startsWith("/"))
			realUrl = realUrl + url.toString();
		else
			realUrl = url.toString().replaceAll(bindUrl,targetUrl);
		try {
			Header[] headers = {
					new BasicHeader("Accept", request.getHeader("Accept")),
					new BasicHeader("Accept-Encoding",
							request.getHeader("Accept-Encoding")),
					new BasicHeader("Accept-Language",
							request.getHeader("Accept-Language")),
					new BasicHeader("Connection",
							request.getHeader("Connection")),
					new BasicHeader("User-Agent",
							request.getHeader("User-Agent")),
					new BasicHeader("Host", "www.qq.com"),
					new BasicHeader("Referer", targetUrl) };

			content = HttpClientFactory.getContent(realUrl, headers);
		} catch (Exception e) {
			logger.error("获取请求失败：" + url + " :", e);
		}

		return content.toString();
	}
	
	@Override
	public String getRandonInfos(){
		Double r = Math.random();
		int size = infoList.size();
		int randon = (int)(r*(size));
		return infoList.get(randon);
	}
	
	@Override
	public String getRadomUrl(JscInfos jscInfos){
		Double r = Math.random();
		int size = jscInfos.getCreateNum();
		int randon = (int)(1+r*(size));
		String randomUrl = "";
		String url = jscInfos.getSaveUrl();
		if(url.endsWith("/")){
			randomUrl += url+jscInfos.getFilePrex()+randon+".html";
		}else {
			randomUrl += url+"/"+jscInfos.getFilePrex()+randon+".html";
		}
		return randomUrl;
	}
	
}