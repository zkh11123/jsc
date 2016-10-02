package com.mgc.common.model;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class Plupload
{
  private String name;
  private int chunks = -1;

  private int chunk = -1;
  private HttpServletRequest request;
  private MultipartFile multipartFile;

  public String getName()
  {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getChunks() {
    return this.chunks;
  }

  public void setChunks(int chunks) {
    this.chunks = chunks;
  }

  public int getChunk() {
    return this.chunk;
  }

  public void setChunk(int chunk) {
    this.chunk = chunk;
  }

  public HttpServletRequest getRequest() {
    return this.request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public MultipartFile getMultipartFile() {
    return this.multipartFile;
  }

  public void setMultipartFile(MultipartFile multipartFile) {
    this.multipartFile = multipartFile;
  }
}