import java.util.*;
import javax.swing.JOptionPane;

public class State
{
    public static String Turn = "b";
    public static String Piece = "";
    public static int row = 0;
    public static int column = 0;

    
    // public static ArrayList<String> wDead = new ArrayList<String>(); 
	// public static ArrayList<String> bDead = new ArrayList<String>();
	// public static ArrayList<inf>wAlive = new ArrayList<inf>();
	// public static ArrayList<inf>bAlive = new ArrayList<inf>();
    // public static boolean wCastleUpLeftMoved = false;
    // public static boolean wCastleUpRightMoved = false;
    // public static boolean wKingMoved = false;
	// public static boolean bCastleDownLeftMoved = false;
    // public static boolean bCastleDownRightMoved = false;
    // public static boolean bKingMoved = false;
    // public static boolean Convert = false;
    // public static boolean checkMate = false;
    // public static boolean staleMate = false;
	// public static String Turn = "b";
    // public static String cTurn = "";
    // public static int cRow = 0;
    // public static int cColumn = 0;
	// public static String[][] Board = new String[8][8];
    // public static boolean kCanMove = false;
    // public static int legalCount = 0;

    //public static String[][] board = new String[3][3];
    public static Coordinate[][] board = new Coordinate[3][3];

    public static Card state_card;

    public static ArrayList<Card> redDeck = new ArrayList<>();
    public static ArrayList<Card> blueDeck = new ArrayList<>();
    
    public void changeTurn()
    {
        if(Turn.equals("b")){
            Turn = "r";
            // remove last card from deck
            blueDeck.remove(0);
        }
        else{
            Turn = "b";
            // remove last card from deck
            redDeck.remove(0);
        }
    }
    public void update()
    {
        if(!isSpotTaken()){
            board[row][column].piece = Piece;
            board[row][column].turn = Turn;
            board[row][column].card = state_card;
            //
            // add logic
            //
            updateBoardFromMove();
            changeTurn();
        }
        else{

        }
        
    }

    public void updateBoardFromMove(){

        // check up
        try{
            if(row-1 >= 0){
                if(state_card.up > board[row-1][column].card.down){
                    board[row-1][column].turn = Turn;
                }
            }
        }
        catch(Exception e){
            // usually out of bounds exception
            // or null object in board array
        }

        // check right
        try{
            // System.out.println("found 2");
            if(column + 1 < 3){
                if(state_card.right > board[row][column+1].card.left){
                    board[row][column+1].turn = Turn;
                }
            }
        }
        catch(Exception e){
            // usually out of bounds exception
            // or null object in board array
        }

        // check down
        try{
            if(row + 1 < 3){
                if(state_card.down > board[row+1][column].card.up){
                    board[row+1][column].turn = Turn;
                }
            }
        }
        catch(Exception e){
            // usually out of bounds exception
            // or null object in board array
        }

        // check left
        try{
            if(column - 1 >= 0){
                if(state_card.left > board[row][column-1].card.right){
                    board[row][column-1].turn = Turn;
                }
            }
        }
        catch(Exception e){
            // usually out of bounds exception
            // or null object in board array
        }
        

    }

