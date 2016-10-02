package com.mgc.common.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class DESEncryptUtil
{
  public static final String KEY_ALGORITHM = "DES";
  public static final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";
  private static String strDefaultKey = "2016MagicZkhOpen";

  private static Cipher encryptCipher = null;

  private static Cipher decryptCipher = null;

  static {
    Key key = null;
    try {
      key = getKey(strDefaultKey.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      encryptCipher = Cipher.getInstance("DES");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    }
    try {
      encryptCipher.init(1, key);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
    try {
      decryptCipher = Cipher.getInstance("DES");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    }
    try {
      decryptCipher.init(2, key);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
  }

  public DESEncryptUtil()
    throws Exception
  {
  }

  public DESEncryptUtil(String strKey)
    throws Exception
  {
    Key key = getKey(strKey.getBytes());
    encryptCipher = Cipher.getInstance("DES");
    encryptCipher.init(1, key);
    decryptCipher = Cipher.getInstance("DES");
    decryptCipher.init(2, key);
  }

  private static String byteArr2HexStr(byte[] arrB)
    throws Exception
  {
    int iLen = arrB.length;

    StringBuffer sb = new StringBuffer(iLen * 2);
    for (int i = 0; i < iLen; i++) {
      int intTmp = arrB[i];

      while (intTmp < 0) {
        intTmp += 256;
      }

      if (intTmp < 16) {
        sb.append("0");
      }
      sb.append(Integer.toString(intTmp, 16));
    }
    return sb.toString();
  }

  private static byte[] hexStr2ByteArr(String strIn)
    throws Exception
  {
    byte[] arrB = strIn.getBytes();
    int iLen = arrB.length;

    byte[] arrOut = new byte[iLen / 2];
    for (int i = 0; i < iLen; i += 2) {
      String strTmp = new String(arrB, i, 2);
      arrOut[(i / 2)] = (byte)Integer.parseInt(strTmp, 16);
    }
    return arrOut;
  }

  private static byte[] encrypt(byte[] arrB)
    throws Exception
  {
    return encryptCipher.doFinal(arrB);
  }

  public static String encrypt(String strIn)
    throws Exception
  {
    return byteArr2HexStr(encrypt(strIn.getBytes()));
  }

  private static byte[] decrypt(byte[] arrB)
    throws Exception
  {
    return decryptCipher.doFinal(arrB);
  }

  public static String decrypt(String strIn)
    throws Exception
  {
    return new String(decrypt(hexStr2ByteArr(strIn)));
  }

  private static Key getKey(byte[] arrBTmp)
    throws Exception
  {
    byte[] arrB = new byte[8];

    for (int i = 0; (i < arrBTmp.length) && (i < arrB.length); i++) {
      arrB[i] = arrBTmp[i];
    }

    Key key = new SecretKeySpec(arrB, "DES");
    return key;
  }

  public static void main(String[] args)
    throws Exception
  {
    System.out.println("--------------------DEC加密------------------------");

    System.out.println("加密后：" + encrypt("123456"));

    System.out.println("解密后：" + decrypt(encrypt("123456")));

    Pattern pattern = Pattern.compile("abc");
    Matcher m = pattern.matcher("abcd");
    System.out.println(m.find());
  }
}