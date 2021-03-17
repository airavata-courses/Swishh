package com.swishh.ImageStorage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swishh.ImageStorage.models.FilesDao;
import com.swishh.ImageStorage.util.UserIdUtil;

@Service
public class ShareService {

	@Autowired
	Fileservice fileservice;

	@Autowired
	UserIdUtil userIdUtil;

	public boolean shareWithUsers(String username, List<String> fileids, List<String> shareWith) {
		String userid = userIdUtil.getUserIdfromUserName(username);
		for (String fileid : fileids) {
			FilesDao filesDao = fileservice.getUserFiles(userid,fileid);
			if (filesDao != null) {
				List<String> curSharedWith = filesDao.getSharedwith();
				curSharedWith.addAll(shareWith);
				filesDao.setSharedwith(curSharedWith);
				fileservice.updateUserFiles(filesDao);
				return true;
			}
		}
		return false;
	}
}
