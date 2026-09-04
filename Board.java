class Board
{
    int winCondition;
    int playsLeft = 9;
    Cell[][] board;

    Player pl1;
    Player pl2;

    public Board(int winCondition, Player pl1, Player pl2)
    {
        this.winCondition = winCondition;
        this.pl1 = pl1;
        this.pl2 = pl2;
        this.board = new Cell[3][3];

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }

    private boolean cellValidToTake(int xPos, int yPos)
    {
        return (0 <= xPos && xPos <= 2) && (0 <= yPos && yPos <= 2) && this.board[xPos][yPos].isEmpty();
    }

    public boolean addChar(int xPos, int yPos, Player pl)
    {
        if(!cellValidToTake(xPos, yPos)) return false;

        this.board[xPos][yPos].setOccupant(pl);
        this.playsLeft -= 1;
        return true;
    }

    public boolean isNotDrawn()
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
        return !board[row][0].isEmpty() && board[row][0].getOccupant() == pl && !board[row][1].isEmpty() && board[row][1].getOccupant() == pl && !board[row][2].isEmpty() && board[row][2].getOccupant() == pl;
    }

    private boolean checkCol(int col, Player pl)
    {
        return !board[0][col].isEmpty() && board[0][col].getOccupant() == pl && !board[1][col].isEmpty() && board[1][col].getOccupant() == pl && !board[2][col].isEmpty() && board[2][col].getOccupant() == pl;
    }

    private boolean checkDiagonals(Player pl)
    {
        boolean principal = !board[0][0].isEmpty() && board[0][0].getOccupant() == pl && !board[1][1].isEmpty() && board[1][1].getOccupant() == pl && !board[2][2].isEmpty() && board[2][2].getOccupant() == pl;

        boolean secundaria = !board[0][2].isEmpty() && board[0][2].getOccupant() == pl && !board[1][1].isEmpty() && board[1][1].getOccupant() == pl && !board[2][0].isEmpty() && board[2][0].getOccupant() == pl;

        return principal || secundaria;
    }

    public void printBoard(){
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }
}