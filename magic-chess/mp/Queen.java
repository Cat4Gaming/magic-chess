package mp;

import javax.swing.*;
import main.MainFrame;

public class Queen extends ChessPiece {
    
    public Queen(Game gOwner, boolean black) {
        pieceNumber = 4;
        owner = gOwner;
        isBlack = black;
        MainFrame tmp = owner.getOwner();
        if(black) setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Queen.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
        else setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Queen.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
    }
    
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
        int dif = 0;
        dif = 8-yPos;
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