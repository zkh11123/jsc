package com.mgc.common.model;

import java.io.Serializable;

public class SoftAdvert implements Serializable{

	private static final long serialVersionUID = 3310935856390027727L;
	
	private String url;
	
	private String keyword;
	
	private String name;
	
	private String tencentNum;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTencentNum() {
		return tencentNum;
	}

	public void setTencentNum(String tencentNum) {
		this.tencentNum = tencentNum;
	}
	
	
}
