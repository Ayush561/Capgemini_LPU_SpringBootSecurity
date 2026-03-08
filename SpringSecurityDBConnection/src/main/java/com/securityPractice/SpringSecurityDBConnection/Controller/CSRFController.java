package com.securityPractice.SpringSecurityDBConnection.Controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSRFController {
	
	//If csrf is not disabled in the security config then we won't be able to do DML operations, only get request is allowed
	//DML(Post,Path,Delete) will only work when the CSRF is disabled
	
	@GetMapping("/public")
	public String getDetails() {
		return "Details";
	}
	
	@PostMapping("/admin")  //This will not work in the postman because we haven't disabled the csrf. 
	public String deleteUser() {
		return "Delete";
	}

	@GetMapping("/csrf-token")
	public CsrfToken getCsrf(CsrfToken token) {
		return token;
	}
}
