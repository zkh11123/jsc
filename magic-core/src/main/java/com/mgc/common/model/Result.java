package com.mgc.common.model;

import java.io.Serializable;

public class Result
  implements Serializable
{
  private static final long serialVersionUID = -6033060276990504870L;
  private boolean isSuccess = false;
  private String tip;
  private Object data;

  public boolean isSuccess()
  {
    return this.isSuccess;
  }

  public void setSuccess(boolean isSuccess) {
    this.isSuccess = isSuccess;
  }

  public String getTip() {
    return this.tip;
  }

  public void setTip(String tip) {
    this.tip = tip;
  }

  public Object getData() {
    return this.data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}