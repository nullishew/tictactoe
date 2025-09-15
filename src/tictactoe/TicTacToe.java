package tictactoe;

public class TicTacToe {
  private int[] playerBoards;
  private int totalTurns;
  private int playerTurn;

  public TicTacToe() {
    playerBoards = new int[] { 0, 0 };
    totalTurns = 0;
    playerTurn = 0;
  }

  public int getPlayerPosition(int playerId) {
    return playerBoards[playerId];
  }

  public int getTotalTurns() {
    return totalTurns;
  }

  public int getPlayerTurn() {
    return playerTurn;
  }

}
