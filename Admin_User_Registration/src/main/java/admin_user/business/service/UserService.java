package admin_user.business.service;

import java.util.List;
import java.util.Optional;

import admin_user.dao.model.User;
import admin_user.web.dto.UserDto;

public interface UserService {
	
	User save (UserDto userDto);

}
