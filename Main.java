import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "";

        System.out.println("=================================");
        System.out.println("       BOARD GAME ENGINE         ");
        System.out.println("=================================");
        System.out.println("Selecione o jogo que deseja jogar:");
        System.out.println("1 - Jogo da Velha");
        System.out.println("2 - Connect Four");
        System.out.print("Opção: ");

        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                filePath = "tic-tac-toe.properties";
                break;
            case "2":
                filePath = "connect4.properties";
                break;
            default:
                System.out.println("Opção inválida. A carregar o Jogo da Velha por defeito...");
                filePath = "velha.properties";
                break;
        }

        GameConfig config = new GameConfig();
        config.loadFromFile(filePath);

        System.out.println("\nA iniciar o jogo a partir da configuração: " + filePath + "\n");

        GameEngine engine = new GameEngine(config);
        engine.start();
    }
}