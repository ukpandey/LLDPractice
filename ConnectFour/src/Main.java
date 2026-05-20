public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Ujjwal", DiscColor.RED);
        Player player2 = new Player("Divya", DiscColor.YELLOW);

        Game game = new Game(player1, player2);

        // Sequence of moves that makes RED win horizontally on the bottom row.
        //
        // Column numbers: 0 1 2 3 4 5 6
        //
        // RED    -> 0
        // YELLOW -> 0
        // RED    -> 1
        // YELLOW -> 1
        // RED    -> 2
        // YELLOW -> 2
        // RED    -> 3  <-- RED wins

        makeMove(game, player1, 0); // RED
        makeMove(game, player2, 0); // YELLOW

        makeMove(game, player1, 1); // RED
        makeMove(game, player2, 1); // YELLOW

        makeMove(game, player1, 2); // RED
        makeMove(game, player2, 2); // YELLOW

        makeMove(game, player1, 3); // RED wins

        // Final Result
        System.out.println("\n=== Final Result ===");
        System.out.println("Game State: " + game.getGameState());

        if (game.getGameState() == GameState.WON) {
            System.out.println("Winner: " + game.getWinner().getName());
        } else if (game.getGameState() == GameState.DRAW) {
            System.out.println("Game ended in a draw.");
        }

        // Print final board
        printBoard(game.getBoard());
    }

    private static void makeMove(Game game, Player player, int column) {
        System.out.println("\n" + player.getName()
                + " (" + player.getColor() + ") plays column " + column);

        boolean success = game.makeMove(player, column);

        if (!success) {
            System.out.println("Invalid move!");
            return;
        }

        printBoard(game.getBoard());
        System.out.println("Game State: " + game.getGameState());

        if (game.getGameState() == GameState.IN_PROGRESS) {
            System.out.println("Next Turn: " + game.getCurrentPlayer().getName());
        }
    }

    private static void printBoard(Board board) {
        System.out.println();

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                DiscColor cell = board.getCell(row, col);

                char symbol = '.';
                if (cell == DiscColor.RED) {
                    symbol = 'R';
                } else if (cell == DiscColor.YELLOW) {
                    symbol = 'Y';
                }

                System.out.print(symbol + " ");
            }
            System.out.println();
        }

        // Column indices for reference
        System.out.println("0 1 2 3 4 5 6");
    }
}