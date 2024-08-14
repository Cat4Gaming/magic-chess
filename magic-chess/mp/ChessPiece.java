package mp;

import javax.swing.*;
import main.MainFrame;

public abstract class ChessPiece extends JLabel {
    int xPos, yPos, moved, pieceNumber;
    Game owner;
    boolean isBlack, enPassant;
    
    public int getXPos() {return xPos;}
    public int getYPos() {return yPos;}
    public int getMoved() {return moved;}
    public boolean isBlack() {return isBlack;}
    public boolean getEnPassant() {return enPassant;}
    public void resetEnPassant() {enPassant = false;}
    public void resetMoved() {moved = 0;}
    public int getPieceNumber() {return pieceNumber;}
    
    public void moveTo(int x, int y) {
        xPos = x;
        yPos = y;
        moved++;
    }
    
    public abstract void showMoves();
}