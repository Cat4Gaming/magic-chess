package main;

import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * This class creates the settings user-interface which can be viewed by creating an instance of this class and passing it in as a parameter in the method showView() of the class MainFrame. <br>
 * It also enables to store the settings data in one file.
 * 
 * @author      Andreas Moosbauer (github.com/Cat4Gaming)
 * @version     1.2
 */
public class Settings extends JPanel {
    private MainFrame owner;
    private Font font;
    private int volume;
    private String language;
    
    /**
     * Creates the settings screen which can be viewed by creating an instance of this class and passing it in as a parameter in the method showView() of the class MainFrame.
     * 
     * @param owner It is the owner of this class. It is mainly used to get screen size details and to change the UI view.
     */
    public Settings(MainFrame owner) {
        super();
        this.owner = owner;
        volume = 100;
        language = owner.getTextByTag("langcode");
        createGUI();
    }
    
    /**
     * Creates an UI which displays all the settings one can change. <br>
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
        optionPanel.setLayout(new BorderLayout());
        optionPanel.setOpaque(false);
        
        JPanel bottomOptionPanel = new JPanel();
        bottomOptionPanel.setLayout(new BoxLayout(bottomOptionPanel, BoxLayout.Y_AXIS));
        bottomOptionPanel.setOpaque(false);
        
        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new BoxLayout(languagePanel, BoxLayout.X_AXIS));
        languagePanel.setOpaque(false);
        
        //screen title text:
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
        
        JLabel titleText = new JLabel(" " + owner.getTextByTag("settings"), SwingConstants.LEFT);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));
        
        //language setting:
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(48f);
        } catch(IOException| FontFormatException e) {}
        
        JLabel langText = new JLabel(" " + owner.getTextByTag("language") + ": ");
        langText.setFont(font);
        langText.setForeground(Color.WHITE); 
        langText.setBorder(BorderFactory.createEmptyBorder());
        
        JLabel languageText = new JLabel(owner.getTextByTag("lang"));
        languageText.setFont(font);
        languageText.setForeground(Color.WHITE); 
        languageText.setBorder(BorderFactory.createEmptyBorder());
        
        File directoryPath = new File("assets/lang");
        String contents[] = directoryPath.list();
        
        JButton leftLanguangeButton = new JButton("     <-");
        leftLanguangeButton.setFont(font);
        leftLanguangeButton.setForeground(Color.WHITE); 
        leftLanguangeButton.setContentAreaFilled(false);
        leftLanguangeButton.setBorder(BorderFactory.createEmptyBorder());
        leftLanguangeButton.addActionListener(event -> {
            for(int i = 0; i < contents.length; i++) {
                if(contents[i].equals(owner.getTextByTag("langcode") + ".lang")) {
                    if(i != 0) owner.setLanguage(contents[i-1]);
                    else owner.setLanguage(contents[contents.length-1]);
                    languageText.setText(owner.getTextByTag("lang"));
                    i = contents.length;
                }
            }
        });
        
        JButton rightLanguangeButton = new JButton("  ->");
        rightLanguangeButton.setFont(font);
        rightLanguangeButton.setForeground(Color.WHITE); 
        rightLanguangeButton.setContentAreaFilled(false);
        rightLanguangeButton.setBorder(BorderFactory.createEmptyBorder());
        rightLanguangeButton.addActionListener(event -> {
            for(int i = 0; i < contents.length; i++) {
                if(contents[i].equals(owner.getTextByTag("langcode") + ".lang")) {
                    if(i != contents.length-1) owner.setLanguage(contents[i+1]);
                    else owner.setLanguage(contents[0]);
                    languageText.setText(owner.getTextByTag("lang"));
                    i = contents.length;
                }
            }
        });
        
        //button to apply all settings:
        JButton applyButton = new JButton("             " + owner.getTextByTag("apply"));
        applyButton.setFocusable(false);
        applyButton.setBorder(BorderFactory.createEmptyBorder());
        applyButton.setFont(font);
        applyButton.setForeground(Color.WHITE); 
        applyButton.setContentAreaFilled(false);
        applyButton.addActionListener(event -> {
            language = owner.getTextByTag("langcode");
            saveSettings();
            SwingUtilities.invokeLater(() -> owner.showView(new Settings(owner)));
        });
        
        //button to go back to main menu:
        JButton backButton = new JButton("             " + owner.getTextByTag("back"));
        backButton.setFocusable(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFont(font);
        backButton.setForeground(Color.WHITE); 
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(event -> {
            if(owner.getTextByTag("langcode") != language) owner.setLanguage(language + ".lang");
            SwingUtilities.invokeLater(() -> owner.showView(new Menu(owner)));
        });
        
        //adding the UI elements together:
        languagePanel.add(leftLanguangeButton);
        languagePanel.add(rightLanguangeButton);
        languagePanel.add(langText);
        languagePanel.add(languageText);
        
        bottomOptionPanel.add(applyButton);
        bottomOptionPanel.add(backButton);
        
        optionPanel.add(languagePanel, BorderLayout.NORTH);
        optionPanel.add(bottomOptionPanel, BorderLayout.WEST);
        
        contentPanel.add(titleText, BorderLayout.NORTH);
        contentPanel.add(optionPanel, BorderLayout.WEST);
    }
    
    /**
     * Saves all settings in the file: "data/settings.magic-chess"
     */
    private void saveSettings() {
        SettingsData setStor = new SettingsData();
        setStor.setLanguage(language);
        setStor.save();
    }
}