package com.naveen.rest.webservices.restfulwebservices;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource; 
	
	@GetMapping(path="/hello-world")
	public String helloWorld()
	{
		return "restful web service";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean HelloWorldBean()
	{
		return new HelloWorldBean("Naveen", "Information Technology", "2018");
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean HelloWorldBeanPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("hello,  %s", name));
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null,
									LocaleContextHolder.getLocale());
	}
	
}
