public interface PlacementRule {
    int[] getValidPlacement(Cell[][] board, int targetRow, int targetCol);
}