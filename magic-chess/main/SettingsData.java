package main;

import java.io.Serializable;
import java.io.*;

/**
 * This class is only used to store and load the saved settings. <br>
 * It stores the settings in the file: "data/settings.magic-chess" <br><br>
 * Currently you can only store which language to use.
 *  
 * @author      Andreas Moosbauer (github.com/Cat4Gaming)
 * @version     1.1
 */
public class SettingsData implements Serializable {
    //settings stored as variables:
    private String language;
    
    /**
     * Creating this class will automatically load all the stored settings from: "data/settings.magic-chess"
     */
    public SettingsData() {
        try {
            FileInputStream fis = new FileInputStream("data/settings.magic-chess");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            SettingsData setStor = (SettingsData)ois.readObject();
            //load all stored settings here:
            language = setStor.getLanguage();
            
            ois.close();
        } 
        catch(IOException e) {} 
        catch(ClassNotFoundException e) {}
    }
    
    /**
     * This method is saving all the data stored for later use. It will be stored in: "data/settings.magic-chess"
     */
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("data/settings.magic-chess");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            SettingsData setStor = this;
            oos.writeObject(setStor);
            oos.close();
        } catch(IOException e) {e.printStackTrace();}
    }
    
    /**
     * Returns the language stored in the settings file.
     * 
     * @return returns the language stored in this class.
     */
    public String getLanguage() {return language;}
    
    /**
     * Sets the language that will be stored in the settings file. <br>
     * Warning!: by this point the settings are not stored only after using the store() method.
     * 
     * @param l stores the language in this class.
     */
    public void setLanguage(String l) {language = l;}
}