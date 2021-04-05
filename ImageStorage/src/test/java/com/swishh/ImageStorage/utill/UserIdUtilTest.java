package com.swishh.ImageStorage.utill;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.swishh.ImageStorage.models.UserDAO;
import com.swishh.ImageStorage.service.impl.UserServiceImpl;
import com.swishh.ImageStorage.util.UserIdUtil;

@SpringBootTest
public class UserIdUtilTest {
	
	@MockBean
	UserServiceImpl userServiceImpl;
	
	@Autowired
	UserIdUtil userIdUtil;
	
	@Test
	public void testgetUserid() {
		UserDAO user=new UserDAO();
		user.setUserid("someid");
		user.setUsername("someuser");
		Mockito.when(userServiceImpl.getUserRecord("someuser")).thenReturn(user);
		assertEquals(user.getUserid(), userIdUtil.getUserIdfromUserName("someuser"));
		
	}

}
