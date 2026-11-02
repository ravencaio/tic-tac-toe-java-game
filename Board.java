class Board {
    int playsLeft;
    Cell[][] board;
    int squareSize;

    public Board(int squareSize) {
        this.squareSize = squareSize;
        this.playsLeft = squareSize * squareSize;
        this.board = new Cell[squareSize][squareSize];

        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }

    private boolean cellValidToTake(int xPos, int yPos) {
        return (0 <= xPos && xPos < this.squareSize) && (0 <= yPos && yPos < this.squareSize) && this.board[xPos][yPos].isEmpty();
    }

    public boolean addChar(int xPos, int yPos, Player pl) {
        if (!cellValidToTake(xPos, yPos)) return false;

        this.board[xPos][yPos].setOccupant(pl);
        this.playsLeft -= 1;
        return true;
    }

    public boolean isDrawn() {
        return (this.playsLeft == 0);
    }

    public boolean checkWin(Player pl, int winCondition) {
        for (int r = 0; r < squareSize; r++) {
            for (int c = 0; c < squareSize; c++) {
                if (board[r][c].isEmpty() || board[r][c].getOccupant() != pl) continue;

                if (c + winCondition <= squareSize && checkDirection(r, c, 0, 1, pl, winCondition)) return true;
                if (r + winCondition <= squareSize && checkDirection(r, c, 1, 0, pl, winCondition)) return true;
                if (r + winCondition <= squareSize && c + winCondition <= squareSize && checkDirection(r, c, 1, 1, pl, winCondition)) return true;
                if (r + winCondition <= squareSize && c - winCondition + 1 >= 0 && checkDirection(r, c, 1, -1, pl, winCondition)) return true;
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
        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }
}