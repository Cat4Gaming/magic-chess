package mp;

import javax.swing.*;
import main.MainFrame;

public class King extends ChessPiece {
    
    public King(Game gOwner, boolean black) {
        owner = gOwner;
        isBlack = black;
        setBorder(BorderFactory.createEmptyBorder());
        MainFrame tmp = owner.getOwner();
        if(black) setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/King.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
        else setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/King.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
    }
    
    @Override public boolean isKing() {return true;}
    
    @Override public void showMoves() {
        ChessPiece tmp = null;
        if(yPos != 0) {
            tmp = owner.getChessPieceOfSquare(xPos, yPos-1);
            if(!(tmp != null && tmp.isBlack() == isBlack())) owner.setSquareSelectable(xPos, yPos-1, true);
            if(xPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos-1);
                if(!(tmp != null && tmp.isBlack() == isBlack())) owner.setSquareSelectable(xPos-1, yPos-1, true);
            }
            if(xPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos-1);
                if(!(tmp != null && tmp.isBlack() == isBlack())) owner.setSquareSelectable(xPos+1, yPos-1, true);
            }
        }
        if(yPos != 7) {
            tmp = owner.getChessPieceOfSquare(xPos, yPos+1);
            if(!(tmp != null && tmp.isBlack() == isBlack())) owner.setSquareSelectable(xPos, yPos+1, true);
            if(xPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos+1);
                if(!(tmp != null && tmp.isBlack() == isBlack())) owner.setSquareSelectable(xPos-1, yPos+1, true);
            }
            if(xPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos+1);
                if(!(tmp != null && tmp.isBlack() == isBlack())) owner.setSquareSelectable(xPos+1, yPos+1, true);
            }
        }
        if(xPos != 0) {
            tmp = owner.getChessPieceOfSquare(xPos-1, yPos);
            if(!(tmp != null && tmp.isBlack() == isBlack())) owner.setSquareSelectable(xPos-1, yPos, true);
        }
        if(xPos != 7) {
            tmp = owner.getChessPieceOfSquare(xPos+1, yPos);
            if(!(tmp != null && tmp.isBlack() == isBlack())) owner.setSquareSelectable(xPos+1, yPos, true);
        }
    }
}