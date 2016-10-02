package com.mgc.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mgc.common.model.Plupload;

public class PluploadUtil {
	private static Logger logger = Logger.getLogger(PluploadUtil.class);
	private static final int BUF_SIZE = 2048;

	public static void upload(Plupload plupload, String tempDir,
			String targetDir) throws IllegalStateException, IOException {
		String targetName = plupload.getName();
		File temp = new File(tempDir);

		upload(plupload, temp, targetDir + targetName);
	}

	public static void upload(Plupload plupload, File temp, String filename)
			throws IllegalStateException, IOException {
		int chunks = plupload.getChunks(); // 获取总的碎片数
		int chunk = plupload.getChunk(); // 获取当前碎片(从0开始计数)

		HttpSession session = plupload.getRequest().getSession();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) plupload
				.getRequest();
		MultiValueMap<String, MultipartFile> map = multipartRequest
				.getMultiFileMap();

		if (map != null) {
			if (!temp.exists())
				temp.mkdirs(); // 如果目标文件夹不存在则创建新的文件夹

			// 事实上迭代器中只存在一个值,所以只需要返回一个值即可
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String str = (String) iter.next();
				List<MultipartFile> fileList = map.get(str);
				for (MultipartFile multipartFile : fileList) {
					// 因为只存在一个值,所以最后返回的既是第一个也是最后一个值
					plupload.setMultipartFile(multipartFile);
					
					// 创建新目标文件
					File targetFile = new File(filename);
					logger.debug("当前块是："+chunk+" 总块数是："+chunks);
					// 当chunks>1则说明当前传的文件为一块碎片，需要合并
					if (chunks > 1) {
						// 需要创建临时文件名，最后再更改名称
						File tempFile = new File(temp.getPath() + "/"
								+ multipartFile.getName());
						// 如果chunk==0,则代表第一块碎片,不需要合并
						saveUploadFile(multipartFile.getInputStream(),
								tempFile, chunk == 0 ? false : true);

						// 上传并合并完成，则将临时名称更改为指定名称
						if (chunks - chunk == 1) {
							tempFile.renameTo(targetFile);
							//保存当前的文件信息
							Map<String, String> fileInfoMap = Constants.sessionMap.get(session.getId());
							if(MapUtils.isEmpty(fileInfoMap)){
								fileInfoMap = new HashMap<String, String>();
							}
							fileInfoMap.put(plupload.getName(), filename);
							Constants.sessionMap.put(session.getId(), fileInfoMap);
							plupload.getName();
						}

					} else {
						// 否则直接将文件内容拷贝至新文件
						multipartFile.transferTo(targetFile);
						//保存当前的文件信息
						Map<String, String> fileInfoMap = Constants.sessionMap.get(session.getId());
						if(MapUtils.isEmpty(fileInfoMap)){
							fileInfoMap = new HashMap<String, String>();
						}
						fileInfoMap.put(plupload.getName(), filename);
						Constants.sessionMap.put(session.getId(), fileInfoMap);
						plupload.getName();
					}
				}
			}
		}

	}

	/**
	 * 保存上传文件，兼合并功能
	 */
	private static void saveUploadFile(InputStream input, File targetFile,
			boolean append) throws IOException {
		OutputStream out = null;
		try {
			if (targetFile.exists() && append) {
				out = new BufferedOutputStream(new FileOutputStream(targetFile,
						true), BUF_SIZE);
			} else {
				out = new BufferedOutputStream(
						new FileOutputStream(targetFile), BUF_SIZE);
			}

			byte[] buffer = new byte[BUF_SIZE];
			int len = 0;
			// 写入文件
			while ((len = input.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭输入输出流
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean isUploadFinish(Plupload plupload) {
		return plupload.getChunks() - plupload.getChunk() == 1;
	}
	
	public static List<Map<String, String>> getListStr(String files){
		List<Map<String, String>> fList = new ArrayList<Map<String, String>>();
		
		if(StringUtils.isNotBlank(files)){
			if(files.contains(",")){
				String[] ss =  files.split(",");
				for (String s : ss) {
					if(StringUtils.isNotBlank(s)){
						Map<String, String> sMap = new HashMap<String, String>();
						String[] sArray = s.split("\\|");
						sMap.put("fileName", sArray[0]);
						sMap.put("fileOriName", sArray[1]);
						fList.add(sMap);
					}
				}
			}
		}
		return fList;
	}
}