public class Cell
{
    private Player occupant;

    public Cell(int row, int col) {
        this.occupant = null;
    }

    public boolean isEmpty()
    {
        return this.occupant == null;
    }

    public Player getOccupant()
    {
        return occupant;
    }

    public void setOccupant(Player occupant)
    {
        this.occupant = occupant;
    }

    public String toString()
    {
        if(isEmpty())
        {
            return " [ ]";
        }
        return " [" + occupant.playerChar + "]";
    }
}