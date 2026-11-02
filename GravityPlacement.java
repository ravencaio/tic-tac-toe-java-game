public class GravityPlacement implements PlacementRule {
    @Override
    public int[] getValidPlacement(Cell[][] board, int targetRow, int targetCol) {
        int rows = board.length;
        int cols = board[0].length;

        if (targetCol < 0 || targetCol >= cols) return null;

        for (int r = rows - 1; r >= 0; r--) {
            if (board[r][targetCol].isEmpty()) {
                return new int[]{r, targetCol};
            }
        }
        return null;
    }
}