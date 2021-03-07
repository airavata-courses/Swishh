package com.swishh.ImageStorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swishh.ImageStorage.models.FilesDao;

public interface IFilesRepository extends JpaRepository<FilesDao, String> {

}
