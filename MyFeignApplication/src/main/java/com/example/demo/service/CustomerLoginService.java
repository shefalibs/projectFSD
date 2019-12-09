package com.example.demo.service;

import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Customer;
import com.example.demo.entity.LoginModel;
@FeignClient(name="CustomerLoginMicroservice")
public interface CustomerLoginService {
	
	@LoadBalanced
	@PostMapping("/register")
	public List<String> createCustomer(@RequestBody Customer c);
	
	@LoadBalanced
	@RequestMapping("/login")
	public String loginCustomer(@RequestBody LoginModel request);
	
	@LoadBalanced
	@GetMapping("/findcustomer/{cid}")
	public List<Customer> findByCustomeremail(@PathVariable String cid);
	
	@LoadBalanced
	@GetMapping("/findcustomerid/{cid}")
	public List<Customer> findByCustomerid(@PathVariable String cid);
}
