package com.mgc.test;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.mgc.common.utils.HttpClientFactory;

public class HttpClientFactoryTest {
	public static void main(String[] args) {
		Header[] headers = {
				new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"),
				new BasicHeader("Accept-Encoding","gzip, deflate, sdch"),
				new BasicHeader("Accept-Language","zh-CN,zh;q=0.8,und;q=0.6"),
				new BasicHeader("Connection","keep-alive"),
				new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36")
				};
		String fckUrl = "http://www.doc88.com/admin/js/fckeditor/editor/dialog/fck_about.html";
		try {
			int status = HttpClientFactory.getUrlStatus(fckUrl, headers);
			System.out.println(status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
