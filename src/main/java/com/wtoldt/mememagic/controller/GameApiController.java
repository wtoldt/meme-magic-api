package com.wtoldt.mememagic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/game")
public class GameApiController {
	
	@RequestMapping("hello")
	public String helloWorld() {
		return "world";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String createGame() {
		return "foo";
	}

}
