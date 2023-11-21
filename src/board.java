import java.util.ArrayList;

public class board {
    private final int boardWidth;
    private final int boardHeight;
    private String[][] board;
    private ArrayList<player> players;

    public board(int width, int height, ArrayList<player> players) {
        boardWidth = width;
        boardHeight = height;
        this.players = players;
        board = new String[boardHeight][boardWidth];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = String.valueOf(i * boardWidth + j + 1);
            }
        }
    }

    private String formatSpace(String item, int num) {
        StringBuilder itemBuilder = new StringBuilder(item);
        while (itemBuilder.length() < String.valueOf(boardWidth * boardHeight).length() || itemBuilder.length() < num) {
            itemBuilder.insert(0, " ");
        }
        item = itemBuilder.toString();
        return item;
    }

    public int[] getCoordinates(int space) {
        int[] coordinates = new int[2];
        if (space <= boardWidth * boardHeight) {
            coordinates[1] = (space - 1) % boardWidth;//y
            coordinates[0] = (space - 1) / boardWidth;//x
        } else {
            coordinates[0] = -1;
            coordinates[1] = -1;
        }
        return coordinates;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (int col = 0; col < boardHeight; col++) {
            for (int row = 0; row < boardWidth; row++) {
                StringBuilder temp;
                boolean spaceHasPlayer = false;
                if (col % 2 == 0) {
                    temp = new StringBuilder(board[col][row]);
                } else {
                    temp = new StringBuilder(board[col][boardWidth - row - 1]);
                }
                for (player player : players) {
                    if (player.getRow() == row && player.getCol() == col && !spaceHasPlayer) {
                        temp = new StringBuilder(String.valueOf(player.getIcon()));
                        spaceHasPlayer = true;
                    } else if (spaceHasPlayer) {
                        temp.append(player.getIcon());
                    }
                }
                temp = new StringBuilder(formatSpace(temp.toString(), players.size()));
                if (row != board[col].length - 1) {
                    returnString.append(temp).append(" ");
                } else {
                    returnString.append(temp).append("\n");
                }
            }
        }
        return returnString.toString();
    }
}
