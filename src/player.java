public class player {
    private char icon;
    private String name;
    private int row;
    private int col;
    private static int numPlayers = 0;
    private static int numGuests = 0;

    public player(char icon, String name) {
        numPlayers++;
        this.name = name;
        this.icon = icon;
        this.row = 0;
        this.col = 0;
    }

    public player(char icon) {
        numPlayers++;
        numGuests++;
        this.name = "Player " + numGuests;
        this.icon = icon;
        this.row = 0;
        this.col = 0;
    }

    public char getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int[] getCoords() {
        return new int[]{row, col};
    }

    public void moveTo(int[] coords) {
        this.row = coords[0];
        this.col = coords[1];
    }

    public void swap(player player) {
        int[] temp = player.getCoords();
        player.moveTo(this.getCoords());
        this.moveTo(temp);
    }

    public static void main(String[] args) {
        player player = new player('a', "test");
        System.out.println(player.getIcon());
        System.out.println(player.getName());
        System.out.println();
        System.out.println(player.getRow());
        System.out.println(player.getCol());
        System.out.println();
        System.out.println(player.getCoords()[0]);
        System.out.println(player.getCoords()[1]);
        player.moveTo(new int[]{1, 1});
        System.out.println();
        System.out.println(player.getRow());
        System.out.println(player.getCol());
        System.out.println();
        System.out.println(player.getCoords()[0]);
        System.out.println(player.getCoords()[1]);
    }

}
