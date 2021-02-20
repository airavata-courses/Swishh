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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.swishh.ImageStorage.exception.StorageException;
import com.swishh.ImageStorage.exception.StorageFileNotFoundException;
import com.swishh.ImageStorage.service.StorageService;

@Service
public class FileSystemStorageService implements StorageService {

	@Value("${file.upload.dir}")
	private String fileUploadDir;

	private Path rootLocation;
	@PostConstruct
	private void initPrams() {
		rootLocation = Paths.get(File.pathSeparator+fileUploadDir);

	}

	@Override
	public void store(MultipartFile[] files,String username) {
		try {
			Path uploaddirPath=Paths.get(fileUploadDir+File.separator+username);

			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					throw new StorageException("Failed to store empty file.");
				}
				Path destinationFile = uploaddirPath.resolve(Paths.get(file.getOriginalFilename())).normalize()
						.toAbsolutePath();
				File creatFile = new File(destinationFile.toString());
				if (!creatFile.exists()) {
					File uploaddir = new File(
							creatFile.getAbsolutePath().substring(0, creatFile.getAbsolutePath().lastIndexOf(File.separatorChar)));
					uploaddir.mkdirs();
					creatFile.createNewFile();
				}

				
				try (InputStream inputStream = file.getInputStream()) {
					Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public ArrayList<byte[]> loadAll(String username) throws IOException {
		Path filePath=Paths.get(fileUploadDir+File.separator+username);
		ArrayList<byte[]> filesList = new ArrayList<byte[]>();
		File dir = new File(filePath.toString());
		try {
			if (dir.exists() && dir.isDirectory()) {
				for (File file : dir.listFiles()) {
					byte[] filear = Files.readAllBytes(file.toPath());
					filesList.add(filear);

				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return filesList;

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
}
