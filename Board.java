class Board {
    int playsLeft;
    Cell[][] board;
    int rows;
    int cols;
    PlacementRule rule;

    public Board(int rows, int cols, PlacementRule rule) {
        this.rows = rows;
        this.cols = cols;
        this.rule = rule;
        this.playsLeft = rows * cols;
        this.board = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean addChar(int xPos, int yPos, Player pl) {
        int[] finalPos = rule.getValidPlacement(this.board, xPos, yPos);
        if (finalPos == null) return false;

        this.board[finalPos[0]][finalPos[1]].setOccupant(pl);
        this.playsLeft -= 1;
        return true;
    }

    public boolean isDrawn() {
        return (this.playsLeft == 0);
    }

    public boolean checkWin(Player pl, int winCondition) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c].isEmpty() || board[r][c].getOccupant() != pl) continue;

                if (c + winCondition <= cols && checkDirection(r, c, 0, 1, pl, winCondition)) return true;
                if (r + winCondition <= rows && checkDirection(r, c, 1, 0, pl, winCondition)) return true;
                if (r + winCondition <= rows && c + winCondition <= cols && checkDirection(r, c, 1, 1, pl, winCondition)) return true;
                if (r + winCondition <= rows && c - winCondition + 1 >= 0 && checkDirection(r, c, 1, -1, pl, winCondition)) return true;
            }
        }
        return false;
    }

    private boolean checkDirection(int startRow, int startCol, int rowDir, int colDir, Player pl, int winCondition) {
        for (int i = 0; i < winCondition; i++) {
            int currentRow = startRow + (i * rowDir);
            int currentCol = startCol + (i * colDir);
            if (board[currentRow][currentCol].getOccupant() != pl) return false;
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }
}