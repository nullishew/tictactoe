package tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class TicTacToeTest {
  @Test(timeout = 10000)
  public void testTicTacToeInitialization() throws InvalidBoardException {
    TicTacToe ticTacToe = new TicTacToe();
    assertEquals(0, ticTacToe.getPlayerPosition(0));
    assertEquals(0, ticTacToe.getPlayerPosition(1));
    assertEquals(0, ticTacToe.getPlayerTurn());
    assertEquals(0, ticTacToe.getTotalTurns());

    int invalidPositions[][] = new int[][] {
        { 999999999, 99999999 }, // number out of bounds
        { -1, 0 }, // number out of bounds
        { 0b000001000, 0b000001000 }, // overlap
        { 0b000000100, 0b000011000 }, // player 1 has more moves than player 0
        { 0b0000001111, 0b000000000 }, // player 0 has more moves than player 1
        { 0b1000000000, 0b000000100 },
        { 0b1111111110, 0b000000000 },
        { 0b000010000, 0b000010000 },
        { 0b1111111111, 0b000000000 },
        { 0b000010000, 0b000011000 },
        { 0b000001000, 0b000001000 },
        { 0b111111111, 0b1000000000 },
        { 0b000000111, 0b000000011 },
        { 0b10000000000, 0b000000000 },
        { 0b1111111111, 0b111111111 },
        { 0b0000000010, 0b0000000010 },
        { 0b000001000, 0b000001001 },
        {0b111000000, 0b000111000}, // test player 1 player move after player 0 wins
        {0b101000101, 0b000111000}, // test player 0 plays move after player 1 wins
    };
    for (int[] positions : invalidPositions) {
      InvalidBoardException exception = assertThrows(InvalidBoardException.class, () -> {
        new TicTacToe(positions[0], positions[1]);
      });
      assertEquals("The starting TicTacToe position is not valid", exception.getMessage());
    }

    // test totalTurns later
    // test playerTurn later
  }

  @Test(timeout = 10000)
  public void testIsInBounds() throws InvalidBoardException {
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
  public void testIsValidPlayer() throws InvalidBoardException {
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
  public void testTrySetCell() throws InvalidBoardException {
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

  @Test
  public void testIsWin() throws InvalidBoardException {
    // tests to be added later
    assertEquals(true, false);

  }
}
