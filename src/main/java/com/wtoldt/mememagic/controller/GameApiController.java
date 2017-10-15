package com.wtoldt.mememagic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wtoldt.mememagic.service.GameService;

@RestController
@RequestMapping("api/v1/game")
public class GameApiController {

	private GameService gameService;

	@Autowired
	public GameApiController(GameService gameService) {
		this.gameService = gameService;
	}

	@RequestMapping("hello")
	public String helloWorld() {
		return "world";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public int createGame() {
		return gameService.createGame();
	}

}
