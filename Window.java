import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Window extends JFrame
{
    // JLabel spot = new JLabel();
    JLabel[][] spots = new JLabel[3][3];

    JLabel bar = new JLabel();

    JLabel[] blueDeck = new JLabel[5];
    JLabel[] redDeck = new JLabel[5];
	// JLabel[][] spots = new JLabel[8][8];

	// images pre-loaded
	ImageIcon a5 = new ImageIcon("A5.png");
    ImageIcon a6 = new ImageIcon("A6.png");

    ImageIcon b5 = new ImageIcon("B5.png");
    ImageIcon b6 = new ImageIcon("B6.png");

    ImageIcon bluedown = new ImageIcon("bluedown.jpg");
    ImageIcon reddown = new ImageIcon("reddown.jpg");

    HashMap<String, ImageIcon> stringToImage;

    State state = new State();
    // Player WPlayer = new Player("w");
    // Player BPlayer = new Player("b");

    // //RFreeza WFreeza = new RFreeza("w");
    // Freeza WFreeza = new Freeza("w");
    // RFreeza BFreeza = new RFreeza("b");

    // public JButton initGame = new JButton("New Game");
    // public JButton initFreeza = new JButton("Start Freeza");
    // public JButton initQueen = new JButton("Queen");
    // public JButton initKnight = new JButton("Knight");
    int column;
    int row;
    // String type;
    //
    // listen class for board
    //
    class listen extends MouseAdapter
    {
        int i;
        int j;
        public listen(int p_i, int p_j)
        {
            i = p_i;
            j = p_j;
        }
        public void mouseClicked(MouseEvent e)
        {
            // Render();
            // System.out.println("spot: " + row + " , " + column);
            // System.out.println("Turn: " + state.Turn);
            // row = i;
        	// column = j;
            // state.row = i;
            // state.column = j;
            // state.update();
            // Render();
        }

        //@Override
        public void mousePressed(MouseEvent e) {
            boolean faceUp = true;
            // TODO Auto-generated method stub
            // super.mouseDragged(e);
            //Render();
            if(e.getButton() == MouseEvent.BUTTON1){
                System.out.println("Left Click");
                // face up
                faceUp = true;
            }
            if(e.getButton() == MouseEvent.BUTTON3){
                System.out.println("Right Click");
                // face down

                faceUp = false;
            }
            row = i;
        	column = j;
            state.row = i;
            state.column = j;
            if(state.Turn.equals("b")){
                state.Piece = state.blueDeck.get(0).piece;
                state.state_card = state.blueDeck.get(0);
                if(faceUp){
                    state.state_card.faceUp = true;
                }
                else{
                    state.state_card.faceUp = false;
                }
            }
            else{
                state.Piece = state.redDeck.get(0).piece;
                state.state_card = state.redDeck.get(0);
                if(faceUp){
                    state.state_card.faceUp = true;
                }
                else{
                    state.state_card.faceUp = false;
                }
            }



            System.out.println("spot: " + row + " , " + column);
            System.out.println("Turn: " + state.Turn);
            System.out.println("Piece: " + state.Piece);
            state.update();
            Render();
        }

        //@Override
        public void mouseDragged(MouseEvent e) {
            boolean faceUp = true;
            // TODO Auto-generated method stub
            // super.mouseDragged(e);
            //Render();
            if(e.getButton() == MouseEvent.BUTTON1){
                System.out.println("Left Click");
                // face up
                faceUp = true;
            }
            if(e.getButton() == MouseEvent.BUTTON3){
                System.out.println("Right Click");
                // face down
                faceUp = false;
            }
            row = i;
        	column = j;
            state.row = i;
            state.column = j;
            if(state.Turn.equals("b")){
                state.Piece = state.blueDeck.get(0).piece;
                state.state_card = state.blueDeck.get(0);
                
            }
            else{
                state.Piece = state.redDeck.get(0).piece;
                state.state_card = state.redDeck.get(0);
            }



            System.out.println("spot: " + row + " , " + column);
            System.out.println("Turn: " + state.Turn);
            System.out.println("Piece: " + state.Piece);
            state.update();
            Render();
        }
    }
    void initGameButton()
    {
        // initGame.setSize(125, 40);
        // initGame.setLocation(275, 30);
        // initGame.setText("New Game");
        // initGame.addActionListener(new ActionListener()
        // {
        //     public void actionPerformed(ActionEvent e)
        //     {
        //         state.newGame();
        //         Render();
        //         RenderDead();
        //     }
        // });
        // add(initGame);
        // initGame.setVisible(true);
    }
    public void init(int p_width, int p_height)
    {

        a5 = new ImageIcon("A5.png");
        a6 = new ImageIcon("A6.png");
    
        b5 = new ImageIcon("B5.png");
        b6 = new ImageIcon("B6.png");

        bluedown = new ImageIcon("bluedown.jpg");
        reddown = new ImageIcon("reddown.jpg");

        stringToImage = new HashMap<String, ImageIcon>();
        stringToImage.put("A5.png", a5);
        stringToImage.put("A6.png", a6);
        stringToImage.put("B5.png", b5);
        stringToImage.put("B6.png", b6);
        stringToImage.put("bluedown.jpg", bluedown);
        stringToImage.put("reddown.jpg", reddown);

        setTitle("Freeza Chess");
        setSize(p_width, p_height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        initBoard();
        initDecks();

        initBar();
        initGameButton();
        initListeners();
        // state.newGame();
        // Render();
    }
    //
    // initialize bar
    //
    void initBar()
    {
        add(bar);
        bar.setSize(500, 150);
        bar.setLocation(250, 780);
        bar.setOpaque(true);
        bar.setBackground(Color.BLUE);
    } 

    //
    // initialize board
    //
    void initBoard()
    {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                spots[i][j] = new JLabel();
                add(spots[i][j]);
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                spots[i][j].setBorder(border);
                spots[i][j].setSize(180,180);
                spots[i][j].setLocation(((j+1) * 180)+45, (i+1)  * 180);
                spots[i][j].setOpaque(true);
                if((i+j) % 2 == 0)
	            {
	               spots[i][j].setBackground(Color.lightGray); // black board spots
	            }
	            else
	            {
	               spots[i][j].setBackground(Color.lightGray); // black board spots
	            }
                spots[i][j].setHorizontalAlignment(JLabel.CENTER);
                spots[i][j].setVerticalAlignment(JLabel.CENTER);
                // if(i == 0 && j == 1){
                //     spots[i][j].setIcon(a4);
                    
                // }
                // else if(j == 2 && i == 0){
                //     spots[i][j].setIcon(b3);
                // }
            }
        }
   	}

    //
    // init decks
    // 
    void initDecks()
    {
        // blue deck
        for(int i = 0; i < 5; i++){
            blueDeck[i] = new JLabel();
            add(blueDeck[i]);
            blueDeck[i].setSize(160,160);
            if(i==0){
                blueDeck[i].setLocation(45, 80);
            }
            else{
                blueDeck[i].setLocation(45, (i+1)*160-80);
            }
            blueDeck[i].setOpaque(true);
            String iconString = state.blueDeck.get(i).piece + ".png";
            blueDeck[i].setIcon(stringToImage.get(iconString));
        }

        // red deck
        for(int i = 0; i < 5; i ++){
            redDeck[i] = new JLabel();
            add(redDeck[i]);
            redDeck[i].setSize(160,160);
            if(i==0){
                redDeck[i].setLocation(800, 80);
            }
            else{
                redDeck[i].setLocation(800, (i+1)* 160-80);
            }
            redDeck[i].setOpaque(true);
            // redDeck[i].setIcon(b5);
            String iconString = state.redDeck.get(i).piece + ".png";
            redDeck[i].setIcon(stringToImage.get(iconString));
        }
    }

    void initListeners()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
            	spots[i][j].addMouseListener(new listen(i, j));
         	}
      	}
    }
	//
    // render total board
    //
    public void Render()
    {
       for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(state.board[i][j].turn.equals("b")){
                    spots[i][j].setBackground(Color.BLUE);
                    if(state.board[i][j].piece.equals("A5")){
                        spots[i][j].setIcon(a5);
                    }
                    else if(state.board[i][j].piece.equals("A6")){
                        spots[i][j].setIcon(a6);
                    }
                    if(state.board[i][j].card.faceUp == false){
                        spots[i][j].setIcon(bluedown);
                    }
                }
                else if(state.board[i][j].turn.equals("r")){
                    spots[i][j].setBackground(Color.RED);
                    if(state.board[i][j].piece.equals("B5")){
                        spots[i][j].setIcon(b5);
                    }
                    else if(state.board[i][j].piece.equals("B6")){
                        spots[i][j].setIcon(b6);
                    }
                    if(state.board[i][j].card.faceUp == false){
                        spots[i][j].setIcon(reddown);
                    }
                }
            }
        }
        //
        // render bar
        //
      	switch(state.Turn)
      	{
         	case "b":
            	bar.setBackground(Color.BLUE);
            	break;
         	case "r":
            	bar.setBackground(Color.RED);
            	break;
      	}

        //
        // render decks
        //
        // blue deck
        int lastIndex = 0;
        for(int i = 0; i < 5; i++){
            try{
                if(state.blueDeck.get(i).piece != null){
                    String iconString = state.blueDeck.get(i).piece + ".png";
                    blueDeck[i].setIcon(stringToImage.get(iconString));
                }
                else{
                    blueDeck[i].setIcon(null);
                }
            }
            catch(Exception e){
                blueDeck[i].setIcon(null);
            }
        }

        // red deck
        for(int i = 0; i < 5; i ++){
            try{
                if(state.redDeck.get(i).piece != null){
                    String iconString = state.redDeck.get(i).piece + ".png";
                    redDeck[i].setIcon(stringToImage.get(iconString));
                }
                else{
                    redDeck[i].setIcon(null);
                }
            }
            catch(Exception e){
                redDeck[i].setIcon(null);
            }
        }
   	}

    
    private void RenderDead()
    {

    } 
}