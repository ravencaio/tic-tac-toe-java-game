class Board{
    int winCondition;
    int playsLeft = 9;

    int lDiagCount;
    int rDiagCount;
    
    int r1Count;
    int r2Count;
    int r3Count;

    int c1Count;
    int c2Count;
    int c3Count;

    String[][] board = {{" [ ] ", " [ ] ", " [ ] "},
                        {" [ ] ", " [ ] ", " [ ] "},
                        {" [ ] ", " [ ] ", " [ ] "}};

    Player pl1;
    Player pl2;

    public Board(int winCondition, Player pl1, Player pl2) {
        this.winCondition = winCondition;
        this.pl1 = pl1;
        this.pl2 = pl2;
    }

    private boolean cellValidToTake(int xPos, int yPos){
        return ((0 <= xPos && xPos <= 2) && (0 <= yPos && yPos <= 2)) && this.board[xPos][yPos].equals(" [ ] ");
    }

    public boolean addChar(int xPos, int yPos, Player pl){
        if (!cellValidToTake(xPos, yPos)) return false;

        switch (yPos) {
            case 0:
                r1Count += pl.playerType;
                if (xPos == 0) {lDiagCount += pl.playerType; c1Count += pl.playerType;}
                if (xPos == 1) {c2Count += pl.playerType;}
                if (xPos == 2) {rDiagCount += pl.playerType; c3Count += pl.playerType;}
                break;
            case 1:
                r2Count += pl.playerType;
                if (xPos == 0) {c1Count += pl.playerType;}
                if (xPos == 1) {c2Count += pl.playerType; lDiagCount += pl.playerType; rDiagCount += pl.playerType;}
                if (xPos == 2) {c3Count += pl.playerType;}
                break;
            case 2:
                r3Count += pl.playerType;
                if (xPos == 0) {rDiagCount += pl.playerType; c1Count += pl.playerType;}
                if (xPos == 1) {c2Count += pl.playerType;}
                if (xPos == 2) {lDiagCount += pl.playerType; c3Count += pl.playerType;}
                break;
            default:
                return false;
        }
        this.board[xPos][yPos] = " [" + pl.playerChar + "] ";
        this.playsLeft -= 1;
        return true;
    }

    public boolean isNotDrawn() {
        return (this.playsLeft == 0);
    }

    public Player playerWon() {
        int pl1WinCond = this.pl1.playerType * this.winCondition;
        int pl2WinCond = this.pl2.playerType * this.winCondition;

        if (this.r1Count == pl1WinCond || this.r2Count == pl1WinCond || this.r3Count == pl1WinCond || c1Count == pl1WinCond || c2Count == pl1WinCond || c3Count == pl1WinCond || lDiagCount == pl1WinCond || rDiagCount == pl1WinCond){
            return pl1;
        }
        if (this.r1Count == pl2WinCond || this.r2Count == pl2WinCond || this.r3Count == pl2WinCond || c1Count == pl2WinCond || c2Count == pl2WinCond || c3Count == pl2WinCond || lDiagCount == pl2WinCond || rDiagCount == pl2WinCond){
            return pl2;
        }

        return null;
    }

    public void printBoard(){
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(this.board[i][j]);
                if (j == 2){ System.out.println();}
            }
        }
    }
    
}