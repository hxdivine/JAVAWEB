package com.util;

import java.util.UUID;
/**
 * 文件的utils
 * @author 骄傲的大树
 *
 */
public class UploadUtils {

	/**
	 * 随机文件名
	 * @param fileName
	 * @return
	 */
	public static String getUuidFileName(String fileName) {
		int idx = fileName.lastIndexOf(".");
		String extions = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	/**
	 * 目录分离
	 * @param uuidFileName
	 * @return
	 */
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf; //作为一级目录
		int code2 = code1 >>> 4;
		int d2 = code2 & 0xf; //作为二级目录
		return "/"+d1+"/"+d2;
	}
}
