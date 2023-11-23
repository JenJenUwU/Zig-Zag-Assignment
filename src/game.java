import java.util.ArrayList;

public class game {
    public static void main(String[] args) {
        board board = init();
        die die = new die();
        boolean game = true;
        System.out.println(board);
        while (game) {
            for (player player : board.getPlayers()) {
                CaseCatcher.typeErrorString("\n" + player.getName() + "'s turn\nType anything to roll the die: ");
                die.rollDie();

                int[] coords = board.addMoves(board.getIndex(player.getCoords()), die.getIntValue());
                player.moveTo(coords);
                int newIndex = board.getIndex(player.getCoords());
                boolean isZig = false;

                for (int i = 0; i < board.getZig().length; i++) {
                    if (newIndex == board.getZig()[i]) {
                        player.moveTo(board.getCoordinates(board.getZig()[i + 1]));
                        isZig = true;
                        break;
                    }
                }
                System.out.println(board);
                System.out.println("You rolled a " + die.getIntValue());
                if (isZig) {
                    System.out.println("You landed on a zig space and moved to square" + board.getIndex(player.getCoords()));
                }
                if (board.getIndex(player.getCoords()) == board.getBoardWidth() * board.getBoardHeight()) {
                    System.out.println(player.getName() + " won!");
                    game = false;
                    break;
                }
            }
        }
    }

    private static board init() {
        ArrayList<player> players = new ArrayList<>();
        StringBuilder existingIcons = new StringBuilder("Zz");
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
        System.out.println();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println(players.get(i).getName() + " is playing as " + players.get(i).getIcon());
        }
        return gameBoard;
    }
}