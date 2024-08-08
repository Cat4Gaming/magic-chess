package mp;

public abstract class ChessPiece {
    private int xPos, yPos;
    
    public int getXPos() {return xPos;}
    public int getYPos() {return yPos;}
    
    public void moveTo(int x, int y) {
        xPos = x;
        yPos = y;
    }
    
    public abstract void showMoves();
}