public class testGame {
    public static void main(String[] args) {
        board board = game.init();
        System.out.println(board);
        while (true) {
            for (player player : board.getPlayers()) {

                CaseCatcher.typeErrorString("\n" + player.getName() + "'s turn\nType anything to roll the die: ");
                /*die die = new die();
                die.rollDie();
                 */
                int[] coords = board.addMoves(board.getIndex(player.getCoords()), 1);
                player.moveTo(coords);
                int newIndex = board.getIndex(player.getCoords());
                /*
                boolean isZig = false;
                boolean isZag = false;

                for (int i = 0; i < board.getZig().length - 1; i++) {
                    if (newIndex == board.getZig()[i]) {
                        player.moveTo(board.getCoordinates(board.getZig()[i + 1]));
                        isZig = true;
                        break;
                    }
                }
                if (!isZig) {
                    for (int i = 0; i < board.getZag().length; i++) {
                        if (newIndex == board.getZag()[i]) {
                            isZag = true;
                            break;
                        }
                    }
                }*/
                System.out.println(board);
                //System.out.println("You rolled a " + die.getIntValue());
                /*if (isZig) {
                    System.out.println("You landed on a zig space, jumping to the next zig");
                } else if (isZag) {
                    boolean run = true;
                    while (run) {
                        char icon = CaseCatcher.typeErrorChar("You landed on a zag space. Which player do you want to swap with? ");
                        for (player playerSwapped : board.getPlayers()) {
                            if (playerSwapped.getIcon() == icon && playerSwapped != player) {
                                player.swap(playerSwapped);
                                run = false;
                                break;
                            }
                        }
                        if (run) {
                            System.out.println("Can't find the player, Please enter again");
                        }
                    }
                    System.out.println(board);
                    System.out.println("Successfully swapped!");
                }
                 */
                if (board.getIndex(player.getCoords()) == board.getBoardWidth() * board.getBoardHeight() || board.getIndex(player.getCoords()) == -1) {
                    System.out.println(player.getName() + " won!");
                    return;
                } else {
                    System.out.println("You landed on square " + board.getIndex(player.getCoords()));
                }
            }
        }
    }
}
