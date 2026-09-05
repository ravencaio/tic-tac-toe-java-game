class Board
{
    int playsLeft;
    Cell[][] board;
    int squareSize;


    Player pl1;
    Player pl2;

    public Board(Player pl1, Player pl2, int squareSize)
    {
        this.pl1 = pl1;
        this.pl2 = pl2;
        this.squareSize = squareSize;
        this.playsLeft = squareSize * squareSize;
        this.board = new Cell[squareSize][squareSize];

        for(int i = 0; i < squareSize; i++)
        {
            for(int j = 0; j < squareSize; j++)
            {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }

    private boolean cellValidToTake(int xPos, int yPos)
    {
        return (0 <= xPos && xPos <= this.squareSize - 1) && (0 <= yPos && yPos <= this.squareSize - 1) && this.board[xPos][yPos].isEmpty();
    }

    public boolean addChar(int xPos, int yPos, Player pl)
    {
        if(!cellValidToTake(xPos, yPos)) return false;

        this.board[xPos][yPos].setOccupant(pl);
        this.playsLeft -= 1;
        return true;
    }

    public boolean isDrawn()
    {
        return (this.playsLeft == 0);
    }

    public Player playerWon() {
        for(int i = 0; i < 3; i++)
        {
            if(checkRow(i, pl1) || checkCol(i, pl1)) return pl1;
            if(checkRow(i, pl2) || checkCol(i, pl2)) return pl2;
        }

        if(checkDiagonals(pl1)) return pl1;
        if(checkDiagonals(pl2)) return pl2;

        return null;
    }

    private boolean checkRow(int row, Player pl)
    {
        for (int i = 0; i < squareSize; i++) { if(board[row][i].isEmpty() || board[row][i].getOccupant() != pl) return false;}
        return true;
    }

    private boolean checkCol(int col, Player pl)
    {
        for (int i = 0; i < squareSize; i++) { if(board[i][col].isEmpty() || board[i][col].getOccupant() != pl) return false;}
        return true;
    }

    private boolean checkDiagonals(Player pl)
    {
        boolean principal = true;
        boolean secundaria = true;

        // Checar a diagonal principal
        for (int i = 0; i < squareSize; i++){
            if (board[i][i].getOccupant() != pl){
                principal = false;
                break;
            }
        }
        for (int i = 0; i < squareSize; i++){
            if (board[squareSize-1-i][i].getOccupant() != pl){
                secundaria = false;
                break;
            }
        }


        return principal || secundaria;
    }

    public void printBoard(){
        for(int i = 0; i < squareSize; i++)
        {
            for(int j = 0; j < squareSize; j++)
            {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }
}