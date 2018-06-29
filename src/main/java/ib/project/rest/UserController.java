package ib.project.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ib.project.model.User;
import ib.project.service.UserService;
import ib.project.service.UserServiceInterface;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	UserServiceInterface userService;
	
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
	
	
//	@RequestMapping( method = GET, value= "/all")
//    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping
    public List<User> loadAll() {
		System.out.println("svi useriiiii: "+userService.findAll());
        return this.userService.findAll();
    }

}
