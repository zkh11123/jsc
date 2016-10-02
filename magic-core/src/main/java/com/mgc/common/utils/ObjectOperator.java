package com.mgc.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectOperator
{
  public static Map<String, Object> transObjectToMap(Object object)
    throws Exception
  {
    Map objMap = new HashMap();
    if (object != null) {
      Class clz = object.getClass();
      Field[] fields = clz.getDeclaredFields();
      for (Field field : fields)
      {
        String key = field.getName();
        int modified = field.getModifiers();
        if (modified <= 8)
        {
          Method m = object.getClass().getMethod(
            "get" + getMethodName(field.getName()), new Class[0]);
          Object value = m.invoke(object, new Object[0]);
          objMap.put(key, value);
        }
      }
    }
    return objMap;
  }

  private static String getMethodName(String fildeName) throws Exception {
    byte[] items = fildeName.getBytes();
    items[0] = (byte)((char)items[0] - 'a' + 65);
    return new String(items);
  }
}