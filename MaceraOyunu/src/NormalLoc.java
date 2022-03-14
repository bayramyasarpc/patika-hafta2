public abstract class NormalLoc extends Location {


    public NormalLoc(String name, Player player) {
        super(name, player);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}
