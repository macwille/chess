public class Player {
    String name;
    public Player() {
        this.name = "player";
    }

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
