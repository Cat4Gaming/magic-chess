package mp;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import main.MainFrame;
import main.Menu;

public class Game extends JPanel {
    final private MainFrame owner;
    private Font font;
    private Square[][] square;
    private ChessPiece selectedChessPiece;
    private boolean blackTurn, isGameOver;
    private JLabel wonText;
    
    public Game(MainFrame owner) {
        super();
        this.owner = owner;
        blackTurn = isGameOver = false;
        createGUI();
    }
    
    private void createGUI() {        
        setBounds(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
        setLayout(new BorderLayout());
        
        JLabel contentPanel = new JLabel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setIcon(owner.resizedImageIcon("assets/gfx/background.png", owner.getScreenWidth(), owner.getScreenHeight()));
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Yusei_Magic/YuseiMagic-Regular.ttf")).deriveFont(48f);
        } catch(IOException| FontFormatException e) {}
        
        JPanel leftContentPanel = new JPanel();
        leftContentPanel.setLayout(new BoxLayout(leftContentPanel, BoxLayout.Y_AXIS));
        leftContentPanel.setPreferredSize(new Dimension((owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10), owner.getScreenHeight()));
        leftContentPanel.setOpaque(false);
        
        JPanel rightContentPanel = new JPanel();
        rightContentPanel.setLayout(new BoxLayout(rightContentPanel, BoxLayout.Y_AXIS));
        rightContentPanel.setPreferredSize(new Dimension((owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10), owner.getScreenHeight()));
        rightContentPanel.setOpaque(false);
        
        JPanel topContentPanel = new JPanel();
        topContentPanel.setLayout(new BoxLayout(topContentPanel, BoxLayout.X_AXIS));
        topContentPanel.setPreferredSize(new Dimension(owner.getScreenWidth(), owner.getScreenHeight()/10));
        topContentPanel.setOpaque(false);
        
        JPanel bottomContentPanel = new JPanel();
        bottomContentPanel.setLayout(new BoxLayout(bottomContentPanel, BoxLayout.X_AXIS));
        bottomContentPanel.setPreferredSize(new Dimension(owner.getScreenWidth(), owner.getScreenHeight()/10));
        bottomContentPanel.setOpaque(false);
        
        JButton restartButton = new JButton(owner.getTextByTag("restartGame"));
        restartButton.setFocusable(false);
        restartButton.setBorder(BorderFactory.createEmptyBorder());
        restartButton.setFont(font);
        restartButton.setForeground(Color.BLACK); 
        restartButton.setContentAreaFilled(false);
        restartButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new Game(owner)));
        });
        
        JButton menuButton = new JButton(owner.getTextByTag("backToMenu"));
        menuButton.setFocusable(false);
        menuButton.setBorder(BorderFactory.createEmptyBorder());
        menuButton.setFont(font);
        menuButton.setForeground(Color.BLACK); 
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new main.Menu(owner)));
        });
        
        wonText = new JLabel();
        wonText.setFont(font);
        wonText.setForeground(Color.BLACK);
        
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8,8));
        boardPanel.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createLineBorder(Color.BLACK,5)));
        boardPanel.setBackground(Color.BLACK);
        
        square = new Square[8][8];        
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                if((x+y)%2 == 0) square[x][y] = new Square(false, x, y, this);
                else square[x][y] = new Square(true, x, y, this);
                square[x][y].setSelectable(true);
            }
        }
        
        for(int i = 0; i < 8; i++) {
            square[i][6].setChessPiece(new Pawn(this, false));
            square[i][1].setChessPiece(new Pawn(this, true));
        }
        square[0][0].setChessPiece(new Rook(this, true));
        square[7][0].setChessPiece(new Rook(this, true));
        square[0][7].setChessPiece(new Rook(this, false));
        square[7][7].setChessPiece(new Rook(this, false));
        square[1][0].setChessPiece(new Knight(this, true));
        square[6][0].setChessPiece(new Knight(this, true));
        square[1][7].setChessPiece(new Knight(this, false));
        square[6][7].setChessPiece(new Knight(this, false));
        square[2][0].setChessPiece(new Bishop(this, true));
        square[5][0].setChessPiece(new Bishop(this, true));
        square[2][7].setChessPiece(new Bishop(this, false));
        square[5][7].setChessPiece(new Bishop(this, false));
        square[4][7].setChessPiece(new Queen(this, false));
        square[3][7].setChessPiece(new King(this, false));
        square[3][0].setChessPiece(new King(this, true));
        square[4][0].setChessPiece(new Queen(this, true));
        
        setAllUnselected();
               
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                boardPanel.add(square[x][y]);
            }
        }

        leftContentPanel.add(restartButton);
        leftContentPanel.add(menuButton);  
        rightContentPanel.add(wonText);
        
        contentPanel.add(boardPanel, BorderLayout.CENTER);
        contentPanel.add(rightContentPanel, BorderLayout.EAST);
        contentPanel.add(leftContentPanel, BorderLayout.WEST);
        contentPanel.add(topContentPanel, BorderLayout.NORTH);
        contentPanel.add(bottomContentPanel, BorderLayout.SOUTH);
        add(contentPanel);
    }
    
    public void selectSquare(int x, int y, boolean selectable) {
        if(selectable) {
            ChessPiece tmp = square[x][y].getChessPiece();
            if(tmp != null && tmp.isKing()) {
                isGameOver = true;
                if(blackTurn) wonText.setText(owner.getTextByTag("blackWon"));
                else wonText.setText(owner.getTextByTag("whiteWon"));
            }
            blackTurn = !blackTurn;
            
            square[selectedChessPiece.getXPos()][selectedChessPiece.getYPos()].setChessPiece(null);
            square[x][y].setChessPiece(selectedChessPiece);
            selectedChessPiece = null;
            
            setAllUnselected();
            updateUI();
            return;
        }
        setAllUnselected();
        if(square[x][y].getChessPiece() != null && !isGameOver) {
            selectedChessPiece = square[x][y].getChessPiece();
            if(blackTurn && selectedChessPiece.isBlack()) selectedChessPiece.showMoves();
            else if(!blackTurn && selectedChessPiece.isBlack() == false) selectedChessPiece.showMoves();
        }
        updateUI();
    }
    
    public void setSquareSelectable(int x, int y, boolean selectable) {square[x][y].setSelectable(selectable);}
    
    private void setAllUnselected() {
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                square[x][y].setSelectable(false);
            }
        }
    }
    
    public ChessPiece getChessPieceOfSquare(int x, int y) {return square[x][y].getChessPiece();}
    
    public MainFrame getOwner() {return owner;}
    
    public void pawnUpgrade(int x, int y, boolean isBlack) {
        //TODO
    }
}