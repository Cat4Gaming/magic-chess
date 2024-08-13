package mp;

import javax.swing.*;
import java.awt.*;
import main.MainFrame;

public class PawnUpgradeButton extends JButton {
    
    public PawnUpgradeButton(int type, Game owner) {
        MainFrame tmp = owner.getOwner();
        setFocusable(false);
        setLayout(new BorderLayout());
        if(type%2 == 0) setBackground(new Color(99, 150, 184));
        else setBackground(new Color(225, 230, 237));
        setOpaque(true);
        switch(type) {
            case 0:     setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Rook.png", ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4, ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4));
                break;
            case 1:     setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Knight.png", ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4, ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4));
                break;
            case 2:     setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Bishop.png", ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4, ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4));
                break;
            case 3:     setIcon(tmp.resizedImageIcon("assets/gfx/pieces/white/Queen.png", ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4, ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4));
                break;
            case 10:    setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Rook.png", ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4, ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4));
                break;
            case 11:    setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Knight.png", ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4, ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4));
                break;
            case 12:    setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Bishop.png", ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4, ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4));
                break;
            case 13:    setIcon(tmp.resizedImageIcon("assets/gfx/pieces/black/Queen.png", ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4, ((tmp.getScreenWidth()-tmp.getScreenHeight())/2+(tmp.getScreenHeight()/10))/4));
                break;
        }
        addActionListener(event -> {
            owner.pawnPromotionResponse(type);
        });
    }
}