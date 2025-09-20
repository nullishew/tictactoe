package tictactoe;

public class TicTacToe {
  public static final int FULL_POSITION = 0b111111111;
  public static final int[] WIN_MASKS = {
      0b000000111, // row 1 _
      0b000111000, // row 2 _
      0b111000000, // row 3 _
      0b001001001, // col 1 |
      0b010010010, // col 2 |
      0b100100100, // col 3 |
      0b001010100, // diagonal 1 /
      0b100010001, // diagonal 2 \
  };

  private int[] playerPositions;
  private int totalTurns;
  private int playerTurn;

  public TicTacToe() throws InvalidBoardException {
    this(0, 0);
  }

  // the bitboards can start at any number, but only the first 9 bits are relevant
  public TicTacToe(int playerPosition0, int playerPosition1) throws InvalidBoardException {
    if (!isValidPosition(playerPosition0, playerPosition1)) {
      throw new InvalidBoardException("The starting TicTacToe position is not valid");
    }
    playerPositions = new int[] { playerPosition0, playerPosition1 };
    totalTurns = 0;
    playerTurn = 0;
  }

  public static boolean isValidPosition(int playerPosition0, int playerPosition1) {
    int[] positions = new int[] { playerPosition0, playerPosition1 };
    for (int pos : positions) {
      if (pos < 0 || pos > FULL_POSITION) {
        return false;// number is too large or small to represent tictactoe board
      }
    }

    int[] turns = new int[] { Integer.bitCount(playerPosition0), Integer.bitCount(playerPosition1) };
    if (turns[0] < turns[1]) {
      return false; // player 1 has skipped player 0's turn at least once
    }
    if (turns[1] + 1 < turns[0]) {
      return false; // player 0 has skipped player 1's turn at least once
    }

    if ((playerPosition0 & playerPosition1) != 0) {
      return false; // overlap found
    }

    return true; // no issues found
  }

  public int getPlayerPosition(int playerId) {
    return playerPositions[playerId];
  }

  public int getTotalTurns() {
    return totalTurns;
  }

  public int getPlayerTurn() {
    return playerTurn;
  }

  // check if cell mask is in bounds
  public boolean isInBounds(int cellMask) {
    return (cellMask & ~FULL_POSITION) == 0;
  }

  public boolean isValidPlayer(int playerId) {
    return (playerId & ~0b1) == 0;
  }

  public boolean trySetCellId(int cellId, int playerId) {
    return trySetCellMask(0b1 << cellId, playerId);
  }

  public boolean trySetCellMask(int cellMask, int playerId) {
    if (!isInBounds(cellMask) || !isValidPlayer(playerId)) {
      return false;
    }
    int occupied = playerPositions[0] | playerPositions[1];
    if ((occupied & cellMask) != 0) {
      return false;
    }
    playerPositions[playerId] ^= cellMask;
    return true;
  }

  public boolean isWin(int playerId) {
    if (!isValidPlayer(playerId)) {
      return false;
    }
    for (int mask : WIN_MASKS) {
      if ((playerPositions[playerId] & mask) == mask) {
        return true;
      }
    }
    return false;
  }

}
