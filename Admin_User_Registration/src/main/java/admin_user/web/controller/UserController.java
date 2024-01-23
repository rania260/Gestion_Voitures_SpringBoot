package admin_user.web.controller;


import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import admin_user.business.service.UserService;
import admin_user.business.service.VoitureService;
import admin_user.dao.model.User;
import admin_user.web.dto.UserDto;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;

	@Autowired
    VoitureService voitureService;

	
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "register";
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto);
		model.addAttribute("message", "Registered Successfuly!");
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

	@GetMapping("user")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		//model.addAttribute("voitures", voitureService.getAllVoitures());
		return "user";
	}
	

	@GetMapping("admin")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "admin";
	}

	@GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", this.userService.getAllUsers());
        return "users"; 
    }

	//create
	@GetMapping("/users/create")
	public String showAddUser(Model model){
		model.addAttribute("userDto", new UserDto());
		return "createUser";
	}
   
	@PostMapping("/users/create")
	public String createUser(@ModelAttribute("userDto")@Validated UserDto userDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "createUser";
		}
		User user = new User();
		
		user.setFullname(userDto.getFullname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		userService.addUser(user);
		return "redirect:/users";
	}
    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id")Long id){
         Optional<User> user = userService.getUser(id);
         if(!user.isPresent()){
            
         }
		this.userService.deleteUser(id);
        return "redirect:/users";
    }

	//update
	@GetMapping("/users/{id}/edit")
	public String showEditUser(@PathVariable("id")Long id, Model model){
		Optional<User> user = userService.getUser(id);
		if(user==null){ 
	   }
	   UserDto userDto = new UserDto(
	   user.get().getFullname(),
	   user.get().getEmail(),
	   user.get().getPassword(),
	   user.get().getRole()
	   );
	   model.addAttribute("userDto", userDto);
	   return "modifierUser";
   }
   @PostMapping("/users/{id}/edit")
   public String editUser(@PathVariable("id")Long id, @ModelAttribute("productForm")@Validated UserDto userDto, BindingResult bindingResult){
	   if(bindingResult.hasErrors()) {
		   return "modifierUser";
	   }

	   Optional<User> user = userService.getUser(id);
		  
			   if(user.isPresent()){
				user.get().setFullname(userDto.getFullname());
				user.get().setEmail(userDto.getEmail());
				user.get().setPassword(userDto.getPassword());
				user.get().setRole(userDto.getRole());
				
			   }
		userService.updateUser(user.get());
	   return "redirect:/users";
   }

  
}
