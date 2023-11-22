import java.util.ArrayList;

public class game {
    public static void main(String[] args) {
        board board = init();
        die die = new die();
        boolean game = true;
        while (game) {
            System.out.println(board);
            for (player player : board.getPlayers()) {
                System.out.println();
                player.moveTo(board.addMoves(board.getIndex(player.getCoords()), 12));
                System.out.println(player.getRow());
                System.out.println(player.getCol());
            }
            break;
        }
        System.out.println(board);
    }

    private static board init() {
        ArrayList<player> players = new ArrayList<>();
        StringBuilder existingIcons = new StringBuilder();
        int numPlayers = CaseCatcher.typeErrorInt("\nHow many players are playing the game? ", "\nThe player has to be an integer");
        while (numPlayers < 2) {
            numPlayers = CaseCatcher.typeErrorInt("\nYou can not have less than 2 players\nHow many players are playing the game? ", "\nThe player hsa to be an integer");
        }
        for (int i = 0; i < numPlayers; i++) {
            String name = CaseCatcher.typeErrorString("\nWhat is the name of player " + (i + 1) + "? ");
            char icon;
            while (true) {
                icon = CaseCatcher.typeErrorChar("\nWhat is the icon of player " + (i + 1) + "? ");
                if (existingIcons.toString().indexOf(icon) == -1) {
                    existingIcons.append(icon);
                    break;
                } else {
                    System.out.println("\nThat icon is already taken");
                }
            }
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