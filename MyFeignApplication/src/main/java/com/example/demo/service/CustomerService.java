package com.example.demo.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Customer;
import com.example.demo.entity.MyCart;
import com.example.demo.entity.Product;

@FeignClient(name="CustomerMicroservice")
public interface CustomerService {
	
	@RequestMapping(value="/mycart/{pid}/{cid}",  method=RequestMethod.POST)
	public String addToCart(@PathVariable String pid,@PathVariable String cid);
	
	@RequestMapping(value="/savecart/{cid}/{pid}/{quantity}",  method=RequestMethod.GET)
	public String saveCart(@PathVariable String cid,@PathVariable String pid, @PathVariable String quantity);
	@RequestMapping(value="/checkout/{status}/{cid}",  method=RequestMethod.GET)
	public String orderProduct(@PathVariable String cid, @PathVariable String status);
	@RequestMapping(value="/showproductbyid/{pid}",  method=RequestMethod.GET)
	public List<Product> findByProductid(@PathVariable String pid);
	@RequestMapping(value="/viewcart/{cid}", method=RequestMethod.GET) 
	public List<MyCart> viewcart(@PathVariable String cid);
}
