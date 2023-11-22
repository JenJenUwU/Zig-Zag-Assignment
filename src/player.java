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

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setCoords(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void moveTo(int[] coords) {
        this.row = coords[0];
        this.col = coords[1];
    }

}
