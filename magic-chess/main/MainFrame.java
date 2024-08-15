package main;

import javax.swing.*;
import java.awt.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

/**
 * This is the main class which should be executed in order to start the magic-chess game. <br>
 * It is also used for getting the screen height and width as well as getting language data. <br>
 * Resizing images is possible too.
 * 
 * @author      Andreas Moosbauer (github.com/Cat4Gaming)
 * @version     1.3
 */
public class MainFrame extends JFrame {
    private int screenWidth, screenHeight;
    private JPanel viewPanel;
    private Document doc;
    private DocumentBuilder builder;
    private String language;
    
    /**
     * This is the main class which should be executed in order to start the magic-chess game. <br>
     * It is also used for getting the screen height and width as well as getting language data. <br>
     * Resizing images is possible too.
     * 
     * @param   appName The name with which the application will be shown.
     */
    public MainFrame(String appName){
        super(appName);
        screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        loadSettings();
        setLanguage(language + ".lang"); //TODO - Settings to change language and automatically detect it.
        createGUI();
    }
    
    /**
     * This method searches the language file given before for the correct xml-tag and returns the text stored there. <br>
     * Warning!: Having not used the setLanguage() method before as well as the xml-tag not existing in the language-file will result in null-exeptions.
     * 
     * @param   tag     the xml-tag which will be searched for in the language-file
     * @return          the text stored in the language-file with the given tag
     */
    public String getTextByTag(String tag) {return doc.getElementsByTagName(tag).item(0).getTextContent();}
    
    /**
     * Sets the file of the language to the String which you give it. <br>
     * The file should have the ".lang" file-extension but any other extension can be used as long as the file uses the xml format.
     * Warning!: Missing xml tags in the file will lead to errors later.
     * 
     * @param   lang    the language file located in "assets/lang/" | it must include the file extension | file must use the xml format
     */
    public void setLanguage(String lang){
        String src = "assets/lang/" + lang;
        try {builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();}
        catch (ParserConfigurationException pce) {pce.printStackTrace();}
        try {
            try {doc = builder.parse(src);}
            catch (java.io.IOException ioe) {ioe.printStackTrace();}
        }
        catch (org.xml.sax.SAXException saxe) {saxe.printStackTrace();}
    }
    
    /**
     * It creates the base of the UI and automatically loads the main-menu when creating an instance of this class. <br>
     * This method is mandatory for the creation of the UI.
     */
    private void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //defines what is done when window is getting closed
        setLayout(new BorderLayout());
        setResizable(false);
        setMinimumSize(new Dimension(screenWidth, screenHeight));
        setUndecorated(true);
        viewPanel = new JPanel(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        showView(new Menu(this));   //defines which view is shown first
        pack();
    }
    
    /**
     * Shows the UI which is passed into the method to the user. <br>
     * Any class which extends JPanel can be used.
     * 
     * @param   newViewPanel    JPanel to be shown to the user
     */
    public void showView(JPanel newViewPanel) {
        viewPanel.removeAll();
        viewPanel.add(newViewPanel, BorderLayout.CENTER);
        viewPanel.revalidate();
        viewPanel.repaint();
    }
    
    /**
     * Returns the width of the screen in pixels.
     * 
     * @return  screen width in pixels
     */
    public int getScreenWidth() {return screenWidth;}

    /**
     * Returns the height of the screen in piexels.
     * 
     * @return  screen height in pixels
     */
    public int getScreenHeight() {return screenHeight;}
    
    /**
     * Resizes image from a given filePah to the specified width and height and returns it as an ImageIcon as its most commonly used in ths UI.
     * 
     * @param   
     * @return  ImageIcon   the resized image as an ImageIcon
     */
    public ImageIcon resizedImageIcon(String filePath, int imageWidth, int imageHeight) {return new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT));}
    
    /**
     * Loads necesary settings into variables of this class.
     */
    private void loadSettings() {
        //loading settings:
        language = new SettingsData().getLanguage();
        
        //detection and automatic completion of missing settings:
        if(language == null) {
            File directoryPath = new File("assets/lang");
            String contents[] = directoryPath.list();
            for(int i = 0; i < contents.length; i++) {
                if(contents[i].equals(System.getProperty("user.language") + ".lang")) {     //checks if System language of user corresponds to any language-file
                    language = System.getProperty("user.language");
                    i = contents.length;
                }
            }
        }
        else language = "en";   //standard lanugange is set to english
    }
}