package com.naveen.rest.webservices.restfulwebservices.User;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user.")
@Entity
public class Users {
		
	 	@Size(min=2, message="name should be more than 2 characters")
	 	@ApiModelProperty(notes="name MUST be more than 2 characters long")
		private String name;
	    
		private String branch;
		
	    
		private Integer yop;
		
		@Id
		@GeneratedValue
		private Integer id;
		
		@OneToMany(mappedBy="user")
		private List<Post> posts;
		
		public Users(String name, String branch, Integer yop,Integer id ) {
			super();
			this.name = name;
			this.branch = branch;
			this.yop = yop;
			this.id = id;
		}
		
		protected Users() {
			
		}
		
		
		public List<Post> getPosts() {
			return posts;
		}

		public void setPosts(List<Post> posts) {
			this.posts = posts;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public Integer getYop() {
			return yop;
		}
		public void setYop(Integer yop) {
			this.yop = yop;
		}
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return "Users [name=" + name + ", branch=" + branch + ", yop=" + yop + ", id=" + id + "]";
		}
		
		
		
}
