package com.wtoldt.mememagic;

import com.wtoldt.mememagic.dao.GameDao;
import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.service.GameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Emily Li on 21/10/2017.
 */
public class GameServiceTest {
    private GameService gameService;

    @Before
    public void setup() {
        GameDao gameDao = new GameDao();
        gameService = new GameService(gameDao);
    }

    @Test
    public void testCreateGame() {
        final int gameId = gameService.createGame();
        Game game = gameService.getGame(gameId);

        Assert.assertNotNull(game);
        Assert.assertEquals(game.getId(), gameId);
    }
}
