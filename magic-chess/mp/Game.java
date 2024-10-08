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
    private Pawn pawnForUpgrade;
    private boolean blackTurn, gameFieldStop;
    private JLabel wonText;
    private JPanel changeWhitePawnPanel, changeBlackPawnPanel;
    private JButton restartButton, drawButton;
    private int passedMoves;
    private GameplayData gameplayData;
    
    public Game(MainFrame owner) {
        super();
        this.owner = owner;
        blackTurn = gameFieldStop = false;
        gameplayData = new GameplayData();
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
        
        JPanel rightContentPanel = new JPanel();
        rightContentPanel.setLayout(new BorderLayout());
        rightContentPanel.setPreferredSize(new Dimension((owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10), owner.getScreenHeight()));
        rightContentPanel.setOpaque(false);
        
        changeWhitePawnPanel = new JPanel();
        changeWhitePawnPanel.setLayout(new GridLayout(1,4));
        changeWhitePawnPanel.setPreferredSize(new Dimension((owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10), ((owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10))/4));
        changeWhitePawnPanel.setOpaque(true);
        changeWhitePawnPanel.setVisible(false);
        
        changeBlackPawnPanel = new JPanel();
        changeBlackPawnPanel.setLayout(new GridLayout(1,4));
        changeBlackPawnPanel.setPreferredSize(new Dimension((owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10), ((owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10))/4));
        changeBlackPawnPanel.setOpaque(false);
        changeBlackPawnPanel.setVisible(false);
        
        for(int i = 0; i < 4; i++) {
            changeWhitePawnPanel.add(new PawnUpgradeButton(i, this));
            changeBlackPawnPanel.add(new PawnUpgradeButton(i+10, this));
        }
        
        JPanel topContentPanel = new JPanel();
        topContentPanel.setLayout(new BoxLayout(topContentPanel, BoxLayout.X_AXIS));
        topContentPanel.setPreferredSize(new Dimension(owner.getScreenWidth(), owner.getScreenHeight()/10));
        topContentPanel.setOpaque(false);
        
        JPanel bottomContentPanel = new JPanel();
        bottomContentPanel.setLayout(new BoxLayout(bottomContentPanel, BoxLayout.X_AXIS));
        bottomContentPanel.setPreferredSize(new Dimension(owner.getScreenWidth(), owner.getScreenHeight()/10));
        bottomContentPanel.setOpaque(false);
        
        JPanel leftContentPanel = new JPanel();
        leftContentPanel.setLayout(new BoxLayout(leftContentPanel, BoxLayout.Y_AXIS));
        leftContentPanel.setPreferredSize(new Dimension((owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10), owner.getScreenHeight()));
        leftContentPanel.setOpaque(false);
        
        restartButton = new JButton(owner.getTextByTag("restartGame"));
        restartButton.setFocusable(false);
        restartButton.setBorder(BorderFactory.createEmptyBorder());
        restartButton.setFont(font);
        restartButton.setForeground(Color.BLACK); 
        restartButton.setContentAreaFilled(false);
        restartButton.setVisible(false);
        restartButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new Game(owner)));
        });

        drawButton = new JButton(owner.getTextByTag("drawAgree"));
        drawButton.setFocusable(false);
        drawButton.setBorder(BorderFactory.createEmptyBorder());
        drawButton.setFont(font);
        drawButton.setForeground(Color.BLACK); 
        drawButton.setContentAreaFilled(false);
        drawButton.addActionListener(event -> {
            gameFieldStop = true;
            wonText.setText(owner.getTextByTag("drawGame"));
            saveGame("draw-agree");
            drawButton.setVisible(false);
            restartButton.setVisible(true);
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
        square[3][7].setChessPiece(new Queen(this, false));
        square[4][7].setChessPiece(new King(this, false));
        square[4][0].setChessPiece(new King(this, true));
        square[3][0].setChessPiece(new Queen(this, true));
        
        setAllUnselected();
               
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                boardPanel.add(square[x][y]);
            }
        }

        leftContentPanel.add(restartButton);
        leftContentPanel.add(drawButton);
        leftContentPanel.add(menuButton);  
        
        rightContentPanel.add(changeBlackPawnPanel, BorderLayout.NORTH);
        rightContentPanel.add(wonText, BorderLayout.CENTER);
        rightContentPanel.add(changeWhitePawnPanel, BorderLayout.SOUTH);
        
        contentPanel.add(boardPanel, BorderLayout.CENTER);
        contentPanel.add(rightContentPanel, BorderLayout.EAST);
        contentPanel.add(leftContentPanel, BorderLayout.WEST);
        contentPanel.add(topContentPanel, BorderLayout.NORTH);
        contentPanel.add(bottomContentPanel, BorderLayout.SOUTH);
        add(contentPanel);
    }
    
    public void selectSquare(int x, int y, boolean selectable) {
        int blackPoints = 0;
        int whitePoints = 0;
        for(int yB = 0; yB < 8; yB++) {
            for(int xB = 0; xB < 8; xB++) {
                if(square[xB][yB].getChessPiece() != null && square[xB][yB].getChessPiece().isBlack() && square[xB][yB].getChessPiece().getPieceNumber() != 5 && square[xB][yB].getChessPiece().getPieceNumber() != 3 && square[xB][yB].getChessPiece().getPieceNumber() != 2) blackPoints = -10000;
                if(square[xB][yB].getChessPiece() != null && square[xB][yB].getChessPiece().isBlack() == false && square[xB][yB].getChessPiece().getPieceNumber() != 5 && square[xB][yB].getChessPiece().getPieceNumber() != 3 && square[xB][yB].getChessPiece().getPieceNumber() != 2) whitePoints = -10000;
                if(square[xB][yB].getChessPiece() != null && square[xB][yB].getChessPiece().isBlack() && square[xB][yB].getChessPiece().getPieceNumber() == 5) blackPoints += 5;
                if(square[xB][yB].getChessPiece() != null && square[xB][yB].getChessPiece().isBlack() == false && square[xB][yB].getChessPiece().getPieceNumber() == 5) whitePoints += 5;
                if(square[xB][yB].getChessPiece() != null && square[xB][yB].getChessPiece().isBlack() && square[xB][yB].getChessPiece().getPieceNumber() > 1 && square[xB][yB].getChessPiece().getPieceNumber() < 4) blackPoints += 3;
                if(square[xB][yB].getChessPiece() != null && square[xB][yB].getChessPiece().isBlack() == false && square[xB][yB].getChessPiece().getPieceNumber() > 1 && square[xB][yB].getChessPiece().getPieceNumber() < 4) whitePoints += 3;
            }
        }
        if(blackPoints > 4 && whitePoints > 4) {
            gameFieldStop = true;
            wonText.setText(owner.getTextByTag("drawGame"));
            saveGame("draw-insufmat");
            drawButton.setVisible(false);
            restartButton.setVisible(true);
        }
            
        if(selectable) {
            ChessPiece tmp = square[x][y].getChessPiece();
            if(tmp != null && tmp.getPieceNumber() == 5) {
                gameFieldStop = true;
                restartButton.setVisible(true);
                drawButton.setVisible(false);
                if(blackTurn) { 
                    wonText.setText(owner.getTextByTag("blackWon"));
                    saveGame("win-black");
                }
                else { 
                    wonText.setText(owner.getTextByTag("whiteWon"));
                    saveGame("win-white");
                }
            }
                        
            square[selectedChessPiece.getXPos()][selectedChessPiece.getYPos()].setChessPiece(null);
            square[x][y].setChessPiece(selectedChessPiece);
            selectedChessPiece = null;
            
            if(!gameFieldStop )wonText.setText("");
            for(int yB = 0; yB < 8; yB++) {
                for(int xB = 0; xB < 8; xB++) {
                    ChessPiece temp = square[xB][yB].getChessPiece();
                    if(temp != null && temp.getPieceNumber() == 5) {
                        if(((King)temp).detectCheck()) {
                            if(temp.isBlack() == blackTurn) {
                                boolean tmpD = false;
                                for(int i = 4; i > 0; i--) {
                                    for(int yC = 0; yC < 8; yC++) {
                                        for(int xC = 0; xC < 8; xC++) {
                                            if(square[xC][yC].getChessPiece() != null && square[xC][yC].getChessPiece().isBlack() == blackTurn && square[xC][yC].getChessPiece().getPieceNumber() == i) {
                                                square[xC][yC].setSelectable(true);
                                                square[xC][yC].setChessPiece(null);
                                                square[xC][yC].setSelectable(false);
                                                tmpD = true;
                                                i = -10;
                                                yC = xC = 10;
                                            }
                                        }
                                    }
                                }
                                if(!tmpD) {
                                    gameFieldStop = true;
                                    restartButton.setVisible(true);
                                    drawButton.setVisible(false);
                                    if(!blackTurn) { 
                                        wonText.setText(owner.getTextByTag("blackWon"));
                                        saveGame("checkwin-black");
                                    }
                                    else { 
                                        wonText.setText(owner.getTextByTag("whiteWon"));
                                        saveGame("checkwin-white");
                                    }
                                }
                            }
                        else wonText.setText(owner.getTextByTag("inCheck"));
                        }
                    }
                }
            }
            
            blackTurn = !blackTurn;
            
            setAllUnselected();
            updateUI();
            
            if(gameplayData.addMoveDataAndCheckThreefold(square)) {
                gameFieldStop = true;
                wonText.setText(owner.getTextByTag("drawGame"));
                saveGame("draw-threefold");
                drawButton.setVisible(false);
                restartButton.setVisible(true);
            }
            return;
        }
        setAllUnselected();
        if(square[x][y].getChessPiece() != null && !gameFieldStop) {
            selectedChessPiece = square[x][y].getChessPiece();
            if(blackTurn && selectedChessPiece.isBlack()) { 
                selectedChessPiece.showMoves();
            }
            else if(!blackTurn && selectedChessPiece.isBlack() == false) { 
                selectedChessPiece.showMoves();
            }
            for(int yB = 0; yB < 8; yB++) {
                for(int xB = 0; xB < 8; xB++) {
                    if(square[xB][yB].getChessPiece() != null && blackTurn == square[xB][yB].getChessPiece().isBlack()) square[xB][yB].getChessPiece().resetEnPassant();
                }
            }
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
    
    public void pawnPromotion(Pawn cp) {
        gameFieldStop = true;
        if(cp.isBlack()) changeBlackPawnPanel.setVisible(true);
        else changeWhitePawnPanel.setVisible(true);
        pawnForUpgrade = cp;
    }
    
    public void pawnPromotionResponse(int type) {
        square[pawnForUpgrade.getXPos()][pawnForUpgrade.getYPos()].setChessPiece(null);
        ChessPiece tmp = null;
        switch(type) {
            case 0:     tmp = new Rook(this, false);
                break;
            case 1:     tmp = new Knight(this, false);
                break;
            case 2:     tmp = new Bishop(this, false);
                break;
            case 3:     tmp = new Queen(this, false);
                break;
            case 10:    tmp = new Rook(this, true);
                break;
            case 11:    tmp = new Knight(this, true);
                break;
            case 12:    tmp = new Bishop(this, true);
                break;
            case 13:    tmp = new Queen(this, true);
                break;
        }
        square[pawnForUpgrade.getXPos()][pawnForUpgrade.getYPos()].setSelectable(true);
        square[pawnForUpgrade.getXPos()][pawnForUpgrade.getYPos()].setChessPiece(tmp);
        square[pawnForUpgrade.getXPos()][pawnForUpgrade.getYPos()].setSelectable(false);
        changeBlackPawnPanel.setVisible(false);
        changeWhitePawnPanel.setVisible(false);
        gameFieldStop = false;
        selectedChessPiece = null;
        updateUI();
    }
    
    public void castling(int x, boolean isBlack) {
        if(!isBlack) {
            if(x == 6) {
                selectedChessPiece = square[7][7].getChessPiece();
                square[7][7].setChessPiece(null);
                square[5][7].setSelectable(true);
                square[5][7].setChessPiece(selectedChessPiece);
            }
            if(x == 2) {
                selectedChessPiece = square[0][7].getChessPiece();
                square[0][7].setChessPiece(null);
                square[3][7].setSelectable(true);
                square[3][7].setChessPiece(selectedChessPiece);
            }
        }
        else {
            if(x == 6) {
                selectedChessPiece = square[7][0].getChessPiece();
                square[7][0].setChessPiece(null);
                square[5][0].setSelectable(true);
                square[5][0].setChessPiece(selectedChessPiece);
            }
            if(x == 2) {
                selectedChessPiece = square[0][0].getChessPiece();
                square[0][0].setChessPiece(null);
                square[3][0].setSelectable(true);
                square[3][0].setChessPiece(selectedChessPiece);
            }
        }
        selectedChessPiece = null;
        setAllUnselected();
        updateUI();
    }
    
    public void killChessPiece(int x, int y) {
        square[x][y].setSelectable(true);
        square[x][y].setChessPiece(null);
        square[x][y].setSelectable(false);
    }
    
    public void capture(boolean captured) {
        if(captured) passedMoves = 0;
        else passedMoves++;
        if(passedMoves >= 100) {
            gameFieldStop = true;
            wonText.setText(owner.getTextByTag("drawGame"));
            saveGame("draw-50moves");
            drawButton.setVisible(false);
            restartButton.setVisible(true);
        }
    }
    
    public void saveGame(String reason) {
        try {
            FileOutputStream fos = new FileOutputStream("data/games/localmp/" + reason + "-" + gameplayData.createFileName() + ".magic-chess");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(gameplayData);
            oos.close();
        } catch(IOException e) {e.printStackTrace();}
    }
}