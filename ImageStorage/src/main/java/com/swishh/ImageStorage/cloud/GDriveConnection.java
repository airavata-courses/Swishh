package com.swishh.ImageStorage.cloud;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

@Service
public class GDriveConnection {
	
    private static List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static JsonFactory JSON_FACTORY=JacksonFactory.getDefaultInstance();
    private final static String APPLICATION_NAME="SWISHH";
    
	private static Credential getCredentials() throws IOException {
	    GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(ResourceUtils.getFile("classpath:Gdrive.json")))
	                .createScoped(SCOPES);

	    return credential;
	}
	public static Drive getDriveService() throws IOException, GeneralSecurityException {
	    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

	    // Instantiating a client
	    Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
	                .setApplicationName(APPLICATION_NAME)
	                .build();

	    return service;
	}

}
