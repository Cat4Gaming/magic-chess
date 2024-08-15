package main;

import java.awt.*;
import javax.swing.*;
import java.io.*;

 /**
 * Creates the main-menu screen which can be viewed by creating an instance of this class and passing it in as a parameter in the method showView() of the class MainFrame.
 * 
 * @author      Andreas Moosbauer (github.com/Cat4Gaming)
 * @version     1.0.2
 */
public class Menu extends JPanel {
    private MainFrame owner;
    private Font font;
    
    /**
    * Creates the main-menu screen which can be viewed by creating an instance of this class and passing it in as a parameter in the method showView() of the class MainFrame.
    * 
    * @param owner It is the owner of this class. It is mainly used to get screen size details and to change the UI view.
    */
    public Menu(MainFrame owner) {
        super();
        this.owner = owner;
        createGUI();
    }
    
    /**
     * Creates an UI which displays the main menu. <br>
     * This will be displayed to the user after creating an instance of this class. <br>
     * This method is mandatory for the creation of the UI.
     */
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
        
        //screen title text:
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
        
        JLabel titleText = new JLabel(" " + owner.getTextByTag("title"), SwingConstants.LEFT);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));

        //Buttons for UI contorl:
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(48f);
        } catch(IOException| FontFormatException e) {}
        
        JButton spButton = new JButton("     " + owner.getTextByTag("singleplayer"));
        spButton.setFocusable(false);
        spButton.setBorder(BorderFactory.createEmptyBorder());
        spButton.setFont(font);
        spButton.setForeground(Color.WHITE); 
        spButton.setContentAreaFilled(false);
        spButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new sp.Game(owner)));
        });
        
        JButton mpButton = new JButton("     " + owner.getTextByTag("multiplayer"));
        mpButton.setFocusable(false);
        mpButton.setBorder(BorderFactory.createEmptyBorder());
        mpButton.setFont(font);
        mpButton.setForeground(Color.WHITE); 
        mpButton.setContentAreaFilled(false);
        mpButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new mp.Game(owner)));
        });
        
        JButton settingsButton = new JButton("     " + owner.getTextByTag("settings"));
        settingsButton.setFocusable(false);
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setFont(font);
        settingsButton.setForeground(Color.WHITE); 
        settingsButton.setContentAreaFilled(false);
        settingsButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new Settings(owner)));
        });

        JButton closeButton = new JButton("     " + owner.getTextByTag("quit"));
        closeButton.setFocusable(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setFont(font);
        closeButton.setForeground(Color.WHITE); 
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(event -> {
            owner.dispose();
        });
                
        //Shows warning in the bottom of the screen if the reatio of the screen is not 16:9 or 16:10 as this can lead to unintended behaviour.
        //TODO: Change screen resolution automatically it this warning is detected.
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(16f);
        } catch(IOException| FontFormatException e) {}
        
        int screenRatio = owner.getScreenWidth()*10 / owner.getScreenHeight();
        if(screenRatio < 15 || screenRatio > 18) {   
            JLabel warningText = new JLabel(owner.getTextByTag("screenSizeWarning"),  SwingConstants.CENTER);
            warningText.setForeground(Color.ORANGE);
            warningText.setBackground(Color.BLACK);
            warningText.setOpaque(true);
            warningText.setFont(font);
            contentPanel.add(warningText, BorderLayout.PAGE_END);
        }
        
        //adding the UI elements together:
        //optionPanel.add(spButton);    //Blocked until singleplayer is impplemented!
        optionPanel.add(mpButton);
        optionPanel.add(settingsButton);
        optionPanel.add(closeButton);

        contentPanel.add(titleText, BorderLayout.PAGE_START);
        contentPanel.add(optionPanel, BorderLayout.WEST);
    }
}