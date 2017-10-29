package com.wtoldt.mememagic;

import com.wtoldt.mememagic.dao.GameDao;
import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GamePhase;
import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.exception.GameNotJoinableException;
import com.wtoldt.mememagic.exception.NoSuchGameException;
import com.wtoldt.mememagic.exception.PlayerAlreadyExistsException;
import com.wtoldt.mememagic.service.GameService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Emily Li on 21/10/2017.
 */
public class GameServiceTest {
    private GameService gameService;

    @Before
    public void setup() {
        final GameDao gameDao = new GameDao();
        gameService = new GameService(gameDao);
    }

    @Test
    public void testCreateGame() throws NoSuchGameException {
        final int gameId = gameService.createGame();
        final Game game = gameService.getGame(gameId);

        assertNotNull(game);
        assertEquals(game.getId(), gameId);
    }

    @Test(expected = NoSuchGameException.class)
    public void testJoinGameForUnknownIdFails() throws NoSuchGameException, GameNotJoinableException, PlayerAlreadyExistsException {
        final int unknownId = 1;
        final String player = "player";
        gameService.joinGame(unknownId, player);
    }

    @Test
    public void testPlayerNotReadyByDefault() throws Exception {
        final int gameId = gameService.createGame();
        final String playerName = "asdf";
        gameService.joinGame(gameId, playerName);
        final Game game = gameService.getGame(gameId);

        final List<Player> players = game.getPlayers();
        assertEquals(1, players.size());

        final Player player = players.get(0);
        assertEquals(false, player.isReady());
    }

    @Test
    public void testReadyPlayer() throws Exception {
        final int gameId = gameService.createGame();
        final String playerName = "asdf";
        gameService.joinGame(gameId, playerName);
        final Game game = gameService.getGame(gameId);
        gameService.setPlayerReady(gameId, playerName, true);

        final List<Player> players = game.getPlayers();
        assertEquals(1, players.size());

        final Player player = players.get(0);
        assertEquals(true, player.isReady());
    }

    @Test
    public void testJoinGamePhase() throws Exception {
        final int gameId = gameService.createGame();
        final Game game = gameService.getGame(gameId);
        assertEquals(GamePhase.JOIN, game.getPhase());
    }
}
