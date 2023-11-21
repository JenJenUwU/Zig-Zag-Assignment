public class game {
    public static void main(String[] args) {
        board board = new board(6, 5);
        System.out.println(board);
        System.out.println(board.getCoordinates(3)[0]);
    }
}