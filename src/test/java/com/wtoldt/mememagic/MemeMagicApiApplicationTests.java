package com.wtoldt.mememagic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wtoldt.mememagic.domain.ApiResponse;
import com.wtoldt.mememagic.domain.GameState;
import com.wtoldt.mememagic.domain.PlayerState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemeMagicApiApplicationTests {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	private String url;
	private String playerName;

	@Before
	public void setup() throws Exception {
		playerName = "asdf";
		url = "http://localhost:" + port + "/api/v1/games/";
	}

	@Test
	public void testCreateGame() {
		final int gameId = createGame();

		final ResponseEntity<ApiResponse> getGameResponse = restTemplate.getForEntity(url + gameId, ApiResponse.class);
		assertTrue(getGameResponse.getBody().getSuccess());
	}

	@Test
	public void testJoinGame() {
		final int gameId = createGame();

		final ResponseEntity<ApiResponse> joinGameResponse = restTemplate.postForEntity(url + gameId + "/players", Collections.singletonMap("playerName", playerName), ApiResponse.class);
		assertTrue(joinGameResponse.getBody().getSuccess());

		final ResponseEntity<ApiResponse> getGameResponse = restTemplate.getForEntity(url + gameId, ApiResponse.class);
		final GameState gameState = OBJECT_MAPPER.convertValue(getGameResponse.getBody().get("game"), GameState.class);

		final List<PlayerState> playerStates = gameState.getPlayers();
		assertEquals(1, playerStates.size());
		assertEquals(playerName, playerStates.get(0).getName());
	}

	@Test
	public void testReadyPlayer() throws Exception {
		// Create game for player to join
		final int gameId = createGame();

		// Join player to game
		final ResponseEntity<ApiResponse> joinGameResponse = restTemplate.postForEntity(url + gameId + "/players", Collections.singletonMap("playerName", playerName), ApiResponse.class);

		// Ready the player
		// There doesn't seem to be a putForEntity so we will check the result of this request in the next request
		restTemplate.put(url + gameId + "/players/" + playerName, Collections.singletonMap("ready", true));

		// Verify player is correctly readied
		final ResponseEntity<ApiResponse> getGameResponse = restTemplate.getForEntity(url + gameId, ApiResponse.class);
		final GameState gameState = OBJECT_MAPPER.convertValue(getGameResponse.getBody().get("game"), GameState.class);
		final PlayerState playerState = gameState.getPlayers().get(0);
		assertTrue(playerState.isReady());
	}

	private int createGame() {
		final ResponseEntity<ApiResponse> createGameResponse = restTemplate.postForEntity(url, null, ApiResponse.class);
		return (int) createGameResponse.getBody().get("gameId");
	}
}
