package tictactoe;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicTacToeTest {
  @Test(timeout = 10000)
  public void testTicTacToeInitialization() {
    TicTacToe ticTacToe = new TicTacToe();
    assertEquals(ticTacToe.getPlayerPosition(0), 0);
    assertEquals(ticTacToe.getPlayerPosition(1), 0);
  }

  @Test(timeout = 10000)
  public void testIsInBounds() {
    TicTacToe ticTacToe = new TicTacToe();
    // test valid corresponding cell bitmasks
    assertEquals(true, ticTacToe.isInBounds(0));
    int mask = 0b1;
    while (mask <= TicTacToe.FULL_POSITION) {
      assertEquals(true, ticTacToe.isInBounds(mask));
      mask <<= 1;
    }
    assertEquals(true, ticTacToe.isInBounds(0b000000111));
    assertEquals(true, ticTacToe.isInBounds(0b111000000));
    assertEquals(true, ticTacToe.isInBounds(0b101000100));
    assertEquals(true, ticTacToe.isInBounds(0b100111100));
    assertEquals(true, ticTacToe.isInBounds(0b111111111));

    // Test invalid corresponding cell bitmasks
    assertEquals(false, ticTacToe.isInBounds(0b1000000000));
    assertEquals(false, ticTacToe.isInBounds(0b1111111110));
    assertEquals(false, ticTacToe.isInBounds(0b1110000000));
    assertEquals(false, ticTacToe.isInBounds(0b1111111111));
    assertEquals(false, ticTacToe.isInBounds(0b10000000000));
    assertEquals(false, ticTacToe.isInBounds(0b1000000010));
    assertEquals(false, ticTacToe.isInBounds(0b1000111010));
    assertEquals(false, ticTacToe.isInBounds(1234567890));
    assertEquals(false, ticTacToe.isInBounds(Integer.MAX_VALUE));

  }

  @Test(timeout = 10000)
  public void testIsValidPlayer() {
    TicTacToe ticTacToe = new TicTacToe();
    // test valid players (0 - 1 inclusive)
    assertEquals(true, ticTacToe.isValidPlayer(0));
    assertEquals(true, ticTacToe.isValidPlayer(1));
    // test invalid players
    for (int i = Integer.MIN_VALUE; i <= -1; i++) {
      assertEquals(false, ticTacToe.isValidPlayer(i));
    }
    for (int i = 2; i < Integer.MAX_VALUE; i++) {
      assertEquals(false, ticTacToe.isValidPlayer(i));
    }

  }

  @Test(timeout = 10000)
  public void testTrySetCell() {
    // test placing pieces out of bounds
    TicTacToe ticTacToe = new TicTacToe();
    assertEquals(false, ticTacToe.trySetCellId(-1, 0));
    assertEquals(false, ticTacToe.trySetCellId(-1, 1));
    assertEquals(false, ticTacToe.trySetCellId(9, 0));
    assertEquals(false, ticTacToe.trySetCellId(9, 1));

    // test placing pieces for a player that may or may not exist
    ticTacToe = new TicTacToe();
    assertEquals(true, ticTacToe.trySetCellId(0, 0));
    ticTacToe = new TicTacToe();
    assertEquals(true, ticTacToe.trySetCellId(0, 1));
    ticTacToe = new TicTacToe();
    assertEquals(false, ticTacToe.trySetCellId(0, -1));
    ticTacToe = new TicTacToe();
    assertEquals(false, ticTacToe.trySetCellId(0, 2));
    ticTacToe = new TicTacToe();
    assertEquals(false, ticTacToe.trySetCellId(0, 67));

    // test setting empty cells to player 0
    ticTacToe = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(true, ticTacToe.trySetCellId(cellId, 0));
    }
    // test setting empty cells to player 0 then attempting to set cells player
    // player 0 and then player 1
    ticTacToe = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(true, ticTacToe.trySetCellId(cellId, 0));
      assertEquals(false, ticTacToe.trySetCellId(cellId, 0));
      assertEquals(false, ticTacToe.trySetCellId(cellId, 1));
    }
    // test setting empty cells to player 0 then attempting to set cells player
    // player 1 and then player 0
    ticTacToe = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(true, ticTacToe.trySetCellId(cellId, 0));
      assertEquals(false, ticTacToe.trySetCellId(cellId, 1));
      assertEquals(false, ticTacToe.trySetCellId(cellId, 0));
    }

    // test setting empty cells to player 1
    TicTacToe ticTacToe2 = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(true, ticTacToe2.trySetCellId(cellId, 1));
    }

    // test setting empty cells to player 1 then attempting to set cells player
    // player 0 and then player 1
    ticTacToe2 = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(true, ticTacToe2.trySetCellId(cellId, 1));
      assertEquals(false, ticTacToe2.trySetCellId(cellId, 0));
      assertEquals(false, ticTacToe2.trySetCellId(cellId, 1));
    }

    // test setting empty cells to player 1 then attempting to set cells player
    // player 1 and then player 0
    ticTacToe2 = new TicTacToe();
    for (int cellId = 0; cellId < 9; cellId++) {
      assertEquals(true, ticTacToe2.trySetCellId(cellId, 1));
      assertEquals(false, ticTacToe2.trySetCellId(cellId, 1));
      assertEquals(false, ticTacToe2.trySetCellId(cellId, 0));
    }
  }
}
