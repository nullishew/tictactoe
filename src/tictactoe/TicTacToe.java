package tictactoe;

public class TicTacToe {
  public static final int FULL_POSITION = 0b111111111;
  private int[] playerPositions;
  private int totalTurns;
  private int playerTurn;

  public TicTacToe() {
    playerPositions = new int[] { 0b000000000, 0b000000000 };
    totalTurns = 0;
    playerTurn = 0;
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

}
