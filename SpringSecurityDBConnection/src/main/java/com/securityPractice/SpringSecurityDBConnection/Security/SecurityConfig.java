package com.securityPractice.SpringSecurityDBConnection.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity  //Ye annotation use krne ke baad ab hum seedha service class me seedha method ke upar @PreAuthorize annotation use krke rolebased autorization apply kr sakte h, pehle security config me changes krne padte the
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder encode() {  //It is used to encrypt the password.
		return new BCryptPasswordEncoder();   
	}
	
	
//	@Bean
//	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
//		return http.cors(cors->{})  //Cors enable, without this we cannot integrate frontend with this project because it is secured
//				   .csrf(csrf->csrf.disable())  //By default it will be enable and CSRF attack can happen. And CSRF token are created manually and here we are not creating any token manually, Automatic tokens are getting created by Headers.
//				   .authorizeHttpRequests(auth->auth
//				   .requestMatchers("/swagger-ui.html").permitAll()		   
//				   .requestMatchers("/actuator/beans","/actuator/sessions").hasRole("ADMIN")
//				   .requestMatchers("/actuator/**").permitAll()
//				   .requestMatchers("/create").permitAll()
//				   .requestMatchers("/public/**").permitAll()
//				   .requestMatchers("/admin/**").hasRole("ADMIN")  //("/admin/**") this represents admin/anything that can be an endpoint
//				   //.requestMatchers("/find-all").hasRole("ADMIN")    //We can do like this for find-all the rows in the table but we have given /admin/** sp we can change the end point in the controller to /admin/all so that no need to create another requestMatcher.
//				   .anyRequest().authenticated())
//				   .httpBasic(Customizer.withDefaults()).build();
//	}
	
	
	//To understand CSRF
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth->auth //Csrf is disabled here so if we perform any post,patch,put,delete request it will not work. 
				   .requestMatchers("/public").permitAll()
				   .requestMatchers("/admin").hasRole("ADMIN")
				   .requestMatchers("/csrf-token").permitAll()  //this in the manual creation of 
				   .anyRequest().authenticated())
				   .httpBasic(Customizer.withDefaults()).build();
	}
	
	
	@Bean
	public UserDetailsService getUserDetails() {
		
		UserDetails admin = User.withUsername("admin")
				           //.password("1234")  //Here it will show the password as 1234 but we want to encode the password so will use {noop}
						   .password("{noop}1234")	//This will encode our password
				           .roles("ADMIN")    //here we have passed role as ADMIN so in the above method we need to tell that the role is ADMIN
				           .build();
		
		UserDetails user = User.withUsername("user")
								.password("{noop}1234")
								.roles("USER")
								.build();
		//Both user and admin will be stored in JVM memory.
		
		//return new InMemoryUserDetailsManager(admin);
//		return new InMemoryUserDetailsManager(user);
		//If we want to use both then
		return new InMemoryUserDetailsManager(user,admin);
		
	}
	


}
