package main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private int screenWidth, screenHeight;
    private JPanel viewPanel;
    
    public MainFrame(String appName) {
        super(appName);
        screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        createGUI();
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
}