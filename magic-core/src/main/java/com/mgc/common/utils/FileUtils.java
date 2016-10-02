package com.mgc.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtils {
	
	public static void copyFile(String sourcePath, String targetPath) throws IOException {
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			File source = new File(sourcePath);
			File target = new File(targetPath);
			if(!target.exists()){
				target.mkdirs();
			}
			String targetFile = targetPath+source.getName();
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(targetFile);
			in = inStream.getChannel();
			out = outStream.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			throw e;
		} finally {
			inStream.close();
			in.close();
			outStream.close();
			out.close();
		}
	}
	
	public static String codeString(File fileName) throws Exception{  
	    BufferedInputStream bin = new BufferedInputStream(  
	    new FileInputStream(fileName));  
	    int p = (bin.read() << 8) + bin.read();  
	    String code = null;  
	      
	    switch (p) {  
	        case 0xefbb:  
	            code = "UTF-8";  
	            break;  
	        case 0xfffe:  
	            code = "Unicode";  
	            break;  
	        case 0xfeff:  
	            code = "UTF-16BE";  
	            break;  
	        default:  
	            code = "GBK";  
	    }  
	      
	    return code;  
	}  
	
	public static boolean deleteFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			return file.delete();
		}else {
			return true;
		}
	}

}