package sp;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import main.MainFrame;
import main.Menu;

public class Game extends JPanel {
    final private MainFrame owner;
    private Font font;
    
    public Game(MainFrame owner) {
        super();
        this.owner = owner;
        createGUI();
    }
    
    private void createGUI() {        
        setBounds(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
        setLayout(new BorderLayout());
        
        JLabel contentPanel = new JLabel();
        contentPanel.setIcon(owner.resizedImageIcon("assets/gfx/menubackground.png", owner.getScreenWidth(), owner.getScreenHeight()));
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel);
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
                
        JLabel titleText = new JLabel(" " + owner.getTextByTag("singleplayer"), SwingConstants.LEFT);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));
        
        contentPanel.add(titleText, BorderLayout.PAGE_START);
    }
}