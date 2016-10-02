package com.mgc.common.utils;

import java.util.HashMap;
import java.util.Map;
import com.mgc.common.model.UserLoginVo;


public class Constants
{
/*  public static final String TARGET_URL = "http://www.qq.com/";
  public static final String BIND_URL = "http://www.qq520.com/";
  public static final String VEDIO_URL = "http://www.qq520.com/vedio/";
  public static final String VEDIO_PATH = "F://zhanqun/flv/";*/
  public static final String LOGIN_NAME = "MagicUser";
  public static final String USER_ID = "magicUserId";
  public static final String UPLOAD_FILES = "uploadFiles";
  public static final String VALID_CODE = "validCode";
  public static Map<String, Map<String, String>> sessionMap =new HashMap<String, Map<String,String>>();
  public static Map<Long, UserLoginVo> userLoginVersionMap = new HashMap<Long, UserLoginVo>();
  
}