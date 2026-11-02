import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GameConfig {
    public int boardSize;
    public String[] playerSymbols;
    public int winCondition;

    public void loadFromFile(String filePath) {
        Properties properties = new Properties();

        try (FileInputStream file = new FileInputStream(filePath)) {
            properties.load(file);

            this.boardSize = Integer.parseInt(properties.getProperty("tamanho_tabuleiro", "3"));
            this.playerSymbols = properties.getProperty("jogadores", "X,O").split(",");
            this.winCondition = Integer.parseInt(properties.getProperty("condicao_vitoria", "3"));

        } catch (IOException e) {
            System.err.println("Erro ao carregar o ficheiro de configuração: " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Erro de formatação. Verifique se os valores do tabuleiro e vitória são numéricos.");
            System.exit(1);
        }
    }
}