package mp;

import javax.swing.*;
import main.MainFrame;

public class King extends ChessPiece {
    
    public King(Game gOwner, boolean black) {
        pieceNumber = 5;
        owner = gOwner;
        isBlack = black;
        MainFrame tmp = owner.getOwner();
        if(black) setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/King.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
        else setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/King.png", tmp.getScreenHeight()/10, tmp.getScreenHeight()/10));
    }
    
    @Override public void moveTo(int x, int y) {
        if(owner.getChessPieceOfSquare(x, y) == null) owner.capture(false);
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
            if(!isBlack && owner.getChessPieceOfSquare(7, 7) != null && owner.getChessPieceOfSquare(7, 7).getPieceNumber() == 1 && owner.getChessPieceOfSquare(7, 7).getMoved() < 2 && owner.getChessPieceOfSquare(6, 7) == null && owner.getChessPieceOfSquare(5, 7) == null) {
                owner.setSquareSelectable(6, 7, true);
            }
            if(!isBlack && owner.getChessPieceOfSquare(0, 7) != null && owner.getChessPieceOfSquare(0, 7).getPieceNumber() == 1 && owner.getChessPieceOfSquare(0, 7).getMoved() < 2 && owner.getChessPieceOfSquare(1, 7) == null && owner.getChessPieceOfSquare(2, 7) == null && owner.getChessPieceOfSquare(3, 7) == null) {
                owner.setSquareSelectable(2, 7, true);
            }
            if(isBlack && owner.getChessPieceOfSquare(7, 0) != null && owner.getChessPieceOfSquare(7, 0).getPieceNumber() == 1 && owner.getChessPieceOfSquare(7, 0).getMoved() < 2 && owner.getChessPieceOfSquare(6, 0) == null && owner.getChessPieceOfSquare(5, 0) == null) {
                owner.setSquareSelectable(6, 0, true);
            }
            if(isBlack && owner.getChessPieceOfSquare(0, 0) != null && owner.getChessPieceOfSquare(0, 0).getPieceNumber() == 1 && owner.getChessPieceOfSquare(0, 0).getMoved() < 2 && owner.getChessPieceOfSquare(1, 0) == null && owner.getChessPieceOfSquare(2, 0) == null && owner.getChessPieceOfSquare(3, 0) == null) {
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
    
    public boolean detectCheck() {
        ChessPiece tmp = null;
        if(yPos > 1 && xPos > 0) {
            tmp = owner.getChessPieceOfSquare(xPos-1, yPos-2);
            if(tmp != null && tmp.isBlack() != isBlack() && tmp.getPieceNumber() == 3) return true;
        }
        if(yPos > 0 && xPos > 1) {
            tmp = owner.getChessPieceOfSquare(xPos-2, yPos-1);
            if(tmp != null && tmp.isBlack() != isBlack() && tmp.getPieceNumber() == 3) return true;
        }
        if(yPos < 7 && xPos > 1) {
            tmp = owner.getChessPieceOfSquare(xPos-2, yPos+1);
            if(tmp != null && tmp.isBlack() != isBlack() && tmp.getPieceNumber() == 3) return true;
        }
        if(yPos < 6 && xPos > 0) {
            tmp = owner.getChessPieceOfSquare(xPos-1, yPos+2);
            if(tmp != null && tmp.isBlack() != isBlack() && tmp.getPieceNumber() == 3) return true;
        }
        if(yPos > 1 && xPos < 7) {
            tmp = owner.getChessPieceOfSquare(xPos+1, yPos-2);
            if(tmp != null && tmp.isBlack() != isBlack() && tmp.getPieceNumber() == 3) return true;
        }
        if(yPos > 0 && xPos < 6) {
            tmp = owner.getChessPieceOfSquare(xPos+2, yPos-1);
            if(tmp != null && tmp.isBlack() != isBlack() && tmp.getPieceNumber() == 3) return true;
        }
        if(yPos < 7 && xPos < 6) {
            tmp = owner.getChessPieceOfSquare(xPos+2, yPos+1);
            if(tmp != null && tmp.isBlack() != isBlack() && tmp.getPieceNumber() == 3) return true;
        }
        if(yPos < 6 && xPos < 7) {
            tmp = owner.getChessPieceOfSquare(xPos+1, yPos+2);
            if(tmp != null && tmp.isBlack() != isBlack() && tmp.getPieceNumber() == 3) return true;
        }
        
        int min = 0;
        int max = 7;
        for(int i = 0; i < xPos; i++) {
            tmp = owner.getChessPieceOfSquare(i, yPos);
            if(tmp != null) min = i;
        }
        for(int i = 7; i > xPos; i--) {
            tmp = owner.getChessPieceOfSquare(i, yPos);
            if(tmp != null) max = i;
        }
        tmp = owner.getChessPieceOfSquare(max, yPos);
        if(tmp != null && tmp.isBlack() != isBlack) {
            if(tmp.getPieceNumber() == 1 || tmp.getPieceNumber() == 4) return true;
        }
        tmp = owner.getChessPieceOfSquare(min, yPos);
        if(tmp != null && tmp.isBlack() != isBlack) {
            if(tmp.getPieceNumber() == 1 || tmp.getPieceNumber() == 4) return true;
        }
        min = 0;
        max = 7;
        for(int i = 0; i < yPos; i++) {
            tmp = owner.getChessPieceOfSquare(xPos, i);
            if(tmp != null) min = i;
        }
        for(int i = 7; i > yPos; i--) {
            tmp = owner.getChessPieceOfSquare(xPos, i);
            if(tmp != null) max = i;
        }
        tmp = owner.getChessPieceOfSquare(xPos, max);
        if(tmp != null && tmp.isBlack() != isBlack) {
            if(tmp.getPieceNumber() == 1 || tmp.getPieceNumber() == 4) return true;
        }
        tmp = owner.getChessPieceOfSquare(xPos, min);
        if(tmp != null && tmp.isBlack() != isBlack) {
            if(tmp.getPieceNumber() == 1 || tmp.getPieceNumber() == 4) return true;
        }
        
        if(yPos != 0) {
            tmp = owner.getChessPieceOfSquare(xPos, yPos-1);
            if(tmp != null && tmp.getPieceNumber() == 5) return true;
            if(xPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos-1);
                if(tmp != null && tmp.getPieceNumber() == 5) return true;
            }
            if(xPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos-1);
                if(tmp != null && tmp.getPieceNumber() == 5) return true;
            }
        }
        if(yPos != 7) {
            tmp = owner.getChessPieceOfSquare(xPos, yPos+1);
            if(tmp != null && tmp.getPieceNumber() == 5) return true;
            if(xPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos+1);
                if(tmp != null && tmp.getPieceNumber() == 5) return true;
            }
            if(xPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos+1);
                if(tmp != null && tmp.getPieceNumber() == 5) return true;
            }
        }
        if(xPos != 0) {
            tmp = owner.getChessPieceOfSquare(xPos-1, yPos);
            if(tmp != null && tmp.getPieceNumber() == 5) return true;
        }
        if(xPos != 7) {
            tmp = owner.getChessPieceOfSquare(xPos+1, yPos);
            if(tmp != null && tmp.getPieceNumber() == 5) return true;
        }
        
        if(xPos != 0) {
            if(yPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos-1);
                if(!isBlack() &&tmp != null && tmp.getPieceNumber() < 1 && tmp.isBlack()) return true;
            }
            if(yPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos-1, yPos+1);
                if(isBlack() && tmp != null && tmp.getPieceNumber() < 1 && !tmp.isBlack()) return true;                
            }
        }
        if(xPos != 7) {
            if(yPos != 0) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos-1);
                if(!isBlack() && tmp != null && tmp.getPieceNumber() < 1 && tmp.isBlack()) return true;
            }
            if(yPos != 7) {
                tmp = owner.getChessPieceOfSquare(xPos+1, yPos+1);
                if(isBlack() && tmp != null && tmp.getPieceNumber() < 1 && !tmp.isBlack()) return true;
            }
        }
        
        for(int y = yPos+1; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                if(x+y == xPos+yPos) {
                    tmp = owner.getChessPieceOfSquare(x, y);
                    if(tmp != null) {
                        if(tmp.isBlack() != isBlack()) {
                            if(tmp.getPieceNumber() == 2 || tmp.getPieceNumber() == 4) return true;
                        }
                        y = x = 9;
                    }
                }
            }
        }
        for(int y = yPos-1; y >= 0; y--) {
            for(int x = 0; x < 8; x++) {
                if(x+y == xPos+yPos) {
                    tmp = owner.getChessPieceOfSquare(x, y);
                    if(tmp != null) {
                        if(tmp.isBlack() != isBlack()) {
                            if(tmp.getPieceNumber() == 2 || tmp.getPieceNumber() == 4) return true;
                        }
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
                    if(tmp != null) {
                        if(tmp.isBlack() != isBlack()) {
                            if(tmp.getPieceNumber() == 2 || tmp.getPieceNumber() == 4) return true;
                        }
                        y = x = 9;
                    }
                }
            }
        }
        for(int y = yPos-1; y >= 0; y--) {
            for(int x = 0; x < 8; x++) {
                if((y-yPos)*-2+x+y == xPos+yPos) {
                    tmp = owner.getChessPieceOfSquare(x, y);
                    if(tmp != null) {
                        if(tmp.isBlack() != isBlack()) {
                            if(tmp.getPieceNumber() == 2 || tmp.getPieceNumber() == 4) return true;
                        }
                        y = 0;
                        x = 9;
                    }
                }
            }
        }

        return false;
    }
}