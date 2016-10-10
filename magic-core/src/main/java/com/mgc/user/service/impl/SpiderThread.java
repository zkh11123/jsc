package com.mgc.user.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mgc.common.utils.HttpClientFactory;

public class SpiderThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(SpiderThread.class);
	private int fileNum;
	private List<String> excludeList ;
	private List<String> dicList;
	public SpiderThread(int fileNum,List<String> excludeList,List<String> dicList){
		this.fileNum = fileNum;
		this.excludeList = excludeList;
		this.dicList = dicList;
	}
	
	public void run() {
		try {
			logger.info("启动线程："+fileNum);
			spiderUrl(fileNum);
		} catch (Exception e) {
			logger.error("爬出出错:",e);
		}
	}
	
	private boolean isExclude(String url){
		for (String excludeUrl : excludeList) {
			if(url.endsWith(excludeUrl)){
				return false;
			}
		}
		return true;
	}
	
	private void spiderUrl(int fileNum) throws Exception{
		Header[] headers = {
				new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"),
				new BasicHeader("Accept-Encoding","gzip, deflate, sdch"),
				new BasicHeader("Accept-Language","zh-CN,zh;q=0.8,und;q=0.6"),
				new BasicHeader("Connection","keep-alive"),
				new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36")
				};
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File("e://saomiaokw/"+fileNum+".txt")),
                "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File("e://saomiaourl/"+fileNum+".txt")), "utf-8"));
		String lineTxt = null;
		while ((lineTxt = br.readLine()) != null) {
			for (int i = 0; i < 76; i++) {
				String baiduUrl = "https://www.baidu.com/s?wd="+lineTxt+"&pn="+i+"0";
				//logger.info("当前url"+baiduUrl);
				Document doc = HttpClientFactory.getContentDocument(baiduUrl,
						headers, "utf-8");
				List<Element> elements = doc.getElementsByClass("f13");
				if(CollectionUtils.isNotEmpty(elements)){
					for (Element element : elements) {
						List<Element> aList = element.select("a[href^=http://www.baidu.com/link?]");
						if(CollectionUtils.isNotEmpty(aList)){

							Element urlElement = aList.get(0);
							String url = urlElement.text();
							int len = url.indexOf("/");
							if (len > 0) {
								url = url.substring(0, len);
							}
							if(isExclude(url)){
								bw.write(url);
								bw.newLine();
								bw.flush();
							}
						
							/*Element urlElement = aList.get(0);
							String url = urlElement.text();
							int len = url.indexOf("/");
							if (len > 0) {
								url = url.substring(0, len);
								url = url.replace("\\.", "");
							}
							if(isExclude(url)){
								for (String dic : dicList) {
									String fckUrl = "http://"+url+dic;
									logger.info("获取fckurl:"+fckUrl);
									int status = 404;
									try {
										status = HttpClientFactory.getUrlStatus(fckUrl, headers);
									} catch (Exception e) {
										logger.error("获取状态失败："+fckUrl,e);
									}
									if(status == 200){
										bw.write(fckUrl);
										bw.newLine();
										bw.flush();
									}
								}
							}
						*/
						}
					}
				}
			}
		}
		br.close();
		bw.close();
	}
}
