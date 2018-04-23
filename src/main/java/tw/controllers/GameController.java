package tw.controllers;

import com.google.inject.Inject;
import tw.core.Game;
import tw.commands.InputCommand;
import tw.views.GameView;

import java.io.IOException;

public class GameController {
    private final Game game;
    private final GameView gameView;
    private final InputCommand command;


    @Inject
    public GameController(Game game, GameView gameView, InputCommand command) {
        this.game = game;
        this.gameView = gameView;
        this.command = command;
    }

    public void beginGame() throws IOException {
        gameView.showBegin();
    }

    public void play() throws IOException {
        if (game.checkCoutinue()) {
            guessAndshowResultAndHistory();
            play();
        } else {
            gameView.showGameStatus(game.checkStatus());
        }
    }

    private void guessAndshowResultAndHistory() throws IOException {
        gameView.showGuessResult(game.guess(command.input()));
        gameView.showGuessHistory(game.guessHistory());
    }

}
