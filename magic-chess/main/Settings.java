package main;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Settings extends JPanel {
    final private MainFrame owner;
    private Font font;
    private int volume;
    
    public Settings(MainFrame owner) {
        super();
        this.owner = owner;
        volume = 100;
        createGUI();
    }
    
    private void createGUI() {        
        setBounds(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
        setLayout(new BorderLayout());
        
        JLabel contentPanel = new JLabel();
        contentPanel.setIcon(owner.resizedImageIcon("assets/gfx/menubackground.png", owner.getScreenWidth(), owner.getScreenHeight()));
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel);
        
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setOpaque(false);
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
        
        JLabel titleText = new JLabel(" Settings", SwingConstants.LEFT);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(48f);
        } catch(IOException| FontFormatException e) {}

        JButton applyButton = new JButton("     Apply");
        applyButton.setFocusable(false);
        applyButton.setBorder(BorderFactory.createEmptyBorder());
        applyButton.setFont(font);
        applyButton.setForeground(Color.WHITE); 
        applyButton.setContentAreaFilled(false);
        applyButton.addActionListener(event -> {
            //TODO
        });
        
        JButton backButton = new JButton("     Back");
        backButton.setFocusable(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFont(font);
        backButton.setForeground(Color.WHITE); 
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new Menu(owner)));
        });
        
        optionPanel.add(applyButton);
        optionPanel.add(backButton);
        
        contentPanel.add(titleText, BorderLayout.PAGE_START);
        contentPanel.add(optionPanel, BorderLayout.WEST);
    }
}