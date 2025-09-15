package tictactoe;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicTacToeTest {
  @Test
  public void testTicTacToeInitialization() {
    TicTacToe ticTacToe = new TicTacToe();
    assertEquals(ticTacToe.getPlayerPosition(0), 0);
    assertEquals(ticTacToe.getPlayerPosition(1), 0);
  }
}
