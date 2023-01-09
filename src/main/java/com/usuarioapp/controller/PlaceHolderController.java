package com.usuarioapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.usuarioapp.entity.PlaceHolder;

@RestController
public class PlaceHolderController {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public PlaceHolderController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@GetMapping("/placeholder")
	public PlaceHolder getApi() {
		String url = "https://jsonplaceholder.typicode.com/posts/1";
		return restTemplate.getForObject(url, PlaceHolder.class);
	}
	
	@GetMapping("/placeholder/list")
	public List<PlaceHolder> getListApi() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		PlaceHolder[] placeHolderList = restTemplate.getForObject(url, PlaceHolder[].class);
		return Arrays.asList(placeHolderList);
	}
}
