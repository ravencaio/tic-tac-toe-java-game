import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private GameConfig config;
    private Board board;
    private List<Player> players;

    public GameEngine(GameConfig config) {
        this.config = config;
        this.board = new Board(config.rows, config.cols, config.placementRule);
        this.players = new ArrayList<>();

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

            if (board.checkWin(turn, config.winCondition)) {
                gameWon = true;
                board.printBoard();
                System.out.printf("Jogador %s venceu! Parabéns!!!\n", turn.playerChar);
                break;
            }

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
            String[] playRaw = scanner.nextLine().trim().split("\\s+");

            try {
                if (playRaw.length == 2) {
                    coordinates[0] = Integer.parseInt(playRaw[0]);
                    coordinates[1] = Integer.parseInt(playRaw[1]);
                    return coordinates;
                } else if (playRaw.length == 1) {
                    coordinates[0] = 1;
                    coordinates[1] = Integer.parseInt(playRaw[0]);
                    return coordinates;
                }
            }
            catch (NumberFormatException e) {

            }

            System.err.println("Entrada errada! Digite 'linha coluna' ou apenas 'coluna'.");
        }
    }
}