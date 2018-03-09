package com.bod.util;

import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.mina.util.Base64;

public class EncrypterDecrypter
{
  private KeySpec keySpec;
  private SecretKey key;
  private IvParameterSpec iv;
  
  public EncrypterDecrypter(String keyString)
  {
    try
    {
      MessageDigest md = MessageDigest.getInstance("md5");
      byte[] digestOfPassword = md.digest(Base64.decodeBase64(keyString.getBytes("utf-8")));
      
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      int j = 0;
      for (int k = 16; j < 8;) {
        keyBytes[(k++)] = keyBytes[(j++)];
      }
      this.keySpec = new DESedeKeySpec(keyBytes);
      
      this.key = SecretKeyFactory.getInstance("DESede").generateSecret(this.keySpec);
      
      byte[] ivBytes = { 0, 1, 2, 3, 0, 0, 0, 1 };
      
      this.iv = new IvParameterSpec(ivBytes);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public String encrypt(String value)
  {
    try
    {
      Cipher ecipher = Cipher.getInstance("DESede/CBC/PKCS5Padding", "SunJCE");
      
      ecipher.init(1, this.key, this.iv);
      if (value == null) {
        return null;
      }
      byte[] utf8 = value.getBytes("UTF8");
      
      byte[] enc = ecipher.doFinal(utf8);
      
      return new String(Base64.encodeBase64(enc), "UTF-8");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public String decrypt(String value)
  {
    try
    {
      Cipher dcipher = Cipher.getInstance("DESede/CBC/PKCS5Padding", "SunJCE");
      
      dcipher.init(2, this.key, this.iv);
      if (value == null) {
        return null;
      }
      byte[] dec = Base64.decodeBase64(value.getBytes());
      
      byte[] utf8 = dcipher.doFinal(dec);
      
      return new String(utf8, "UTF8");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
