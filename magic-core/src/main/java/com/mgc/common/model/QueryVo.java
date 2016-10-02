package com.mgc.common.model;

import org.apache.commons.lang.StringUtils;

public class QueryVo
{
  private String userName;
  private String password;
  private String phoneNumber;
  private String email;
  private String validimage;

  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isNotIllegal() {
    if (StringUtils.isBlank(this.userName)) {
      return false;
    }
    if (StringUtils.isBlank(this.password)) {
      return false;
    }
    if (StringUtils.isBlank(this.email)) {
      return false;
    }
    if (StringUtils.isBlank(this.phoneNumber)) {
      return false;
    }
    return true;
  }

  public boolean isIllegal() {
    return !isNotIllegal();
  }

public String getValidimage() {
	return validimage;
}

public void setValidimage(String validimage) {
	this.validimage = validimage;
}
}