package com.swishh.ImageStorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swishh.ImageStorage.models.FilesDao;

public interface IFilesRepository extends JpaRepository<FilesDao, Integer> {
	
	
	@Query(value="select * from files_dao where userid=? and fileid=?",nativeQuery = true)
	public FilesDao findByUserAndFile(String userid,String fileid);

}
