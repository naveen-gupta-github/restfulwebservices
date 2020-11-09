package com.naveen.rest.webservices.restfulwebservices.User;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.naveen.rest.webservices.restfulwebservices.Exception.EmptyUserListException;
import com.naveen.rest.webservices.restfulwebservices.Exception.UserNotFoundException;

@RestController
public class UsersController {

	@Autowired
	private UsersDaoService service;
	
	@GetMapping("/users")
	public List<Users> retreiveAllUsers(){
	       if(service.findAll().isEmpty())
	    	   throw new EmptyUserListException("List is Empty");
	       return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<Users> retrieveOne(@PathVariable int id) {
		
		Users user =  service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id - "+id);
		
		EntityModel<Users> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retreiveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> postUser(@Valid @RequestBody Users user) {
		
		Users createdUser =  service.saveUser(user);
		
		URI location =  ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		Users user =  service.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("id - "+id);
		
	 }
}













