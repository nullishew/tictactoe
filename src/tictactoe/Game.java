package tictactoe;

import java.util.Scanner;

public class Game {
  public static void main(String[] args) throws Exception {
    Scanner scan = new Scanner(System.in);

    TicTacToe ticTacToe = new TicTacToe();
    while (true) {
      if (ticTacToe.getTotalTurns() >= 9) {
        System.out.println("nobody wins!");
        break;
      }
      if (ticTacToe.isWin(0)) {
        System.out.println("o loses!");
        break;
      }
      if (ticTacToe.isWin(1)) {
        System.out.println("x loses!");
        break;
      }

      System.out.println("0 1 2\n3 4 5\n6 7 8\n");
      System.out.println(ticTacToe);
      System.out.println("It is player's " + ("xo".charAt(ticTacToe.getPlayerTurn())) + " turn");

      int cellId = -1;
      while (true) {
        if (ticTacToe.trySetCellId(cellId, ticTacToe.getPlayerTurn())) {
          break;
        }
        cellId = scan.nextInt();
      }

    }
    scan.close();
  }
}
