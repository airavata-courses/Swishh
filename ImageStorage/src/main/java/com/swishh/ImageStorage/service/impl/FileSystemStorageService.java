package com.swishh.ImageStorage.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.swishh.ImageStorage.exception.StorageException;
import com.swishh.ImageStorage.exception.StorageFileNotFoundException;
import com.swishh.ImageStorage.models.FilesDao;
import com.swishh.ImageStorage.models.UserDAO;
import com.swishh.ImageStorage.models.request.FilesResponse;
import com.swishh.ImageStorage.service.IFileService;
import com.swishh.ImageStorage.service.IUserService;
import com.swishh.ImageStorage.service.StorageService;
import com.swishh.ImageStorage.util.GDriveFileUploadUtil;
import com.swishh.ImageStorage.util.UserIdUtil;

@Service
public class FileSystemStorageService implements StorageService {

	@Value("${file.upload.dir}")
	private String fileUploadDir;

	private Path rootLocation;
	
	@Autowired 
	private IUserService userService; 
	
	@Autowired
	private IFileService fileService;
	
	@Autowired
	private GDriveFileUploadUtil gcloudUploadUtil;
	
	@Autowired
	UserIdUtil userIdUtil;
	
	@PostConstruct
	private void initPrams() {
		rootLocation = Paths.get(File.pathSeparator+fileUploadDir);

	}

	@Override
	public void store(MultipartFile[] files,String username,String folder) {
		System.out.println("---------------upload files method starts--------------------");
		try {
			String userId=userIdUtil.getUserIdfromUserName(username);
			List<FilesDao> filesList=new ArrayList<FilesDao>();
			String filepath=fileUploadDir+File.separator+userId;
			if(folder!=null&&folder.trim()!="") {
				filepath=filepath+File.separator+folder;
				
			}
			Path uploaddirPath=Paths.get(filepath);
			System.out.println(uploaddirPath.toString());
			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					throw new StorageException("Failed to store empty file.");
				}
				String fileExtension=file.getContentType().substring(file.getContentType().lastIndexOf('/')+1);
				String filename=UUID.randomUUID().toString();
				FilesDao filedao=new FilesDao();
				filedao.setUserId(userId);
				filedao.setFileids(filename);
				filename+="."+fileExtension;
				
				File somefile=new File("Som.txt");
				
				System.out.println(somefile.getAbsolutePath());
				Path destinationFile = uploaddirPath.resolve(Paths.get(filename)).normalize()
						.toAbsolutePath();
				System.out.println(destinationFile.toString());
				File creatFile = new File(destinationFile.toString());
				System.out.println(creatFile.getAbsolutePath()+" files :"+creatFile.exists());
				if (!creatFile.exists()) {
				
					File uploaddir = new File(
							creatFile.getAbsolutePath().substring(0, creatFile.getAbsolutePath().lastIndexOf(File.separatorChar)));
					uploaddir.mkdirs();
					creatFile.createNewFile();
					System.out.println(creatFile.getAbsolutePath()+" files :"+creatFile.exists());
				}
				System.out.println(destinationFile);
				gcloudUploadUtil.fileUpload(file, filename);
				try (InputStream inputStream = file.getInputStream()) {
					Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
				}
			}
			saveFileIdsToDB(userId, filesList);
			System.out.println("---------------Upload files method ends--------------------");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public ArrayList<FilesResponse> loadAll(String username) throws IOException {
		System.out.println("---------------Get files method starts--------------------");
		System.out.println(username);
		String userId=userIdUtil.getUserIdfromUserName(username);
		System.out.println(userId+" found");
		Path filePath=Paths.get(fileUploadDir+File.separator+userId);
		System.out.println(filePath.toString());
		ArrayList<byte[]> filesList = new ArrayList<byte[]>();
		ArrayList<FilesResponse> fileResponseList=new ArrayList<FilesResponse>();
		File dir = new File(filePath.toString());
		System.out.println("dir :"+dir.getAbsolutePath()+ " "+dir.exists());
		try {
			if (dir.exists() && dir.isDirectory()) {
				for (File file : dir.listFiles()) {
					System.out.println("getting files from "+file.toString()+" "+file.getAbsolutePath());
					byte[] filear = Files.readAllBytes(file.toPath());
					filesList.add(filear);
					FilesResponse filesResponse=new FilesResponse();
					filesResponse.setFilename(file.getName());
					filesResponse.setFile(Files.readAllBytes(file.toPath()));
					fileResponseList.add(filesResponse);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("---------------Get files method ends--------------------");
		return fileResponseList;

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
	
	
	
	private void saveFileIdsToDB(String userid,List<FilesDao> fileids) {
		for(FilesDao file:fileids)
		fileService.updateUserFiles(file);
		
	}
}
