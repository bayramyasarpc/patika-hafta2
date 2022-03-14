import java.util.Scanner;

public abstract class Location {
    public Scanner input= new Scanner(System.in);

    private String name;
    private Player player;

    public Location(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    public abstract boolean onLocation();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
