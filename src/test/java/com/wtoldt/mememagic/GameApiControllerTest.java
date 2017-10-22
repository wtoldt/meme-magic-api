package com.wtoldt.mememagic;

import com.wtoldt.mememagic.config.MemeMagicApiConfig;
import com.wtoldt.mememagic.controller.GameApiController;
import com.wtoldt.mememagic.domain.JoinGameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

/**
 * Created by Emily Li on 21/10/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MemeMagicApiConfig.class})
public class GameApiControllerTest {
    @Autowired
    private GameApiController gameApiController;

    @Test(expected = ConstraintViolationException.class)
    public void testInvalidPlayerNameValidation() throws Exception {
        testJoinGameNameLimit(51);
    }

    @Test
    public void testValidPlayerNameValidation() throws Exception {
        testJoinGameNameLimit(50);
    }

    private void testJoinGameNameLimit(final int nameLength) throws Exception {
        StringBuilder playerName = new StringBuilder();
        for (int i = 0; i < nameLength ; i++) {
            playerName.append('.');
        }

        final int gameId = gameApiController.createGame();
        final JoinGameRequest joinGameRequest = new JoinGameRequest();
        joinGameRequest.setPlayer(playerName.toString());

        gameApiController.joinGame(gameId, joinGameRequest);
    }
    
    @Test
    public void testJoinGame() {
        // 1. joinGame(..) should accept gameId and playerName
        // 2. playerName should be validated (between 1 - 50 chars)
        // 3. gameId should be validated - it needs to exist
        // 4. gameId should be validated - it should be open
        // 5. verify player in game
    }
}
