package com.wtoldt.mememagic;

import com.wtoldt.mememagic.dao.GameDao;
import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.exception.GameNotJoinableException;
import com.wtoldt.mememagic.exception.NoSuchGameException;
import com.wtoldt.mememagic.exception.NoSuchPlayerException;
import com.wtoldt.mememagic.exception.PlayerAlreadyExistsException;
import com.wtoldt.mememagic.service.GameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

        Assert.assertNotNull(game);
        Assert.assertEquals(game.getId(), gameId);
    }

    @Test(expected = NoSuchGameException.class)
    public void testJoinGameForUnknownIdFails() throws NoSuchGameException, GameNotJoinableException, PlayerAlreadyExistsException {
        final int unknownId = 1;
        final String player = "player";
        gameService.joinGame(unknownId, player);
    }

    @Test(expected = NoSuchPlayerException.class)
    public void testNoSuchPlayerExceptionWhenAttemptingToReady() throws NoSuchGameException, NoSuchPlayerException {
        final int gameId = gameService.createGame();
        gameService.setPlayerReady(gameId, "player", true);
    }
}
