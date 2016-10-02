package com.mgc.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ConvertUtil {
	
	private static Logger logger = Logger.getLogger(ConvertUtil.class);
	
	public static boolean convertVideoToFlv(String srcFile,String targetDir){
		
		boolean result = false;
		// 创建一个List集合来保存转换视频文件为flv格式的命令
		String targetFile = targetDir+DateFormatUtil.formatDate(new Date(),"yyyyMMdd")+"//";
		File file = new File(targetFile);
		if(!file.exists()){
			file.mkdirs();
		}
		File src = new File(srcFile);
		String targetName = src.getName();
		targetFile += targetName;
		targetFile = targetFile.substring(0,targetFile.length()-4 );
		targetFile += ".flv";
		logger.info(targetFile);
		
		String ffmpegPath = "F://zhanqun//ffmpeg-latest-win64-static//bin//ffmpeg.exe";
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); // 添加转换工具路径
        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add(srcFile); // 添加要转换格式的视频文件的路径
        convert.add("-qscale");     //指定转换的质量
        convert.add("6");
        convert.add("-ab");        //设置音频码率
        convert.add("64");
        convert.add("-ac");        //设置声道数
        convert.add("2");
        convert.add("-ar");        //设置声音的采样频率
        convert.add("22050");
        convert.add("-r");        //设置帧频
        convert.add("24");
        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add(targetFile);
		
        ProcessBuilder builder = new ProcessBuilder();
        logger.info(convert);
        builder.command(convert);
        builder.redirectErrorStream(true);
        try {
			
        	Process process = builder.start();
			java.io.InputStream in=process.getInputStream();
            
	         byte[] re=new byte[1024];
	         while (in.read(re)!= -1) {
	             logger.debug(re);
	          }
	          in.close();
	          File tFile = new File(targetFile);
	          if(tFile.isFile()){
	        	  result = true;
	          }
	          
		} catch (IOException e) {
			result = false;
			logger.error("转换文件出错",e);
		}
        
		return result;
	}
	
	public static String convertUnicode(String ori) {
		char aChar;
		int len = ori.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = ori.charAt(x++);
			if (aChar == '\\') {
				aChar = ori.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = ori.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);

		}
		return outBuffer.toString();
	}
	
	public static String replaceString(String source,Map<String, String> replaceMap){
		if(StringUtils.isNotBlank(source)){
			for (String key : replaceMap.keySet()) {
				try {
					source = source.replaceAll(key, replaceMap.get(key));
				} catch (Exception e) {
					logger.error(key+"="+replaceMap.get(key));
				}
			}
		}
		return source;
	}
}
