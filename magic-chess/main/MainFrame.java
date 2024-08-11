package main;

import javax.swing.*;
import java.awt.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class MainFrame extends JFrame {
    private int screenWidth, screenHeight;
    private JPanel viewPanel;
    private Document doc;
    private DocumentBuilder builder;
    private String language;
    
    public MainFrame(String appName){
        super(appName);
        screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        loadSettings();
        setLanguage(language + ".lang"); //TODO - Settings to change language and automatically detect it.
        createGUI();
    }
    
    public String getTextByTag(String tag) {
        return doc.getElementsByTagName(tag).item(0).getTextContent();
    }
    
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
    
    private void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setMinimumSize(new Dimension(screenWidth, screenHeight));
        setUndecorated(true);
        viewPanel = new JPanel(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        showView(new Menu(this));
        pack();
    }
    
    public void showView(JPanel newViewPanel) {
        viewPanel.removeAll();
        viewPanel.add(newViewPanel, BorderLayout.CENTER);
        viewPanel.revalidate();
        viewPanel.repaint();
    }
    
    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
    
    public ImageIcon resizedImageIcon(String filePath, int imageWidth, int imageHeight) {
        ImageIcon ii = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT));
        return ii;
    }
    
    public void loadSettings() {
        try {
            FileInputStream fis = new FileInputStream("data/settings.magic-chess");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            SettingsData setStor = (SettingsData)ois.readObject();
            language = setStor.language;
            ois.close();
        } 
        catch(IOException e) {} 
        catch(ClassNotFoundException e) {}
        if(language == null) {
            File directoryPath = new File("assets/lang");
            String contents[] = directoryPath.list();
            for(int i = 0; i < contents.length; i++) {
                if(contents[i].equals(System.getProperty("user.language") + ".lang")) {
                    language = System.getProperty("user.language");
                    i = contents.length;
                }
            }
        }
        else language = "en";
    }
}