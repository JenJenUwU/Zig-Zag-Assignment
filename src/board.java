public class board {
    private int boardWidth;
    private int boardHeight;
    private String[][] board;

    public board(int width, int height) {
        boardWidth = width;
        boardHeight = height;
        board = new String[boardHeight][boardWidth];
        int numSpaces = width * height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 0) {
                    board[i][j] = String.valueOf(i * boardHeight + j);
                } else {
                    board[i][j] = String.valueOf(i * boardHeight + (width - j - 1));
                }
            }
        }
    }

    @Override
    public String toString() {
        String returnString = "";
        for (String arr[] : board) {
            for (int i = 0; i < arr.length; i++) {
                String temp = arr[i];
                while (temp.length() < String.valueOf(boardWidth * boardHeight).length() || temp.length() < 2) {
                    temp = " " + temp;
                }
                if (i != arr.length - 1) {
                    returnString += temp + " ";
                } else {
                    returnString += temp + "\n";
                }
            }
        }
        return returnString;
    }
}
