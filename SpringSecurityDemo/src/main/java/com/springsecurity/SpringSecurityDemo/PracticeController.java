package com.springsecurity.SpringSecurityDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/practices")
public class PracticeController {
	
	
	//In postman if we run http://localhost:8080/practices in get mode getDetails() will run.
	@GetMapping("/practices")
	public String getDetails() {
		return "Details";
	}
	
	//If we don't give end point to this method,In postman if we run http://localhost:8080/practices in post mode deleteUser() will run.
	//But if we give GetMapping to both then we need to specify admin for one to make it unique.
	//@PostMapping
	@GetMapping("/admin")
	public String deleteUser() {
		return "Delete";
	}
	
	//Here if we are giving admin as the end point and writing on the browser it will give admin on the page as it is not secures, so we will add spring security dependency from spring initialzr
	//We have added the dependency, when we will restart the server , in the console there will be a generated password, now if you go on the browser and give any end point it will first land on a login page where username is "user" and password is the generated password in the console
	
	//But we want only admin end point to be secured and not all the endpoint so weill create a SecurityConfig class.
	

}
