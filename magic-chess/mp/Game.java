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
    
    public Game(MainFrame owner) {
        super();
        this.owner = owner;
        createGUI();
    }
    
    private void createGUI() {        
        setBounds(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
        setLayout(new BorderLayout());
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(8,8));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(owner.getScreenHeight()/10, (owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10), owner.getScreenHeight()/10, (owner.getScreenWidth()-owner.getScreenHeight())/2+(owner.getScreenHeight()/10)));
        
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
                contentPanel.add(square[x][y]);
            }
        }
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    public void selectSquare(int x, int y, boolean selectable) {
        if(selectable) {
            square[selectedChessPiece.getXPos()][selectedChessPiece.getYPos()].setChessPiece(null);
            square[x][y].setChessPiece(selectedChessPiece);
            selectedChessPiece = null;
            setAllUnselected();
            updateUI();
            return;
        }
        setAllUnselected();
        if(square[x][y].getChessPiece() != null) {
            selectedChessPiece = square[x][y].getChessPiece();
            selectedChessPiece.showMoves();
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