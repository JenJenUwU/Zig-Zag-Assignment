public class player {
    private String icon;
    private String name;
    private int row;
    private int col;
    private static int numPlayers = 0;
    private static int numGuests = 1;
    public player(String icon, String name){
        this.name = name;
        this.icon = icon;
        this.row = 0;
        this.col = 0;
        numPlayers++;
    }
    public player(String icon){
        this.name = "Player " + numGuests;
        this.icon = icon;
        this.row = 0;
        this.col = 0;
        numPlayers++;
        numGuests++;
    }
    public String getIcon(){
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

    public static int getNumPlayers() {
        return numPlayers;
    }
}