    boolean isSpotTaken(){
        if(!board[row][column].turn.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    public State(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Coordinate coord = new Coordinate();
                coord.piece = "";
                coord.turn = "";
                board[i][j] = coord;
            }
        }

        for(int i = 0; i < 5; i++){
            if(i % 2 == 0){
                // String a5 = "A5";
                Card a5 = new Card();
                a5.piece = "A5";
                a5.up = 4;
                a5.right = 5;
                a5.down = 6;
                a5.left = 5;
                blueDeck.add(a5);
            }
            else{
                Card a6 = new Card();
                a6.piece = "A6";
                a6.up = 9;
                a6.right = 6;
                a6.down = 9;
                a6.left = 1;
                blueDeck.add(a6);
            }
        }

        for(int i = 0; i < 5; i++){
            if(i % 2 == 0){
                Card b5 = new Card();
                b5.piece = "B5";
                b5.up = 2;
                b5.right = 7;
                b5.down = 8;
                b5.left = 3;
                redDeck.add(b5);
            }
            else{
                Card b6 = new Card();
                b6.piece = "B6";
                b6.up = 4;
                b6.right = 8;
                b6.down = 9;
                b6.left = 8;
                redDeck.add(b6);
            }
        }
    }
    // //
    // // checks for checkmate but also accounts for stalemates as well
    // //
    // void updateCheckMate()
    // {
    //     int kRow = 0;
    //     int kColumn = 0;
    //     Danger danger = new Danger();
    //     if(Turn == "w")
    //     {
    //         for(int i = 0; i < wAlive.size(); i++)
    //         {
    //             if(wAlive.get(i).type == "wk")
    //             {
    //                 kRow = wAlive.get(i).startRow;
    //                 kColumn = wAlive.get(i).startColumn;
    //                 break;
    //             }
    //         }
    //     }
    //     else if(Turn == "b")
    //     {
    //         for(int i = 0; i < bAlive.size(); i++)
    //         {
    //             if(bAlive.get(i).type == "bk")
    //             {
    //                 kRow = bAlive.get(i).startRow;
    //                 kColumn = bAlive.get(i).startColumn;
    //                 break;
    //             }
    //         }
    //     }
    //     // if king can move away, out of check
    //     if(danger.checkRunAway(kRow, kColumn))
    //     {
    //         kCanMove = true;
    //         checkMate = false;
    //     }
    //     else
    //     {
    //         kCanMove = false;
    //     }
    //     // if king is in check at this state
    //     if(danger.inDanger(kRow, kColumn))
    //     {
    //         // check danger up
    //         if(!kCanMove)
    //         {
    //             if(danger.checkUp)
    //             {
    //                 for(int i = kRow - 1; i >= danger.checkRow; i--)
    //                 {
    //                     if(danger.range(i, kColumn))
    //                     {
    //                         danger.moveTemp(danger.rangeRow, danger.rangeColumn, i, kColumn);
    //                         if(danger.inDanger(kRow, kColumn))
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, i, kColumn);
    //                             checkMate = true;
    //                         }
    //                         else
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, i, kColumn);
    //                             checkMate = false;
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             // check danger down
    //             else if(danger.checkDown)
    //             {
    //                 for(int i = kRow + 1; i <= danger.checkRow; i++)
    //                 {
    //                     if(danger.range(i, kColumn))
    //                     {
    //                         danger.moveTemp(danger.rangeRow, danger.rangeColumn, i, kColumn);
    //                         if(danger.inDanger(kRow, kColumn))
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, i, kColumn);
    //                             checkMate = true;
    //                         }
    //                         else
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, i, kColumn);
    //                             checkMate = false;
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             // check danger right
    //             else if(danger.checkRight)
    //             {
    //                 for(int i = kColumn + 1; i <= danger.checkColumn; i++)
    //                 {
    //                     if(danger.range(kRow, i))
    //                     {
    //                         danger.moveTemp(danger.rangeRow, danger.rangeColumn, kRow, i);
    //                         if(danger.inDanger(kRow, kColumn))
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow, i);
    //                             checkMate = true;
    //                         }
    //                         else
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow, i);
    //                             checkMate = false;
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             // check danger left
    //             else if(danger.checkLeft)
    //             {
    //                 for(int i = kColumn - 1; i >= danger.checkColumn; i--)
    //                 {
    //                     if(danger.range(kRow, i))
    //                     {
    //                         danger.moveTemp(danger.rangeRow, danger.rangeColumn, kRow, i);
    //                         if(danger.inDanger(kRow, kColumn))
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow, i);
    //                             checkMate = true;
    //                         }
    //                         else
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow, i);
    //                             checkMate = false;
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             // check danger upright
    //             else if(danger.checkUpRight)
    //             {
    //                 for(int i = 1; i <= kRow - danger.checkRow; i++)
    //                 {
    //                     if(danger.range(kRow - i, kColumn + i))
    //                     {
    //                         danger.moveTemp(danger.rangeRow, danger.rangeColumn, kRow - i, kColumn + i);
    //                         if(danger.inDanger(kRow, kColumn))
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow - i, kColumn + i);
    //                             checkMate = true;
    //                         }
    //                         else
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow - i, kColumn + i);
    //                             checkMate = false;
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             // check danger upleft
    //             else if(danger.checkUpLeft)
    //             {
    //                 for(int i = 1; i <= kRow - danger.checkRow; i++)
    //                 {
    //                     if(danger.range(kRow - i, kColumn - i))
    //                     {
    //                         danger.moveTemp(danger.rangeRow, danger.rangeColumn, kRow - i, kColumn - i);
    //                         if(danger.inDanger(kRow, kColumn))
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow - i, kColumn - i);
    //                             checkMate = true;
    //                         }
    //                         else
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow - i, kColumn - i);
    //                             checkMate = false;
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             // check danger downleft
    //             else if(danger.checkDownLeft)
    //             {
    //                 for(int i = 1; i <= danger.checkRow - kRow; i++)
    //                 {
    //                     if(danger.range(kRow + i, kColumn - i))
    //                     {
    //                         danger.moveTemp(danger.rangeRow, danger.rangeColumn, kRow + i, kColumn - i);
    //                         if(danger.inDanger(kRow, kColumn))
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow + i, kColumn - i);
    //                             checkMate = true;
    //                         }
    //                         else
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow + i, kColumn - i);
    //                             checkMate = false;
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             // check danger downright
    //             else if(danger.checkDownRight)
    //             {
    //                 for(int i = 1; i <= danger.checkRow - kRow; i++)
    //                 {
    //                     if(danger.range(kRow + i, kColumn + i))
    //                     {
    //                         danger.moveTemp(danger.rangeRow, danger.rangeColumn, kRow + i, kColumn + i);
    //                         if(danger.inDanger(kRow, kColumn))
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow + i, kColumn + i);
    //                             checkMate = true;
    //                         }
    //                         else
    //                         {
    //                             danger.moveBack(danger.rangeRow, danger.rangeColumn, kRow + i, kColumn + i);
    //                             checkMate = false;
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             else if(danger.checkKnight)
    //             {
    //                 if(danger.range(danger.checkRow, danger.checkColumn))
    //                 {
    //                     danger.moveTemp(danger.rangeRow, danger.rangeColumn, danger.checkRow, danger.checkColumn);
    //                     if(danger.inDanger(kRow, kColumn))
    //                     {
    //                         danger.moveBack(danger.rangeRow, danger.rangeColumn, danger.checkRow, danger.checkColumn);
    //                         checkMate = true;
    //                     }
    //                     else
    //                     {
    //                         danger.moveBack(danger.rangeRow, danger.rangeColumn, danger.checkRow, danger.checkColumn);
    //                         checkMate = false;
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //             else if(danger.checkPawn)
    //             {
    //                 if(danger.range(danger.checkRow, danger.checkColumn))
    //                 {
    //                     danger.moveTemp(danger.rangeRow, danger.rangeColumn, danger.checkRow, danger.checkColumn);
    //                     if(danger.inDanger(kRow, kColumn))
    //                     {
    //                         danger.moveBack(danger.rangeRow, danger.rangeColumn, danger.checkRow, danger.checkColumn);
    //                         checkMate = true;
    //                     }
    //                     else
    //                     {
    //                         danger.moveBack(danger.rangeRow, danger.rangeColumn, danger.checkRow, danger.checkColumn);
    //                         checkMate = false;
    //                     }
    //                 }
    //                 if(!danger.rangeFound)
    //                 {
    //                     checkMate = true;
    //                 }
    //             }
    //         }
    //         else
    //         {
    //             checkMate = false;
    //         }
    //     }
    //     // if king is not in check at this state
    //     else
    //     {
    //         // if king can move checkmate false;
    //         if(kCanMove)
    //         {
    //             checkMate = false;
    //             onlyKings();
    //         }
    //         else
    //         {
    //             if(anyCanMove())
    //             {
    //                 checkMate = false;
    //             }
    //             else
    //             {
    //                 checkMate = true;
    //                 staleMate = true;
    //             }
    //         }
    //     }
    //     updateMessage();
    // }
    // boolean anyCanMove()
    // {
    //     Danger danger = new Danger();
    //     if(Turn == "w")
    //     {
    //         for(int i = 0; i < wAlive.size(); i++)
    //         {
    //             if(wAlive.get(i).type != "wk")
    //             {
    //                 if(danger.canMove(wAlive.get(i).startRow, wAlive.get(i).startColumn, wAlive.get(i).type))
    //                 {
    //                     return true;
    //                 }
    //             }
    //             if(i == wAlive.size() - 1)
    //             {
    //                return false;
    //             }
    //         }
    //     }
    //     else
    //     {
    //         for(int i = 0; i < bAlive.size(); i++)
    //         {
    //             if(bAlive.get(i).type != "bk")
    //             {
    //                 if(danger.canMove(bAlive.get(i).startRow, bAlive.get(i).startColumn, bAlive.get(i).type))
    //                 {
    //                     return true;
    //                 }
    //             }
    //             if(i == bAlive.size() - 1)
    //             {
    //                 return false;
    //             }
    //         }
    //     }
    //     return false;
    // }
    // void onlyKings()
    // {
    //     if(wAlive.size() == 1 && bAlive.size() == 1)
    //     {
    //         staleMate = true;
    //         checkMate = true;
    //     }
    // }
    // public void newGame()
    // {
    //     Turn = "b";
    //     checkMate = false;
    //     staleMate = false;
    //     bDead.clear();
    //     wDead.clear();
    //     wCastleUpLeftMoved = false;
    //     wCastleUpRightMoved = false;
    //     wKingMoved = false;
    //     bCastleDownLeftMoved = false;
    //     bCastleDownRightMoved = false;
    //     bKingMoved = false;
    //     //
    //     // initialize with all blanks at first
    //     //
    //     for(int i = 0; i < 8; i++)
    //     {
    //         for(int j = 0; j < 8; j++)
    //         {
    //             Board[i][j] = "z";
    //         }
    //     }
    //     //
    //     // set white pieces
    //     //
    //     Board[0][0] = "wc";
    //     Board[0][1] = "wn";
    //     Board[0][2] = "wb";
    //     Board[0][3] = "wq";
    //     Board[0][4] = "wk";
    //     Board[0][5] = "wb";
    //     Board[0][6] = "wn";
    //     Board[0][7] = "wc";
    //     for(int i = 0; i < 8; i++)
    //     {
    //        Board[1][i] = "wp";
    //     }
      
    //     //set black pieces
    //     for(int i = 0; i < 8; i++)
    //     {
    //        Board[6][i] = "bp";
    //     }
    //     Board[7][0] = "bc";
    //     Board[7][1] = "bn";
    //     Board[7][2] = "bb";
    //     Board[7][3] = "bq";
    //     Board[7][4] = "bk";
    //     Board[7][5] = "bb";
    //     Board[7][6] = "bn";
    //     Board[7][7] = "bc";  
    //     updateAlive();
    // }
    // void updateMessage()
    // {
    //     if(checkMate)
    //     {
    //         if(!staleMate)
    //         {
    //             JOptionPane.showMessageDialog(null, "CheckMate, game is over.");
    //         }
    //         else if(staleMate)
    //         {
    //             JOptionPane.showMessageDialog(null, "staleMate, game is over.");
    //         }
    //     }
    // }
   	// void updateAlive()
   	// {
    //   	wAlive.clear();
    //   	bAlive.clear();
    //   	for(int i = 0; i < 8; i++)
    //   	{
    //      	for(int j = 0; j < 8; j++)
    //      	{
    //           	if(Board[i][j].startsWith("b"))
    //         	{
    //           	  	bAlive.add(new inf(Board[i][j],i,j));
    //         	}
    //        	    if(Board[i][j].startsWith("w"))
    //         	{
    //             	wAlive.add(new inf(Board[i][j],i,j));
    //         	}
    //        	}
    //   	}
   	// }
}