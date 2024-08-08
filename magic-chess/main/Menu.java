package main;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Menu extends JPanel {
    final private MainFrame owner;
    private Font font;
    
    public Menu(MainFrame owner) {
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
        
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setOpaque(false);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
        
        JLabel titleText = new JLabel(" Magic-Chess", SwingConstants.LEFT);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(48f);
        } catch(IOException| FontFormatException e) {}

        JButton spButton = new JButton("     Singleplayer");
        spButton.setFocusable(false);
        spButton.setBorder(BorderFactory.createEmptyBorder());
        spButton.setFont(font);
        spButton.setForeground(Color.WHITE); 
        spButton.setContentAreaFilled(false);
        spButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new sp.Game(owner)));
        });
        
        JButton mpButton = new JButton("     Multiplayer");
        mpButton.setFocusable(false);
        mpButton.setBorder(BorderFactory.createEmptyBorder());
        mpButton.setFont(font);
        mpButton.setForeground(Color.WHITE); 
        mpButton.setContentAreaFilled(false);
        mpButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new mp.Game(owner)));
        });
        
        JButton settingsButton = new JButton("     Settings");
        settingsButton.setFocusable(false);
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setFont(font);
        settingsButton.setForeground(Color.WHITE); 
        settingsButton.setContentAreaFilled(false);
        settingsButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new Settings(owner)));
        });

        JButton closeButton = new JButton("     Quit");
        closeButton.setFocusable(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setFont(font);
        closeButton.setForeground(Color.WHITE); 
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(event -> {
            owner.dispose();
        });

        int screenRatio = owner.getScreenWidth()*10 / owner.getScreenHeight();
        if(screenRatio < 15 || screenRatio > 18) {   
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(16f);
            } catch(IOException| FontFormatException e) {}
            
            JLabel warningText = new JLabel("Warning!: Using a resolution with a aspect ratio other than 16:10 or 16:9 may result in unintended behaviour. For the best user experience you should change your display resolution in your system settings.",  SwingConstants.CENTER);
            warningText.setForeground(Color.ORANGE);
            warningText.setBackground(Color.BLACK);
            warningText.setOpaque(true);
            warningText.setFont(font);
            contentPanel.add(warningText, BorderLayout.PAGE_END);
        }

        optionPanel.add(spButton);
        optionPanel.add(mpButton);
        optionPanel.add(settingsButton);
        optionPanel.add(closeButton);

        contentPanel.add(titleText, BorderLayout.PAGE_START);
        contentPanel.add(optionPanel, BorderLayout.WEST);
    }
}