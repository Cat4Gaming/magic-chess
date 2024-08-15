import javax.swing.*;

 /**
 * Starts Magic-Chess. <br>
 * By starting the main method you will start the game.
 * 
 * @author      Andreas Moosbauer (github.com/Cat4Gaming)
 * @version     1.0.1
 */
public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new main.MainFrame("Magic-Chess").setVisible(true);
        });
    }
}