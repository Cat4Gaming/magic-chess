package mp;

import java.io.Serializable;
import java.util.*;
import java.time.*;

public class GameplayData implements Serializable {
    List gameXData, gameYData, gameIsBlackData, gamePNumData, gameFirstMoveData;
    
    public GameplayData() {
        gameXData = new ArrayList();
        gameYData = new ArrayList();
        gameIsBlackData = new ArrayList();
        gamePNumData = new ArrayList();
        gameFirstMoveData = new ArrayList();
    }
    
    public boolean addMoveDataAndCheckThreefold(Square[][] squares) {
        int[] xPos = new int[32];
        int[] yPos = new int[32];
        boolean[] isBlack = new boolean[32];
        int[] pNum = new int[32];
        boolean[] firstMove = new boolean[32];
        int i = 0;
        
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                ChessPiece tmp = squares[x][y].getChessPiece();
                if(tmp != null) {
                    xPos[i] = tmp.getXPos();
                    yPos[i] = tmp.getYPos();
                    isBlack[i] = tmp.isBlack();
                    pNum[i] = tmp.getPieceNumber();
                    if(tmp.getMoved() < 2) firstMove[i] = false;
                    else firstMove[i] = true;
                    i++;
                }
            }
        }
        
        gameXData.add(xPos);
        gameYData.add(yPos);
        gameIsBlackData.add(isBlack);
        gamePNumData.add(pNum);
        
        i = 0;
        for(int z = 0; z < gameXData.size(); z++) {
            if(Arrays.equals(xPos, (int[]) gameXData.get(z)) && Arrays.equals(yPos, (int[]) gameYData.get(z)) && Arrays.equals(isBlack, (boolean[]) gameIsBlackData.get(z)) && Arrays.equals(pNum, (int[])gamePNumData.get(z))) i++;
            if(i == 3) return true;
        }
        return false;
    }
    
    public String createFileName() {
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        String s = (ld + "-time-" + lt);
        return s.replace(":", "-");
    }
}
