public class board {
    private final int boardWidth;
    private final int boardHeight;
    private String[][] board;

    public board(int width, int height) {
        boardWidth = width;
        boardHeight = height;
        board = new String[boardHeight][boardWidth];
        int numSpaces = width * height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = String.valueOf(i * boardWidth + j + 1);
            }
        }
    }

    private String formatSpace(String item) {
        while (item.length() < String.valueOf(boardWidth * boardHeight).length()) {
            item = " " + item;
        }
        return item;
    }

    public int[] getCoordinates(int space) {
        int[] coordinates = new int[2];
        coordinates[1] = (space - 1) % boardWidth;//y
        coordinates[0] = (space - 1) / boardWidth;//x
        return coordinates;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int col = 0; col < boardHeight; col++) {
            for (int row = 0; row < boardWidth; row++) {
                String temp;
                if (col % 2 == 0) {
                    temp = formatSpace(board[col][row]);
                } else {
                    temp = formatSpace(board[col][boardWidth - row - 1]);
                }
                if (row != board[col].length - 1) {
                    returnString += temp + " ";
                } else {
                    returnString += temp + "\n";
                }
            }
        }
        return returnString;
    }
}
