package mp;

import javax.swing.*;
import main.MainFrame;

public class Bishop extends ChessPiece {
    
    public Bishop(Game gOwner, boolean black) {
        pieceNumber = 2;
        owner = gOwner;
        isBlack = black;
        MainFrame tmp = owner.getOwner();
        if(black) setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Bishop.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
        else setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Bishop.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
    }
    
    @Override public void showMoves() {
        ChessPiece tmp = null;
        for(int y = yPos+1; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                if(x+y == xPos+yPos) {
                    tmp = owner.getChessPieceOfSquare(x, y);
                    owner.setSquareSelectable(x, y, true);
                    if(tmp != null) {
                        if(tmp.isBlack() == isBlack()) owner.setSquareSelectable(x, y, false);
                        y = x = 9;
                    }
                }
            }
        }
        for(int y = yPos-1; y >= 0; y--) {
            for(int x = 0; x < 8; x++) {
                if(x+y == xPos+yPos) {
                    tmp = owner.getChessPieceOfSquare(x, y);
                    owner.setSquareSelectable(x, y, true);
                    if(tmp != null) {
                        if(tmp.isBlack() == isBlack()) owner.setSquareSelectable(x, y, false);
                        y = 0;
                        x = 9;
                    }
                }
            }
        }
        for(int y = yPos+1; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                if((y-yPos)*-2+x+y == xPos+yPos) {
                    tmp = owner.getChessPieceOfSquare(x, y);
                    owner.setSquareSelectable(x, y, true);
                    if(tmp != null) {
                        if(tmp.isBlack() == isBlack()) owner.setSquareSelectable(x, y, false);
                        y = x = 9;
                    }
                }
            }
        }
        for(int y = yPos-1; y >= 0; y--) {
            for(int x = 0; x < 8; x++) {
                if((y-yPos)*-2+x+y == xPos+yPos) {
                    tmp = owner.getChessPieceOfSquare(x, y);
                    owner.setSquareSelectable(x, y, true);
                    if(tmp != null) {
                        if(tmp.isBlack() == isBlack()) owner.setSquareSelectable(x, y, false);
                        y = 0;
                        x = 9;
                    }
                }
            }
        }
    }
}