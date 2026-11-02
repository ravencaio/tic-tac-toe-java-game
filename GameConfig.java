import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GameConfig {
    public int rows;
    public int cols;
    public String[] playerSymbols;
    public int winCondition;
    public PlacementRule placementRule;

    public void loadFromFile(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream(filePath)) {
            properties.load(file);

            this.rows = Integer.parseInt(properties.getProperty("linhas", "3"));
            this.cols = Integer.parseInt(properties.getProperty("colunas", "3"));
            this.playerSymbols = properties.getProperty("jogadores", "X,O").split(",");
            this.winCondition = Integer.parseInt(properties.getProperty("condicao_vitoria", "3"));

            String ruleType = properties.getProperty("regra_posicionamento", "NORMAL");
            if (ruleType.equals("GRAVIDADE")) {
                this.placementRule = new GravityPlacement();
            } else {
                this.placementRule = new NormalPlacement();
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar ficheiro: " + e.getMessage());
            System.exit(1);
        }
    }
}