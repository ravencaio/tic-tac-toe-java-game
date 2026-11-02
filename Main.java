public class Main {
    public static void main(String[] args) {
        GameConfig config = new GameConfig();
        config.loadFromFile("Settings.properties");

        GameEngine engine = new GameEngine(config);
        engine.start();
    }
}