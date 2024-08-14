package mp;

import javax.swing.*;
import main.MainFrame;

public class Knight extends ChessPiece {
    
    public Knight(Game gOwner, boolean black) {
        pieceNumber = 3;
        owner = gOwner;
        isBlack = black;
        MainFrame tmp = owner.getOwner();
        if(black) setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Knight.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
        else setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Knight.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
    }
    
    @Override public boolean isKnightOrBishop() {return true;}
    
    @Override public void showMoves() {
        ChessPiece tmp = null;
        if(yPos > 1 && xPos > 0) {
            tmp = owner.getChessPieceOfSquare(xPos-1, yPos-2);
            if(tmp == null || tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-1, yPos-2, true);
        }
        if(yPos > 0 && xPos > 1) {
            tmp = owner.getChessPieceOfSquare(xPos-2, yPos-1);
            if(tmp == null || tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-2, yPos-1, true);
        }
        if(yPos < 7 && xPos > 1) {
            tmp = owner.getChessPieceOfSquare(xPos-2, yPos+1);
            if(tmp == null || tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-2, yPos+1, true);
        }
        if(yPos < 6 && xPos > 0) {
            tmp = owner.getChessPieceOfSquare(xPos-1, yPos+2);
            if(tmp == null || tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-1, yPos+2, true);
        }
        if(yPos > 1 && xPos < 7) {
            tmp = owner.getChessPieceOfSquare(xPos+1, yPos-2);
            if(tmp == null || tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+1, yPos-2, true);
        }
        if(yPos > 0 && xPos < 6) {
            tmp = owner.getChessPieceOfSquare(xPos+2, yPos-1);
            if(tmp == null || tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+2, yPos-1, true);
        }
        if(yPos < 7 && xPos < 6) {
            tmp = owner.getChessPieceOfSquare(xPos+2, yPos+1);
            if(tmp == null || tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+2, yPos+1, true);
        }
        if(yPos < 6 && xPos < 7) {
            tmp = owner.getChessPieceOfSquare(xPos+1, yPos+2);
            if(tmp == null || tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+1, yPos+2, true);
        }
    }
}