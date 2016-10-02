package com.mgc.common.model;

import org.apache.commons.lang.StringUtils;

public class UploadInfoVo
{
  private String uploadTitle;
  private String uploadDesc;
  private String uploadType;
  private String sessionId;
  private String files;

  public String getUploadTitle()
  {
    return this.uploadTitle;
  }

  public void setUploadTitle(String uploadTitle) {
    this.uploadTitle = uploadTitle;
  }

  public String getUploadDesc() {
    return this.uploadDesc;
  }

  public void setUploadDesc(String uploadDesc) {
    this.uploadDesc = uploadDesc;
  }

  public boolean isNotIllegal() {
    if (StringUtils.isBlank(this.uploadTitle)) {
      return false;
    }
    if (StringUtils.isBlank(this.uploadDesc)) {
      return false;
    }
    if (StringUtils.isBlank(files) || files.equals(",")) {
		return false;
	}
    return true;
  }

  public boolean isIllegal() {
    return !isNotIllegal();
  }

  public String getUploadType() {
    return this.uploadType;
  }

  public void setUploadType(String uploadType) {
    this.uploadType = uploadType;
  }

public String getFiles() {
	return files;
}

public void setFiles(String files) {
	this.files = files;
}

public String getSessionId() {
	return sessionId;
}

public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
}
}