package mp;

import javax.swing.*;
import main.MainFrame;

public class Pawn extends ChessPiece {
    
    public Pawn(Game gOwner, boolean black) {
        owner = gOwner;
        isBlack = black;
        MainFrame tmp = owner.getOwner();
        if(black) setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Pawn.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
        else setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Pawn.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
    }
    
    @Override public void moveTo(int x, int y) {
        if(y+1 == yPos && x != xPos && owner.getChessPieceOfSquare(x, yPos) != null) owner.killChessPiece(x, yPos);
        if(y-1 == yPos && x != xPos && owner.getChessPieceOfSquare(x, yPos) != null) owner.killChessPiece(x, yPos);
        xPos = x;
        yPos = y;
        if(moved < 2) {
            if(y == 4 || y == 3) enPassant = true;
        }
        if(yPos == 0 && !isBlack) owner.pawnPromotion(this);
        if(yPos == 7 && isBlack) owner.pawnPromotion(this);
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
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos);
                if(tmp != null && tmp.getEnPassant() && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-1, yPos-1, true);
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos-1);
                if(tmp != null && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-1, yPos-1, true);
            }
            if(xPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos);
                if(tmp != null && tmp.getEnPassant() && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+1, yPos-1, true);
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos-1);
                if(tmp != null && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+1, yPos-1, true);
            }
        }
        else {
            tmp = owner.getChessPieceOfSquare(xPos, yPos+1);
            if(tmp == null) owner.setSquareSelectable(xPos, yPos+1, true);
            if(moved < 2 && tmp == null) {
                tmp = owner.getChessPieceOfSquare(xPos, yPos+2);
                if(tmp == null) owner.setSquareSelectable(xPos, yPos+2, true);
            }
            if(xPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos);
                if(tmp != null && tmp.getEnPassant() && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-1, yPos+1, true);
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos+1);
                if(tmp != null && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos-1, yPos+1, true);
            }
            if(xPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos);
                if(tmp != null && tmp.getEnPassant() && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+1, yPos+1, true);
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos+1);
                if(tmp != null && tmp.isBlack() != isBlack()) owner.setSquareSelectable(xPos+1, yPos+1, true);
            }
        }
    }
}