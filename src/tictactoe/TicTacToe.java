package tictactoe;

public class TicTacToe {
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

  public boolean isInBounds(int cellId) {
    return 0 <= cellId && cellId <= 8;
  }

  public boolean isValidPlayer(int playerId) {
    return playerId == 0 || playerId == 1;
  }

  public boolean trySetCell(int cellId, int playerId) {
    if (!isInBounds(cellId) || !isValidPlayer(playerId)) {
      return false;
    }
    int mask = 0b1 << cellId;
    if ((playerPositions[0] & mask) != 0) {
      return false;
    }
    if ((playerPositions[1] & mask) != 0) {
      return false;
    }
    playerPositions[playerId] ^= mask;
    return true;
  }

}
