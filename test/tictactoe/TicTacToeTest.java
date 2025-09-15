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

  @Test
  public void testTrySetCell() {
    // test placing pieces out of bounds
    TicTacToe ticTacToe = new TicTacToe();
    assertEquals(ticTacToe.trySetCell(-1, 0), false);
    assertEquals(ticTacToe.trySetCell(-1, 1), false);
    assertEquals(ticTacToe.trySetCell(9, 0), false);
    assertEquals(ticTacToe.trySetCell(9, 1), false);

    // test placing pieces for a player that does not exist
    ticTacToe = new TicTacToe();
    assertEquals(ticTacToe.trySetCell(0, 0), true);
    ticTacToe = new TicTacToe();
    assertEquals(ticTacToe.trySetCell(0, -1), false);
    ticTacToe = new TicTacToe();
    assertEquals(ticTacToe.trySetCell(0, 2), false);
    ticTacToe = new TicTacToe();
    assertEquals(ticTacToe.trySetCell(0, 67), false);

    // test setting empty cells to player 0
    ticTacToe = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(ticTacToe.trySetCell(cellId, 0), true);
    }
    // test setting empty cells to player 0 then attempting to set cells player
    // player 0 and then player 1
    ticTacToe = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(ticTacToe.trySetCell(cellId, 0), true);
      assertEquals(ticTacToe.trySetCell(cellId, 0), false);
      assertEquals(ticTacToe.trySetCell(cellId, 1), false);
    }
    // test setting empty cells to player 0 then attempting to set cells player
    // player 1 and then player 0
    ticTacToe = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(ticTacToe.trySetCell(cellId, 0), true);
      assertEquals(ticTacToe.trySetCell(cellId, 1), false);
      assertEquals(ticTacToe.trySetCell(cellId, 0), false);
    }

    // test setting empty cells to player 1
    TicTacToe ticTacToe2 = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(ticTacToe2.trySetCell(cellId, 1), true);
    }

    // test setting empty cells to player 1 then attempting to set cells player
    // player 0 and then player 1
    ticTacToe2 = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(ticTacToe2.trySetCell(cellId, 1), true);
      assertEquals(ticTacToe2.trySetCell(cellId, 0), false);
      assertEquals(ticTacToe2.trySetCell(cellId, 1), false);
    }

    // test setting empty cells to player 1 then attempting to set cells player
    // player 1 and then player 0
    ticTacToe2 = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(ticTacToe2.trySetCell(cellId, 1), true);
      assertEquals(ticTacToe2.trySetCell(cellId, 1), false);
      assertEquals(ticTacToe2.trySetCell(cellId, 0), false);
    }
  }
}
