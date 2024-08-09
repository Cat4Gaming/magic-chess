package mp;

import javax.swing.*;
import main.MainFrame;

public abstract class ChessPiece extends JLabel {
    int xPos, yPos;
    Game owner;
    boolean isBlack;
    
    public int getXPos() {return xPos;}
    public int getYPos() {return yPos;}
    public boolean isBlack() {return isBlack;}
    
    public void moveTo(int x, int y) {
        xPos = x;
        yPos = y;
    }
    
    public abstract void showMoves();
}