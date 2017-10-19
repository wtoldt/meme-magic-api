package com.wtoldt.mememagic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.service.GameService;

@RestController
@RequestMapping("api/v1/games")
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

	@RequestMapping(value="", method=RequestMethod.POST)
	public int createGame() {
		return gameService.createGame();
	}

	@RequestMapping(value="/{id}/players", method=RequestMethod.POST)
	public String joinGame(@PathVariable final int id, @Valid Player player) {
		String response = "success";

		return response;
	}

}
