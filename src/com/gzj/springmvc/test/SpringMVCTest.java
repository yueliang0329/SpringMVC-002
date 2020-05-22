package com.gzj.springmvc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gzj.springmvc.crud.dao.EmployeeDao;
import com.gzj.springmvc.crud.entities.Employee;

@Controller
public class SpringMVCTest {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/testConversionServiceConverer")
	public String testConverter(@RequestParam("employee") Employee employee){
		System.out.println("save: " + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson()
	{
		return employeeDao.getAll();
	}
	
	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body){
		System.out.println(body);
		return "helloworld! " + new Date();
	}
	
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException
	{
		byte [] body =null ;
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/files/abc.txt");
		body = new byte[in.available()];
		in.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");	
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	@RequestMapping("/i18n")
	public String testI18n(Locale locale){
		String val = messageSource.getMessage("i18n.user", null, locale);
		System.out.println(val); 
		return "i18n";
	}
}
