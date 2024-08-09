package mp;

import javax.swing.*;
import main.MainFrame;

public class Pawn extends ChessPiece {
    int moved;
    
    public Pawn(Game gOwner, boolean black) {
        owner = gOwner;
        isBlack = black;
        MainFrame tmp = owner.getOwner();
        if(black) setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Pawn.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
        else setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Pawn.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
    }
    
    @Override public void moveTo(int x, int y) {
        xPos = x;
        yPos = y;
        moved++;
    }
    
    @Override public void showMoves() {
        ChessPiece tmp = null;
        if(!isBlack) {
            tmp = owner.getChessPieceOfSquare(xPos, yPos-1);
            if(tmp == null) owner.setSquareSelectable(xPos, yPos-1, true);
            if(moved < 2 && tmp == null) {
                tmp = owner.getChessPieceOfSquare(xPos, yPos-2);
                if(tmp == null) owner.setSquareSelectable(xPos, yPos-2, true);
            }
            if(xPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos-1);
                if(tmp != null && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-1, yPos-1, true);
            }
            if(xPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos-1);
                if(tmp != null && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+1, yPos-1, true);
            }
            if(yPos == 0) owner.pawnUpgrade(xPos, yPos, isBlack);
        }
        else {
            tmp = owner.getChessPieceOfSquare(xPos, yPos+1);
            if(tmp == null) owner.setSquareSelectable(xPos, yPos+1, true);
            if(moved < 2 && tmp == null) {
                tmp = owner.getChessPieceOfSquare(xPos, yPos+2);
                if(tmp == null) owner.setSquareSelectable(xPos, yPos+2, true);
            }
            if(xPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos+1);
                if(tmp != null && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-1, yPos+1, true);
            }
            if(xPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos+1);
                if(tmp != null && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+1, yPos+1, true);
            }
            if(yPos == 7) owner.pawnUpgrade(xPos, yPos, isBlack);
        }
    }
}