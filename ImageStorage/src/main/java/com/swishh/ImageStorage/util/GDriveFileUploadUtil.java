package com.swishh.ImageStorage.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.GeneralSecurityException;

import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.http.FileContent;
import com.swishh.ImageStorage.cloud.GDriveConnection;

public class GDriveFileUploadUtil {
	
	public void fileUpload(MultipartFile file,String fileId) throws IllegalStateException, IOException {
		com.google.api.services.drive.model.File uploadFile=new com.google.api.services.drive.model.File();
		uploadFile.setName(fileId);
		uploadFile.setMimeType("image/jpeg");
		File tmpFile=new File(fileId);
		if(!tmpFile.exists()) {
			tmpFile.createNewFile();
		}
		try (OutputStream os = Files.newOutputStream(tmpFile.toPath())) {
	        os.write(file.getBytes());
	    }
		System.out.println(tmpFile.getTotalSpace()+" "+tmpFile.getAbsolutePath()+" "+file.getContentType());
		FileContent content=new FileContent(file.getContentType(),tmpFile);
		System.out.println(content.getLength()+" "+content.getType());
		try {
			com.google.api.services.drive.model.File file2=GDriveConnection.getDriveService().files().create(uploadFile, content)
			.setFields("id")
			.execute();
			System.out.println(file2.toPrettyString());
			System.out.println(file2.getName()+"  "+file2.getTeamDriveId()+" "+file2.getDescription()+file2.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
