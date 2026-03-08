package com.springsecurity.SpringSecurityDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/*
	 * Mobile
	 * 
	 * -> when you are searching you only want first 3 parameters
	 * -> your friend is searching for a mobile but he wants 5 parameters
	 * this is called builder design pattern
	 * 
	 * In singleton design pattern there will be only 1 object but can me many references, if you change value for one reference all the reference's values will be changed
	 * 
	 * brand
	 * price
	 * color
	 * ram
	 * processor
	 * design
	 * os
	 * camera
	 * pixel
	 * display
	 */
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		
		
		//Singleton design pattern -> only one object will be created
//		return http.csrf(csrf->csrf.disable())
//				.authorizeHttpRequests(auth->auth
//				.requestMatchers("/practices").permitAll()
//				.requestMatchers("/admin").authenticated()).build();
		//In the above code we have used authenticated() for admin(), it will ask for username and password if the end point is admin, but for permitall() it will not ask for username and password
		
		return http.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
				.requestMatchers("/practices").permitAll()
				//.requestMatchers("/admin").hasRole("ADMIN") //here we will give admin ID PASS and not user ID PASS.
				.requestMatchers("/admin").hasRole("USER")) //here we will give user ID PASS and not admin ID PASS.
				.httpBasic(Customizer.withDefaults()) //it is used to send the data through tokens with the help of Headers, It is basic, there are JWT, Bearer tokens that we will use
				.build();
		
//		return http.csrf(csrf->csrf.disable())  //By default by the springboot csrf(Cross_Site Request Forgery)  will be enabled.
//				
//				.authorizeHttpRequests(auth->auth
//				.requestMatchers("/practices").permitAll()
//				.requestMatchers("/admin").authenticated()) // we can do either authenticated() or the above one in which we have used hasRole("ADMIN")
//				.httpBasic(Customizer.withDefaults()) //For basic authorization like username and password and many more so we need to mention which kind of authorization is this.
//				.build();  
//		
		
	}
	
	
	
	//If we want to create custom username and password
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
		return new InMemoryUserDetailsManager(user);
		
	}
	

}
