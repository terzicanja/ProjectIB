package ib.project.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ib.project.dto.UserDTO;
import ib.project.model.Authority;
import ib.project.model.User;
import ib.project.service.AuthorityServiceInterface;
import ib.project.service.UserService;
import ib.project.service.UserServiceInterface;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserServiceInterface userService;
	
	@Autowired
	AuthorityServiceInterface authorityService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	@GetMapping(value = "/all")
//	private List<User> allUsers(){
//		List<User> users = userService.findAll();
//		return users;
//	}
//	@GetMapping(value = "/all")
//	public ResponseEntity<List<User>> getUsers(){
//		List<User> users = userService.findAll();
//		List<User> nesto = new ArrayList<>();
////		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
//		for(User u: users) {
//			nesto.add(new User(u));
//		}
//		return new ResponseEntity<List<User>>(nesto, HttpStatus.OK);
//	}
	
	
	@RequestMapping("/whoami")
    public User user(Principal user) {
        return this.userService.findByEmail(user.getName());
    }
	
	
	@RequestMapping( method = GET)
//    @PreAuthorize("hasRole('ADMIN')")
//	@GetMapping
    public List<User> loadAll() {
		System.out.println("svi useriiiii: "+userService.findAll());
        return this.userService.findAll();
    }
	
	@GetMapping(value = "/inactive")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> inactiveUsers(){
		return this.userService.findByActiveFalse();
	}
	
	@GetMapping(value = "/search/{text}")
	public List<User> search(@PathVariable("text") String text){
		return this.userService.findAllByEmail(text);
	}
	
	@PostMapping(value = "/create", consumes="application/json")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){
		User u = new User();
		Authority authority = authorityService.findByName("ROLE_USER");
		
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		u.setPassword(bc.encode(userDTO.getPassword()));
		
//		passwordEncoder().encode(userDTO.getPassword());
		
		u.setEmail(userDTO.getEmail());
//		u.setPassword(userDTO.getPassword());
//		u.setPassword(passwordEncoder().encode(userDTO.getPassword()));
//		u.setPassword(passwordEncoder(userDTO.getPassword()));
		u.setActive(false);
		
//		Set<Authority> a = u.getUser_authorities();
//		a.add(authority);
		u.getUser_authorities().add(authority);
//		u.setUser_authorities(authority);
		
		u = userService.save(u);
		return new ResponseEntity<UserDTO>(new UserDTO(u),HttpStatus.OK);
	}
	
	@PutMapping(value="/update/{id}", consumes="application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> update(UserDTO userDTO, @PathVariable("id") Long id){
		User user = userService.findById(id);
		if(user == null) {
			System.out.println("nisam nasao usera");
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
		user.setActive(true);
		user.setCertificate(user.getEmail());
		user = userService.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
	}
	
	
	
	
	
	
	

}
