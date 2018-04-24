package tw.controllers;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private GameController gameController;
    private AnswerGenerator answerGenerator;
    private InputCommand inputGuess;
    private Answer answer;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws OutOfRangeAnswerException {
        Answer actualAnswer=Answer.createAnswer("1 2 3 4");
        answerGenerator = mock(AnswerGenerator.class);
        inputGuess = mock(InputCommand.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        gameController = new GameController(new Game(answerGenerator),new GameView());
        System.setOut(new PrintStream(outContent));
        answer = new Answer();
    }

    @Test
    public void should_game_begin_then_retrun_the_same_output() throws Exception {
        gameController.beginGame();
        assertThat(outContent.toString(), CoreMatchers.startsWith("------Guess Number Game, You have 6 chances to guess!  ------"));

    }
    @Test
    public void should_game_success_then_return_the_status() throws Exception {
        Game game = mock(Game.class);
        when(game.checkCoutinue()).thenReturn(false);
        when(game.checkStatus()).thenReturn("success");
        assertEquals("success", game.checkStatus());
    }
    @Test
    public void should_game_end_then_return_the_status() throws Exception {
        Game game = mock(Game.class);
        when(game.checkCoutinue()).thenReturn(false);
        when(game.checkStatus()).thenReturn("fail");
        assertEquals("fail", game.checkStatus());
    }
}