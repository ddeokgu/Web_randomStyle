package com.example.randomStyle.util;

import java.awt.image.BufferedImage;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) 
			throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
		// 미디어 파일이 존재하면 
		if(MediaUtils.getMediaType(formatName) != null) {
			// 썸네일을 만듬
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
			
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadedFileName;
		
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar,'/');
	}
	
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		
		// 원본 이미지
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		// 썸네일 생성
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// 썸네일 파일의 경로
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		// 파일 확장자 
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		// 연도 
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		// 월(0~11이 나와서 +1)
		String monthPath = yearPath + File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		// 일 
		String datePath = monthPath + File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.DATE));
		// 디렉토리 생성 
		makeDir(uploadPath, yearPath, monthPath, datePath);
		System.out.println(datePath);
		return datePath;
	}

	private static void makeDir(String uploadPath, String ...paths) {
		//존재하는 디렉토리이면 디렉토리를 생성하지 않음 
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		// 새로운 디렉토리이면 
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {
				dirPath.mkdir(); // 생성 
			}
		}
	}
}
