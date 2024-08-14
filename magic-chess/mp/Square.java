package mp;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    boolean isBlackSquare, isSelectable;
    ChessPiece chessPiece;
    int xPos, yPos;
    Game owner;
    
    public Square(boolean isBlackSqu, int x, int y, ChessPiece chessPiec, Game owner) {
        this(isBlackSqu, x, y, owner);
        chessPiece = chessPiec;
    }
    
    public Square(boolean isBlackSqu, int x, int y, Game owner) {
        this.owner = owner;
        xPos = x;
        yPos = y;
        isBlackSquare = isBlackSqu;
        setFocusable(false);
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new BorderLayout());
        if(isBlackSquare) setBackground(new Color(99, 150, 184));
        else setBackground(new Color(225, 230, 237));
        setOpaque(true);
        addActionListener(event -> {
            owner.selectSquare(xPos, yPos, isSelectable);
        });
    }
    
    public void setChessPiece(ChessPiece chessPiec) {
        if(chessPiece == null && chessPiec != null) owner.capture(false);
        else if(chessPiece != null && chessPiec != null) owner.capture(true);
        if(chessPiece != null) {
            remove(chessPiece);
        }
        chessPiece = chessPiec;
        if(chessPiec != null && isSelectable) {
            chessPiece.moveTo(xPos, yPos);
            add(chessPiece);
        }
    }
    
    public ChessPiece getChessPiece() {return chessPiece;}
    
    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
        if(selectable) {
            if(isBlackSquare) setBackground(new Color(99, 184, 142));
            else setBackground(new Color(156, 247, 202));
            return;
        }
        if(isBlackSquare) setBackground(new Color(99, 150, 184));
        else setBackground(new Color(225, 230, 237));
    }
}