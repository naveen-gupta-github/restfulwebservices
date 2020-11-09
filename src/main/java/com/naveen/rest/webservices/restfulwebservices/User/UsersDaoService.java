package com.naveen.rest.webservices.restfulwebservices.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UsersDaoService {

	private static List<Users> users = new ArrayList<>();
	private static int  usersCount=4;
	static {
		
		users.add(new Users("naveen", "IT", 2018, 1));
		users.add(new Users("neel", "IT", 2018, 2));
		users.add(new Users("shardul", "EC", 2019, 3));
		users.add(new Users("nikki", "CS", 2016, 4));
	}
	
	public List<Users> findAll(){
		return users;
	}
	
	public Users saveUser(Users user) {
		if(user.getId()==null) 
			user.setId(++usersCount);
		
	    users.add(user);
		return user;
		
	}
	
	public Users findOne(int id) {
		
		for(Users user: users) {
			if(user.getId()==id)
				return user;
		}
		
		return null;
		
	}
	
	public Users deleteById(int id) {
		Iterator<Users> iterator = users.iterator();
		while(iterator.hasNext()) {
			
			Users user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
	return null;
	}
	
}








