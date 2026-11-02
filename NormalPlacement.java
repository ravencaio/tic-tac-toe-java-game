public class NormalPlacement implements PlacementRule {
    @Override
    public int[] getValidPlacement(Cell[][] board, int targetRow, int targetCol) {
        int rows = board.length;
        int cols = board[0].length;

        if (targetRow >= 0 && targetRow < rows && targetCol >= 0 && targetCol < cols && board[targetRow][targetCol].isEmpty()) {
            return new int[]{targetRow, targetCol};
        }
        return null;
    }
}