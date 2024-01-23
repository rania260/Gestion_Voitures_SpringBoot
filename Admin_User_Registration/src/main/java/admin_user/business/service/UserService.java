package admin_user.business.service;

import java.util.List;
import java.util.Optional;

import admin_user.dao.model.User;
import admin_user.web.dto.UserDto;

public interface UserService {
	
	User save (UserDto userDto);
	
	public List<User> getAllUsers();
	
	public Optional<User> getUser(Long id);

	public User addUser(User U);
 
     // Updates an existing car.
     public User updateUser(User U);
 
     // Deletes a car by their ID.
     public void deleteUser(Long id);

}
