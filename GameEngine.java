import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private GameConfig config;
    private Board board;
    private List<Player> players;

    public GameEngine(GameConfig config) {
        this.config = config;
        this.board = new Board(config.boardSize); // Passa apenas o tamanho do tabuleiro
        this.players = new ArrayList<>();

        // Cria os jogadores baseados no arquivo Settings.properties
        for (String symbol : config.playerSymbols) {
            players.add(new Player(symbol.trim()));
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int turnIndex = 0;
        Player turn = players.get(turnIndex);
        boolean gameWon = false;

        while (!gameWon && !board.isDrawn()) {
            board.printBoard();
            System.out.printf("Vez do jogador de símbolo %s, digite linha e coluna: ", turn.playerChar);

            int[] play = readPlay(scanner);
            while (!board.addChar(play[0] - 1, play[1] - 1, turn)) {
                System.err.println("Jogada inválida! Tente novamente.");
                play = readPlay(scanner);
            }

            // A engine agora passa a condição de vitória dinâmica para o tabuleiro
            if (board.checkWin(turn, config.winCondition)) {
                gameWon = true;
                board.printBoard();
                System.out.printf("Jogador %s venceu! Parabéns!!!\n", turn.playerChar);
                break;
            }

            // Alterna para o próximo jogador dinamicamente
            turnIndex = (turnIndex + 1) % players.size();
            turn = players.get(turnIndex);
        }

        if (!gameWon) {
            board.printBoard();
            System.out.println("O jogo empatou!");
        }
    }

    private int[] readPlay(Scanner scanner) {
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