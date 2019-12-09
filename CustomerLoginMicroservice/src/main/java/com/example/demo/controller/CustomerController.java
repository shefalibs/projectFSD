package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.entity.LoginModel;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired CustomerService customerService;
	
	
	@PostMapping("/register")
	public List<String> createCustomer(@RequestBody Customer c){
		List<String> cid=new ArrayList<>();
		String status=customerService.createCustomer(c);
		if(status==null) {
			cid.add("userexist");
		}
		cid.add(status);
		return cid;
	}
	
	@GetMapping("/findcustomer/{cid}")
	public List<Customer> findByCustomeremail(@RequestBody @PathVariable String cid){
		return customerService.findByCustomeremail(cid);
	}

	@GetMapping("/findcustomerid/{cid}")
	public List<Customer> findByCustomerid(@RequestBody @PathVariable String cid){
		return customerService.findByCustomerid(cid);
	}
	
	@PostMapping("/login")
	public String loginCustomer(@RequestBody LoginModel request ){
		String cemail=request.getEmail();
		System.out.println(cemail);
		String cpass = request.getPassword();
		List<Customer> customerlist = customerService.verifyUser(cemail,cpass);
		if(customerlist!=null) {
			return cemail;
		}
		else return "Wrong Credentials";
	}
}
