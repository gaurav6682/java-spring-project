package com.project.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpload {

	public static String uploadFile(MultipartFile file, String uploadDir) {
		try {
			byte bytes[] = file.getBytes();

			String rootPath = System.getProperty("user.dir");
			String dirPath = rootPath + File.separator + "src" + File.separator + "main" + File.separator + "webapp"
					+ File.separator + uploadDir + File.separator;
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			String newFileName = file.getOriginalFilename();
			String filePath = dirPath + newFileName;
			dir = new File(filePath);
			if (dir.exists()) {
				dir.delete();
			}
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			/*
			 * System.err.println(rootPath); System.err.println(dirPath);
			 * System.err.println(extension); System.err.println(newFileName);
			 * System.err.println(filePath);
			 */
			return (uploadDir + File.separator + newFileName).replace("\\", "/");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}