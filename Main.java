import java.util.Scanner;

class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Player pl1 = new Player("O");
        Player pl2 = new Player("X");

        Player turn = pl1;

        Board board = new Board(pl1, pl2, 4);

        while (board.playerWon() == null && !board.isDrawn()) {
            board.printBoard();
            System.out.printf("Vez do jogador de símbolo %s, digite linha e coluna: ", turn.playerChar);
            int[] play = readPlay(scanner);
            while (!board.addChar(play[0]- 1, play[1] - 1, turn)) {
                System.err.println("Jogada inválida! Tente novamente.");
                play = readPlay(scanner);
            }
            turn = (turn == pl1) ? pl2 : pl1;
        }
        if (board.playerWon() != null) {
            board.printBoard();
            System.out.printf("Jogador %s venceu! Parabéns!!!", board.playerWon().playerChar);
            return;
        }
        board.printBoard();
        System.out.printf("O jogo empatou!");
    }

    private static int[] readPlay(Scanner scanner){
        int[] coordinates = new int[2];
        while (true) {
            String[] playRaw = scanner.nextLine().split(" ");
            if (playRaw.length == 2) {
                try {
                    coordinates[0] = Integer.parseInt(playRaw[0]);
                    coordinates[1] = Integer.parseInt(playRaw[1]);
                    return coordinates;
                } catch (NumberFormatException e) {

                }

            }

            System.err.println("Entrada errada! tente novamente.");
        }
    }


}