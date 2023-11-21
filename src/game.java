import java.util.ArrayList;

public class game {
    public static void main(String[] args) {
        board board = init();
        System.out.println(board);
        System.out.println(board.getCoordinates(33)[0]);
    }

    private static board init() {
        ArrayList<player> players = new ArrayList<>();
        int numPlayers = CaseCatcher.typeErrorInt("\nHow many players are playing the game? ", "\nThe player hsa to be an integer");
        while (numPlayers < 2) {
            numPlayers = CaseCatcher.typeErrorInt("\nYou can not have less than 2 players\nHow many players are playing the game? ", "\nThe player hsa to be an integer");
        }
        for (int i = 0; i < numPlayers; i++) {
            String name = CaseCatcher.typeErrorString("\nWhat is the name of player " + (i + 1) + "? ");
            char icon = CaseCatcher.typeErrorChar("\nWhat is the icon of player " + (i + 1) + "? ");
            if (!name.isEmpty()) {
                players.add(new player(icon, name));
            } else {
                players.add(new player(icon));
            }
        }
        board gameBoard = new board(5, 5, players);
        for (int i = 0; i < numPlayers; i++) {
            System.out.println(players.get(i).getName() + " is playing as " + players.get(i).getIcon());
        }
        return gameBoard;
    }
}