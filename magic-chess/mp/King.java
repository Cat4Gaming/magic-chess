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
    
    @Override public void moveTo(int x, int y) {
        xPos = x;
        yPos = y;
        if(moved < 2) {
            if(x == 6 || x == 2) owner.castling(x, isBlack);
        }
        moved++;
    }
    
    @Override public void showMoves() {
        ChessPiece tmp = null;
        if(moved < 2) {
            if(!isBlack && owner.getChessPieceOfSquare(7, 7).isRook() == true && owner.getChessPieceOfSquare(7, 7).getMoved() < 2 && owner.getChessPieceOfSquare(6, 7) == null && owner.getChessPieceOfSquare(5, 7) == null) {
                owner.setSquareSelectable(6, 7, true);
            }
            if(!isBlack && owner.getChessPieceOfSquare(0, 7).isRook() == true && owner.getChessPieceOfSquare(0, 7).getMoved() < 2 && owner.getChessPieceOfSquare(1, 7) == null && owner.getChessPieceOfSquare(2, 7) == null && owner.getChessPieceOfSquare(3, 7) == null) {
                owner.setSquareSelectable(2, 7, true);
            }
            if(isBlack && owner.getChessPieceOfSquare(7, 0).isRook() == true && owner.getChessPieceOfSquare(7, 0).getMoved() < 2 && owner.getChessPieceOfSquare(6, 0) == null && owner.getChessPieceOfSquare(5, 0) == null) {
                owner.setSquareSelectable(6, 0, true);
            }
            if(isBlack && owner.getChessPieceOfSquare(0, 0).isRook() == true && owner.getChessPieceOfSquare(0, 0).getMoved() < 2 && owner.getChessPieceOfSquare(1, 0) == null && owner.getChessPieceOfSquare(2, 0) == null && owner.getChessPieceOfSquare(3, 0) == null) {
                owner.setSquareSelectable(2, 0, true);
            }
        }
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