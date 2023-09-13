package net.kkl.bookapp.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUtil {
	public String writeMediaFile(MultipartFile multipartFile, String absolutePath, String relativePath)
			throws IllegalStateException, IOException {
		String fileName = multipartFile.getOriginalFilename();
		String fileNameUp = fileName == null ? "" : fileName.replaceAll(" ", "_");
		File dest = new File(absolutePath, fileNameUp);
		FileUtils.writeByteArrayToFile(dest, multipartFile.getBytes());
		return relativePath + fileNameUp;
	}

}