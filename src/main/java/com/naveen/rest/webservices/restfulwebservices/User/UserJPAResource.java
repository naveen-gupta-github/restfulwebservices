package com.naveen.rest.webservices.restfulwebservices.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
public class UserJPAResource {

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@GetMapping("/jpa/users")
	public List<Users> retreiveAllUsers(){
	       if(userRepository.findAll().isEmpty())
	    	   throw new EmptyUserListException("List is Empty");
	       return userRepository.findAll();
	  
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<Users> retrieveOne(@PathVariable int id) {
		
		Optional<Users> user =  userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id - "+id);
		
		EntityModel<Users> resource = EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retreiveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
		
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> postUser(@Valid @RequestBody Users user) {
		
		Users createdUser =  userRepository.save(user);
		
		URI location =  ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<Users> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return userOptional.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<Users> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())	
			throw new UserNotFoundException("id-" + id);
		
		Users user = userOptional.get();
		
		post.setUser(user);
		postRepository.save(post);
		
		URI location =  ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
}


















