package com.mgc.user.service;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mgc.common.model.JscInfos;

public interface MagicUserInfoService
{
  public String getContent(String paramString, HttpServletRequest paramHttpServletRequest);

  public String changeUrl(HttpServletRequest paramHttpServletRequest);
  
  public String getContentNews(String targetUrl);

  public String getNewsDetaiOriginal(String source);

  public Document getContentNewsDetail(String targetUrl, HttpServletRequest request,JscInfos jscInfos);

  public String getContentByDocument(Document document,String url,JscInfos jscInfos);

  public void getWeiyuanchuang(Element element);
  
  public String getRadomUrl(JscInfos jscInfos);
  
  public String getRandonInfos();
}