package admin_user.business.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import admin_user.business.service.UserService;
import admin_user.dao.model.User;
import admin_user.dao.repositories.UserRepository;
import admin_user.web.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getRole(), userDto.getFullname());
		return userRepository.save(user);
	}

	@Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

	@Override
    public Optional<User> getUser(Long id) {
        
        return this.userRepository.findById(id);
    }

    @Override
    public User addUser(User u) {
       
        return this.userRepository.save(u);
    }

    @Override
    public User updateUser(User u) {
       
        return this.userRepository.save(u);
    }

    @Override
    public void deleteUser(Long id) {
        
        this.userRepository.deleteById(id);
    }



}
