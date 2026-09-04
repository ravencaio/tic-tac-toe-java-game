import java.util.Scanner;

class Main {
    public static void main(){
        Scanner scnr = new Scanner(System.in);

        Player pl1 = new Player("O", -1);
        Player pl2 = new Player("X", 1);

        Player vez = pl1;

        Board board = new Board(3, pl1, pl2);

        while (board.playerWon() == null && !board.isNotDrawn()) {
            board.printBoard();
            System.out.printf("Vez do jogador de símbolo %s, digite linha e coluna: ", vez.playerChar);
            String[] jogada = scnr.nextLine().split(" ");
            int jogadaX = Integer.parseInt(jogada[0]);
            int jogadaY = Integer.parseInt(jogada[1]);

            while (!board.addChar(jogadaX - 1, jogadaY - 1, vez)) {
                    System.err.printf("Jogada inválida!, tente novamente\n");
                    jogada = scnr.nextLine().split(" ");
                    jogadaX = Integer.parseInt(jogada[0]);
                    jogadaY = Integer.parseInt(jogada[1]);
            }
            vez = (vez == pl1) ? pl2 : pl1;
        }
        if (board.playerWon() != null) {
            board.printBoard();
            System.out.printf("Jogador %s venceu! Parabéns!!!", board.playerWon().playerChar);
            return;
        }
        board.printBoard();
        System.out.printf("O jogo empatou!");
    }



}