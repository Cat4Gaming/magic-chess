package mp;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import main.MainFrame;
import main.Menu;

public class Game extends JPanel {
    final private MainFrame owner;
    private Font font;
    private Square[][] square;
    
    public Game(MainFrame owner) {
        super();
        this.owner = owner;
        createGUI();
    }
    
    private void createGUI() {        
        setBounds(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
        setLayout(new BorderLayout());
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(8,8));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(owner.getScreenHeight()/10, (owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10), owner.getScreenHeight()/10, (owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10)));
        
        square = new Square[8][8];        
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                if((x+y)%2 == 0) square[x][y] = new Square(false, x, y);
                else square[x][y] = new Square(true, x, y);
                contentPanel.add(square[x][y]);
            }
        }
        
        add(contentPanel, BorderLayout.CENTER);
    }
}