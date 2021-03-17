package com.swishh.ImageStorage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swishh.ImageStorage.models.FolderDao;

@Repository
public interface IFolderRepository {/*extends JpaRepository<FolderDao, String>{
	

	@Query(value = "select * from folder_dao where ownername=?",nativeQuery = true)
	public List<FolderDao> getFoldersByOwnerName(String ownerName);
	
	@Query(value = "select * from folder_dao where sharedwith like %?%",nativeQuery = true)
	public List<FolderDao> getFoldersBySharedOwner(String ownerName);*/
}
