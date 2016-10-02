package com.mgc.user.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mgc.common.model.Result;
import com.mgc.common.utils.DateFormatUtil;
import com.mgc.common.utils.HttpClientFactory;
import com.mgc.common.utils.StringFormatUtils;
import com.mgc.user.service.MagicUserInfoService;
import com.mgc.user.service.SpiderService;

@Service("spiderService")
public class SpiderServiceImpl implements SpiderService {

	@Autowired
	private MagicUserInfoService magicUserInfoService;

	private static Logger logger = Logger.getLogger(SpiderServiceImpl.class);
	
	private List<String> kwList = new ArrayList<String>();
	
	private List<String> urlList = new ArrayList<String>();
	
	private List<String> excludeUrlList = new ArrayList<String>();
	
	private Map<String,String> redirectUrls = new HashMap<String,String>();
	
	private long kwFileLastModified = 0l;
	
	private long redirectFileLastModified = 0l;
	
	private long urlsFileLastModified = 0l;
	
	@Value("${kwFile}")
	private String kwFile;
	
	@Value("${redirectFile}")
	private String redirectFile;
	
	@Value("${articlePath}")
	private String articlePath;
	
	@Value("${urlFile}")
	private String urlFile;

	@Override
	public void spiderContents() {

		String[] newsUrls = { 
				"http://news.sina.com.cn/china/",
				"http://news.sina.com.cn/world/",
				"http://news.sina.com.cn/society/",
				"http://mil.news.sina.com.cn/",
				"http://news.qq.com/",
				"http://news.qq.com/society_index.shtml",
				"http://mil.qq.com/mil_index.htm",
				"http://news.qq.com/world_index.shtml", 
				"http://cul.qq.com/",
				"http://news.163.com", 
				"http://news.163.com/domestic",
				"http://news.163.com/shehui", 
				"http://news.163.com/world",
				"http://war.163.com/" ,
				};
		String[] newsMatch = { 
				"\"http://news.sina.com.cn/[^(<>)]*?html\"" ,
				"\"http://news.sina.com.cn/[^(<>)]*?html\"" ,
				"\"http://news.sina.com.cn/[^(<>)]*?html\"" ,
				"\"http://mil.news.sina.com.cn/[^(<>)]*?html\"",
				"\"http://news\\.qq\\.com/a[^(<>)]*?htm\"",
				"\"http://news\\.qq\\.com/a[^(<>)]*?htm\"",
				"\"http://news\\.qq\\.com/a[^(<>)]*?htm\"",
				"\"http://news\\.qq\\.com/a[^(<>)]*?htm\"",
				"\"http://cul\\.qq\\.com/a[^(<>)]*?htm\"",
				"\"http://news\\.163\\.com/[^(<>)]*?html\"",
				"\"http://news\\.163\\.com/[^(<>)]*?html\"",
				"\"http://news\\.163\\.com/[^(<>)]*?html\"",
				"\"http://news\\.163\\.com/[^(<>)]*?html\"",
				"\"http://war\\.163\\.com/[^(<>)]*?html\"" ,
				};
		int num = 1;
		for (int i = 0; i < newsUrls.length; i++) {
			String content = magicUserInfoService.getContentNews(newsUrls[i]);
			String coStr = content.toString();
			Pattern pattern = Pattern.compile(newsMatch[i]);
			Matcher m = pattern.matcher(coStr);
			while (m.find()) {
				String targetUrl = m.group();
				targetUrl = targetUrl.substring(1, targetUrl.length() - 1);
				Header[] headers = {
						new BasicHeader("Accept",
								"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"),
						new BasicHeader("Accept-Encoding",
								"gzip, deflate, sdch"),
						new BasicHeader("Accept-Language",
								"zh-CN,zh;q=0.8,und;q=0.6"),
						new BasicHeader("Connection", "keep-alive"),
						new BasicHeader(
								"User-Agent",
								"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36") };
				Document doc = null;
				Element element = null;
				try {
					logger.info("当前url：" + targetUrl);
					if (targetUrl.startsWith("http://news.qq.com")) {
						// Cnt-Main-Article-QQ 这个id下面的都是内容
						doc = HttpClientFactory.getContentDocument(targetUrl,
								headers, "gbk");
						element = doc.getElementById("Cnt-Main-Article-QQ");
						/*
						 * if (element != null) {||
						 * magicUserInfoService.getWeiyuanchuang(element); }
						 */

					} else if (targetUrl.startsWith("http://cul.qq.com")) {
						// Cnt-Main-Article-QQ 这个id下面的都是内容
						doc = HttpClientFactory.getContentDocument(targetUrl,
								headers, "gbk");
						element = doc.getElementById("Cnt-Main-Article-QQ");
						/*
						 * if (element != null)
						 * {||targetUrl.startsWith("http://cul.qq.com")
						 * magicUserInfoService.getWeiyuanchuang(element); }
						 */

					} else if (targetUrl.startsWith("http://news.163.com")) {
						// post_text class
						doc = HttpClientFactory.getContentDocument(targetUrl,
								headers, "gbk");

						List<Element> elements = doc
								.getElementsByClass("post_text");
						if (CollectionUtils.isNotEmpty(elements)) {
							element = elements.get(0);
							/* magicUserInfoService.getWeiyuanchuang(element); */
						}
					} else if (targetUrl.startsWith("http://war.163.com")) {
						// post_text class
						doc = HttpClientFactory.getContentDocument(targetUrl,
								headers, "gbk");

						element = doc.getElementById("endText");
						
					} else if (targetUrl.startsWith("http://news.sina.com.cn")) {
						doc = HttpClientFactory.getContentDocument(targetUrl,
								headers, "utf-8");
						element = doc.getElementById("artibody");
					}

					if (null != element) {

						List<Element> elements = element.getElementsByTag("p");

						String title = doc.getElementsByTag("title").get(0)
								.text();
						int index = title.indexOf("_新闻_腾讯网");
						if (index == -1) {
							index = title.indexOf("_网易新闻");
						}
						if (index == -1) {
							index = title.indexOf("_新浪新闻");
						}
						if (index == -1) {
							System.out.println(title);
						} else {
							title = title.substring(0, index) + "\n";
						}

						String filePath1 = articlePath + num
								+ ".txt";
						logger.info("写的文件是：" + filePath1);
						File file1 = new File(filePath1);
						FileOutputStream fos1 = new FileOutputStream(file1);
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos1, "utf-8"));
						bw.write(title);
						for (Element element2 : elements) {
							String text = element2.text();
							if (StringUtils.isNotBlank(text)) {
								text = "<p>" + text + "</p>\n";
								bw.write(text);
							}
						}
						num++;
						bw.flush();
						bw.close();
						fos1.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void spiderBaiduUrl(){
		
		try{
			File file = new File("e://exclude.txt");
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis,"utf-8"));
			String line = "";
			excludeUrlList.clear();
			while((line = br.readLine())!=null){
				if(StringUtils.isNotBlank(line)){
					excludeUrlList.add(line.trim());
				}
			}
			br.close();
		}catch (Exception e) {
			logger.error("读取排除列表失败",e);
		}
		
		for(int i=1 ; i < 9 ; i++){
			try {
				SpiderThread spiderThread = new SpiderThread(i,excludeUrlList);
				Thread thread = new Thread(spiderThread);
				thread.start();
			} catch (Exception e) {
				logger.error("抓取失败",e);
			}
		}
	}
	
	@Override
	public String getRandomKeyword() {
			try {
				File file = new File(kwFile);
				Long timeStamp = file.lastModified();
				if(CollectionUtils.isEmpty(kwList) || timeStamp > kwFileLastModified){
					FileInputStream fis = new FileInputStream(file);
					BufferedReader br = new BufferedReader(new InputStreamReader(fis,"utf-8"));
					String line = "";
					kwList.clear();
					while((line = br.readLine())!=null){
						if(StringUtils.isNotBlank(line)){
							kwList.add(line.trim());
						}
					}
					kwFileLastModified = timeStamp;
					br.close();
					fis.close();
				}
			} catch (Exception e) {
				logger.error("读取kw失败：",e);
			}
		int size = kwList.size();
		double d = Math.random();
		int ran = (int)(size * d) ;
		return kwList.get(ran);
	}

	@Override
	public String getRandomArticle() {
		String article = "";
		try {
			File file = new File(articlePath);
			File[] files = file.listFiles();
			int size = files.length;
			double d = Math.random();
			int ran = (int)(size * d) ;
			File articleFile = files[ran];
			Long articleSize = articleFile.length();
			byte[] filecontent = new byte[articleSize.intValue()]; 
			FileInputStream in = new FileInputStream(articleFile);  
			in.read(filecontent);
			article = new String(filecontent,"utf-8");
			in.close();
		} catch (Exception e) {
			logger.error("获取文章失败:",e);
		}
		return article;
	}

	@Override
	public List<Map<String, String>> getSlink(String url,String agent) {
		List<Map<String, String>> urls = new ArrayList<Map<String, String>>();
		logger.info("请求的URL:"+url);
		logger.info("请求的agent:"+agent);
		if(StringUtils.isNotEmpty(url)){
			try {
				File file = new File(urlFile);
				if(CollectionUtils.isEmpty(urlList)){
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = "";
					urlList.clear();
					while(StringUtils.isNotBlank(line = br.readLine())){
						urlList.add(new String(line.getBytes(),"utf-8"));
					}
					br.close();
				}
				if(!urlList.contains(url)){
					urlList.add(url);
					FileWriter fw = new FileWriter(file,true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(url);
					bw.newLine();
					bw.flush();
					bw.close();
					fw.close();
				}
				
				for (int i = 0; i < 9; i++) {
					String str1 = StringFormatUtils.getRandomString(4);
					String str2 = StringFormatUtils.getRandomString(4);
					String kw = getRandomKeyword();
					String link = url+"?"+str1+"/"+str2+".html";
					Map<String, String> slinkMap = new HashMap<String, String>();
					slinkMap.put("kw", kw);
					slinkMap.put("link", link);
					urls.add(slinkMap);
				}
				
				for (int i = 9; i < 16; i++) {
					String str1 = StringFormatUtils.getRandomString(4);
					String str2 = StringFormatUtils.getRandomString(4);
					int size = urlList.size();
					double d = Math.random();
					int ran = (int)(size * d);
					String link = urlList.get(ran)+"?"+str1+"/"+str2+".html";
					String kw = getRandomKeyword();
					Map<String, String> slinkMap = new HashMap<String, String>();
					slinkMap.put("kw", kw);
					slinkMap.put("link", link);
					urls.add(slinkMap);
				}
				
			} catch (Exception e) {
				logger.error("读取urls失败",e);
			}
		}
		return urls;
	}
	
	private String getOneRandomLink(Map<String, String> urlMap){
		String randomUrl = "";
		File file = new File(urlFile);
		Long timeStamp = file.lastModified();
		if(timeStamp > urlsFileLastModified){
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				urlList.clear();
				while(StringUtils.isNotBlank(line = br.readLine())){
					urlList.add(new String(line.getBytes(),"utf-8"));
				}
				br.close();
				urlsFileLastModified = timeStamp;
			} catch (Exception e) {
				logger.error("读取文件出错：",e);
			}
		}
		String str1 = StringFormatUtils.getRandomString(4);
		String str2 = StringFormatUtils.getRandomString(4);
		int size = urlList.size();
		double d = Math.random();
		int ran = (int)(size * d);
		randomUrl = urlList.get(ran)+"?"+str1+"/"+str2+".html";
		while (isExsitMapKey(urlMap,randomUrl)) {
			double d1 = Math.random();
			int ran1 = (int)(size * d1);
			randomUrl = urlList.get(ran1)+"?"+str1+"/"+str2+".html";
		}
		return randomUrl;
	}
	
	private boolean isExsitMapKey(Map<String, String> urlMap,String randomUrl){
		
		for (String key : urlMap.keySet()) {
			if(randomUrl.startsWith(key)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String getRandomTime() {
		return DateFormatUtil.getRandomDateString();
	}

	@Override
	public Result isRedirect(String url,String agent) {
		Result result = new Result();
		try {
			File file = new File(redirectFile);
			Long timeStamp = file.lastModified();
			if(timeStamp > redirectFileLastModified){
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis,"utf-8"));
				String line = "";
				redirectUrls.clear();
				while((line = br.readLine())!=null){
					if(StringUtils.isNotBlank(line)){
						String[] mapStr = line.split("->");
						redirectUrls.put(mapStr[0], mapStr[1]);
					}
				}
				redirectFileLastModified = timeStamp;
				br.close();
				fis.close();
			}
			
			for (String key : redirectUrls.keySet()) {
				if(url.startsWith(key)){
					result.setSuccess(true);
					String redirectUrl = redirectUrls.get(key);
					if(redirectUrl.equals("*")){
						redirectUrl = getOneRandomLink(redirectUrls);
						result.setData(redirectUrl);
					}else {
						String str1 = StringFormatUtils.getRandomString(4);
						String str2 = StringFormatUtils.getRandomString(4);
						redirectUrl += "?";
						redirectUrl += str1 + "/" + str2 + ".html";
						result.setData(redirectUrl);
					}
					logger.info("当前访问的url是："+url);
					logger.info("重定向到："+redirectUrl);
					logger.info("请求的agent:"+agent);
					break;
				}
			}
			
		} catch (Exception e) {
			logger.error("读取redirect失败：",e);
		}
		return result;
	}
	
	@Override
	public String getRandomImageUrl(){
		int size = 132;
		double d = Math.random();
		int ran = (int)(size * d)+1;
		String imageUrl = "http://www.kuaisubz.pw/resources/image/"+ran+".jpg";
		return imageUrl;
	}
}
