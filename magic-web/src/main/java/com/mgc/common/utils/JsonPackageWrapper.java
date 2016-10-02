package com.mgc.common.utils;

public class JsonPackageWrapper
{
  public static final String S_OK = "0";
  public static final String S_ERR = "-1";
  private String scode;
  private String smsg;
  private String errorCode;
  private Object data;

  public String getScode()
  {
    return this.scode;
  }

  public void setScode(String scode) {
    this.scode = scode;
  }

  public String getSmsg() {
    return this.smsg;
  }

  public void setSmsg(String smsg) {
    this.smsg = smsg;
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public Object getData() {
    return this.data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}