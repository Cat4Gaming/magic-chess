package mp;

import javax.swing.*;
import main.MainFrame;

public class Rook extends ChessPiece {
    
    public Rook(Game gOwner, boolean black) {
        owner = gOwner;
        isBlack = black;
        MainFrame tmp = owner.getOwner();
        if(black) setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Rook.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
        else setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Rook.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
    }
    
    @Override public boolean isRook() {return true;}
    
    @Override public void showMoves() {
        int min = 0;
        int max = 7;
        ChessPiece tmp = null;
        for(int i = 0; i < xPos; i++) {
            tmp = owner.getChessPieceOfSquare(i, yPos);
            if(tmp != null) {
                min = i;
                if(tmp.isBlack() == isBlack()) min++;
            }
        }
        for(int i = 7; i > xPos; i--) {
            tmp = owner.getChessPieceOfSquare(i, yPos);
            if(tmp != null) {
                max = i;
                if(tmp.isBlack() == isBlack()) max--;
            }
        }
        for(int i = min; i < max+1; i++) {
            owner.setSquareSelectable(i, yPos, true);
        }
        min = 0;
        max = 7;
        for(int i = 0; i < yPos; i++) {
            tmp = owner.getChessPieceOfSquare(xPos, i);
            if(tmp != null) {
                min = i;
                if(tmp.isBlack() == isBlack()) min++;
            }
        }
        for(int i = 7; i > yPos; i--) {
            tmp = owner.getChessPieceOfSquare(xPos, i);
            if(tmp != null) {
                max = i;
                if(tmp.isBlack() == isBlack()) max--;
            }
        }
        for(int i = min; i < max+1; i++) {
            owner.setSquareSelectable(xPos, i, true);
        }
        owner.setSquareSelectable(xPos, yPos, false);
    }
}