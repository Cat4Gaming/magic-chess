package mp;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    boolean isBlackSquare;
    ChessPiece chessPiece;
    int xPos, yPos;
    
    public Square(boolean isBlackSqu, int x, int y, ChessPiece chessPiec) {
        this(isBlackSqu, x, y);
        chessPiece = chessPiec;
    }
    
    public Square(boolean isBlackSqu, int x, int y) {
        xPos = x;
        yPos = y;
        isBlackSquare = isBlackSqu;
        setFocusable(false);
        setBorder(BorderFactory.createEmptyBorder());
        if(isBlackSquare) this.setBackground(new Color(99, 150, 184));
        else setBackground(new Color(225, 230, 237));
        setOpaque(true);
        addActionListener(event -> {
            //TODO
        });
    }
    
    public void setChessPiece(ChessPiece chessPiec) {
        chessPiece = chessPiec;
        chessPiece.moveTo(xPos, yPos);
    }
}