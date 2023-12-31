import java.util.ArrayList;
import java.util.Arrays;

public class board {
    private final int boardWidth;
    private final int boardHeight;
    private String[][] board;
    private ArrayList<player> players;
    private int[] zig;
    private int[] zag;
    private ArrayList<Integer> occupiedSpaces = new ArrayList<>();

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
        setZig();
        setZag();
    }

    public board(int width, int height) {
        boardWidth = width;
        boardHeight = height;
        players = new ArrayList<>();
        board = new String[boardHeight][boardWidth];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = String.valueOf(i * boardWidth + j + 1);
            }
        }
        setZig();
        setZag();
    }

    private void setZig() {
        zig = new int[(int) Math.sqrt(boardWidth * boardHeight)];
        for (int i = 0; i < zig.length; i++) {
            boolean isDuplicate = false;
            int temp = (int) (Math.random() * (boardWidth * boardHeight - 2 + 1) + 2);
            for (int j : occupiedSpaces) {
                if (j == temp) {
                    isDuplicate = true;
                    i--;
                    break;
                }
            }
            if (!isDuplicate) {
                occupiedSpaces.add(temp);
                zig[i] = temp;
            }
        }
        Arrays.sort(zig);
    }

    private void setZag() {
        zag = new int[(int) Math.cbrt(boardWidth * boardHeight)];
        for (int i = 0; i < zag.length; i++) {
            boolean isDuplicate = false;
            int temp = (int) (Math.random() * (boardWidth * boardHeight - 2 + 1) + 2);
            for (int j : occupiedSpaces) {
                if (j == temp) {
                    isDuplicate = true;
                    i--;
                    break;
                }
            }
            if (!isDuplicate) {
                occupiedSpaces.add(temp);
                zag[i] = temp;
            }
        }
        Arrays.sort(zag);
    }

    private String formatSpace(String item, int num) {
        StringBuilder itemBuilder = new StringBuilder(item);
        while (itemBuilder.length() < String.valueOf(boardWidth * boardHeight).length() || itemBuilder.length() < num + 1) {
            itemBuilder.insert(0, " ");
        }
        item = itemBuilder.toString();
        return item;
    }

    private int maxSpaceLength() {
        int returnInt = 0;
        while (returnInt < String.valueOf(boardWidth * boardHeight).length() || returnInt < players.size() + 1) {
            returnInt++;
        }
        return returnInt;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int[] getCoordinates(int space) {
        int[] coordinates = new int[2];
        if (space <= boardWidth * boardHeight) {
            coordinates[0] = (space - 1) / boardWidth;//y
            coordinates[1] = (space - 1) % boardWidth;//x
        } else {
            coordinates[0] = -1;
            coordinates[1] = -1;
        }
        return coordinates;
    }

    public int getIndex(int[] coords) {
        if (coords[0] == -1 && coords[1] == -1) {
            return -1;
        }
        return coords[0] * boardWidth + coords[1] + 1;
    }

    public int[] getZig() {
        return zig;
    }

    public int[] getZag() {
        return zag;
    }

    public int[] addMoves(int oldCoords, int moves) {
        return getCoordinates(oldCoords + moves);
    }

    public ArrayList<player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("\n");
        for (int i = 0; i < (2 + maxSpaceLength() * boardWidth + boardWidth - 1); i++) {
            returnString.append("#");
        }
        for (int row = 0; row < boardHeight; row++) {
            returnString.append("\n");
            returnString.append("#");
            for (int col = 0; col < boardWidth; col++) {
                StringBuilder temp;
                boolean spaceHasPlayer = false;
                if (row % 2 == 0) {
                    temp = new StringBuilder(board[row][col]);
                    for (int j : zig) {
                        if (j == getIndex(new int[]{row, col})) {
                            temp = new StringBuilder("Z");
                            spaceHasPlayer = true;
                            break;
                        }
                    }
                    for (int j : zag) {
                        if (j == getIndex(new int[]{row, col})) {
                            temp = new StringBuilder("z");
                            spaceHasPlayer = true;
                            break;
                        }
                    }
                    for (player player : players) {
                        if (player.getRow() == row && player.getCol() == col) {
                            if (!spaceHasPlayer) {
                                temp = new StringBuilder(String.valueOf(player.getIcon()));
                                spaceHasPlayer = true;
                            } else {
                                temp.append(player.getIcon());
                            }
                        }
                    }
                } else {
                    temp = new StringBuilder(board[row][boardWidth - col - 1]);
                    for (int j : zig) {
                        if (j == getIndex(new int[]{row, boardWidth - col - 1})) {
                            temp = new StringBuilder("Z");
                            spaceHasPlayer = true;
                            break;
                        }
                    }
                    for (int j : zag) {
                        if (j == getIndex(new int[]{row, boardWidth - col - 1})) {
                            temp = new StringBuilder("z");
                            spaceHasPlayer = true;
                            break;
                        }
                    }
                    for (player player : players) {
                        if (player.getRow() == row && player.getCol() == boardWidth - col - 1) {
                            if (!spaceHasPlayer) {
                                temp = new StringBuilder(String.valueOf(player.getIcon()));
                                spaceHasPlayer = true;
                            } else {
                                temp.append(player.getIcon());
                            }
                        }
                    }
                }
                temp = new StringBuilder(formatSpace(temp.toString(), players.size()));
                if (col != board[row].length - 1) {
                    returnString.append(temp).append(" ");
                } else {
                    returnString.append(temp).append("#\n");
                }
            }
            if (row % 2 != 0) {
                for (int i = 0; i < (2 + maxSpaceLength() * boardWidth + boardWidth - 1); i++) {
                    if (i == 1 || i == 2) {
                        returnString.append(" ");
                    } else {
                        returnString.append("#");
                    }
                }
            } else {
                for (int i = 0; i < (2 + maxSpaceLength() * boardWidth + boardWidth - 1); i++) {
                    if (i == (2 + maxSpaceLength() * boardWidth + boardWidth - 1) - 2 || i == (2 + maxSpaceLength() * boardWidth + boardWidth - 1) - 3) {
                        returnString.append(" ");
                    } else {
                        returnString.append("#");
                    }
                }
            }
        }
        returnString.delete(returnString.length() - 1 - (2 + maxSpaceLength() * boardWidth + boardWidth - 1), returnString.length());
        returnString.append("\n");
        for (int i = 0; i < (2 + maxSpaceLength() * boardWidth + boardWidth - 1); i++) {
            returnString.append("#");
        }
        return returnString.toString();
    }

    public static void main(String[] args) {
        board board = new board(5, 5);
        System.out.println("Board height: " + board.getBoardHeight());
        System.out.println("Board width: " + board.getBoardWidth());
        System.out.println(board);
        System.out.println("Coords of zags" + Arrays.toString(board.getZag()));
        System.out.println("Coords of zigs" + Arrays.toString(board.getZig()));
        System.out.println();
        System.out.println("Translate 1 to coords: " + Arrays.toString(board.getCoordinates(1)));
        System.out.println("Translate [0][0] to index: " + board.getIndex(new int[]{0, 0}));
        System.out.println("Calculate the new coords from index 1 + 1: " + Arrays.toString(board.addMoves(board.getIndex(new int[]{0, 0}), 1)));
        board board2 = new board(10, 7);
        System.out.println(board2);
        for (int i = 1; i <= board2.getBoardHeight() * board2.boardWidth; i++) {
            System.out.println("Translate " + i + " to coords: " + Arrays.toString(board2.getCoordinates(i)));
        }
    }
}
